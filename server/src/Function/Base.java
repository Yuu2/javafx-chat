package Function;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Set;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import Model.Cmd;
import Model.Service;
import Model.SysInfo;
import Template.Station;
import javafx.application.Platform;


/* -------------------------------------------------------------------------------------------------------------------------------------------------------------------------- */
public class Base {
	
	
	
	/*** 서버 시작 ***/
	public void startServer()	{	

		Service.executorService = new ThreadPoolExecutor(0,20, 1L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());	/*  코어스레드, 최대스레드, 대기시간, 시간단위, 큐  */
		
		try {
			// 구동 시 설정 버튼 비활성화
			Platform.runLater(()-> {
				Station.ctr0.btnConfig.setDisable(true);
			});
			// 서버소켓채널 오픈
			Service.serverSocketChannel = ServerSocketChannel.open();		
			// 서버소켓채널 비동기식
			Service.serverSocketChannel.configureBlocking(true);					
			// 서버소켓채널 바인딩
			Service.serverSocketChannel.bind(new InetSocketAddress(SysInfo.getSERVER_IP(), Integer.parseInt(SysInfo.getSERVER_PORT())));	
			// 접속자 수 and 유저 수 업데이트
			Station.ctr0.updateStatus();
			
		} catch(Exception e) {
			Log.custom("구동설정을 확인해 주십시오.\n");
			if(Service.serverSocketChannel.isOpen()) {stopServer();}
			return;
		} 
		
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {	
				try {
					// JDBC 객체 등록
					Class.forName("com.mysql.jdbc.Driver");		
					// 커넥션 설정
					Service.conn = DriverManager.getConnection("jdbc:mysql://"+ SysInfo.getDB_IP() + ":"+ SysInfo.getDB_PORT()+"/" +  SysInfo.getDB_NAME() + "?autoReconnect=true&useSSL=false", SysInfo.getDB_ID(), SysInfo.getDB_PW()); 
					Log.custom("Connected to MySQL 5.7.10 \n성공적으로 연결되었습니다.\n클라이언트 접속 대기 중 ...\n");
					// 전체채팅방 해시테이블 설정
					Service.connections = new Hashtable<SocketChannel, String>();
					// 구동 시 설정 버튼 비활성화

					while(true) {
						try {	
							// [1] 소켓채널 작업 : ACCEPT 
							SocketChannel socketChannel = Service.serverSocketChannel.accept();	
							Log.connect(socketChannel, Thread.currentThread().getName());
							// [2] IP 저장
							String ip = socketChannel.getRemoteAddress().toString().substring(1,socketChannel.getRemoteAddress().toString().length());
							// [3] 블랙리스트 검사
							if(QueryBase.selectBlacklist(ip)) { 
								socketChannel.close(); 
								continue;
							}		
							//[4] 해시테이블 추가.
							Service.connections.put(socketChannel, "");
							receive(socketChannel, ip);
								
						} catch(Exception e) {
							Log.custom("\n서버를 종료합니다.\n");
							if(Service.serverSocketChannel.isOpen()) {stopServer();}
							break;
						}
					}	
				} catch (ClassNotFoundException cnfe) {
					Log.custom(cnfe.toString());
				} catch (SQLException sqle ) {
					Log.custom(sqle.toString());
				} finally {
					stopServer();
				}
			}
		};
		Service.executorService.submit(runnable);	
	}
/* -------------------------------------------------------------------------------------------------------------------------------------------------------------------------- */	
	
	/*** 데이터 수신 ***/
	void receive(SocketChannel socketChannel, String ip) {
		
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				try {
					Sender s = new Sender();
					Whisper w = new Whisper();
					
					while(true) {
						// 채널의 데이터 교환 방식(READ) : 바이트 -> 문자열
						ByteBuffer byteBuffer = ByteBuffer.allocate(Service.bufferSize);					
						int readByteCount = socketChannel.read(byteBuffer);
						if(readByteCount ==-1) {throw new IOException();}
						// 지정한 바이트 수만큼 LIMIT 재조정
						byteBuffer.flip();
						
						Log.receive(socketChannel, Thread.currentThread().getName());

						String data = Service.charset.decode(byteBuffer).toString();
					
						data = QueryBase.insertUser(data,ip);
						
						if(data!=null) {
							// 유저(소켓, 이름)을 해시테이블에 추가
							Service.connections.put(socketChannel, data);
							Station.ctr0.updateStatus();
							
							// [1] 중복검사 리턴 True
							s.send(socketChannel,Cmd.key1); 
							Log.send(socketChannel, Thread.currentThread().getName());
							
							break;
						} else {
							// [2] 중복검사 리턴 false
							s.send(socketChannel,Cmd.key2);
							Log.send(socketChannel, Thread.currentThread().getName());
						}	
					}
					/***********************************************************/
					s.send(socketChannel,"채팅서버에 오신 것을 환영합니다.\n");			
					/***********************************************************/
					while(true) {

						ByteBuffer byteBuffer = ByteBuffer.allocate(Service.bufferSize);	
						int readByteCount = socketChannel.read(byteBuffer);
						if(readByteCount ==-1) {throw new IOException();}
						
						byteBuffer.flip();
						
						String message = Service.charset.decode(byteBuffer).toString();		
						
						Log.receive(socketChannel, Thread.currentThread().getName());	
						
						/**  채팅 기록 **/
						QueryBase.insertLog(Finder.user(socketChannel),message,ip);	
						
						/** 명령어 체크 **/
						if(Checker.check(message)) {	
							// 귓속말
							if(message.indexOf(Cmd.key3)==0) {
								w.whisper(socketChannel,Finder.user(socketChannel), message);
								continue;
							}		
							continue;								
						}	
						/** 전체 채팅 **/
						s.sendAll(socketChannel, message);
					}	
				} catch(Exception e) {
					QueryBase.deleteUser(Finder.user(socketChannel));
					Service.connections.remove(socketChannel);
					Station.ctr0.updateStatus();
					Log.disconnect(socketChannel, Thread.currentThread().getName());	
					try { socketChannel.close(); } catch (Exception ioe) {}
				} 
			}
		};
		Service.executorService.submit(runnable);
	}
/* -------------------------------------------------------------------------------------------------------------------------------------------------------------------------- */
		/*** 서버 종료 ***/
		public void stopServer() {	
			
				/** Connection **/
				if(Service.conn!=null) {
					try {	Service.conn.close();	} catch (SQLException e) {}
				}
				
				/** SocketChannel **/
				if(Service.connections != null) {
					Set<SocketChannel> keySet = Service.connections.keySet();
					Stream<SocketChannel> keySetStream = keySet.stream();
					keySetStream.forEach(socket-> {
						try {	socket.close();	} catch (IOException e) {	}
					});
					Service.connections.clear();
				}
				
				/** ServerSocketChannel **/
				if(Service.serverSocketChannel!=null && Service.serverSocketChannel.isOpen()) {
					try {	Service.serverSocketChannel.close();	} catch (IOException e) {}
				}
				
				/** ThreadPool **/
				if(Service.executorService!=null && !Service.executorService.isShutdown()) {
					Service.executorService.shutdown();
				} 
				
				/** JavaFX UI **/
				Platform.runLater(()->{
					Station.ctr0.btnConfig.setDisable(false);
					Station.ctr0.btnNotice.setDisable(true);
					Station.ctr0.btnRefresh.setDisable(true);
					Station.ctr0.btnBlock.setDisable(true);
					Station.ctr0.btnStartStop.setText("서버시작");
				});
		}	
}
