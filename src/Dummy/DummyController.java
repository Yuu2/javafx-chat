package Dummy;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

import Config.Env;
import Function.Function;
/* -------------------------------------------------------------------------------------------------------------------------------------------------------------------------- */
public class DummyController {
	
	Function func = new Function();

	/*** 클라이언트 시작 ***/
	public void startClient() {
		Thread thread = new Thread() {
		@Override
			public void run() {
				try {			
					// 소켓채널 오픈
					Env.socketChannel = SocketChannel.open();
					Env.socketChannel.configureBlocking(true);
					Env.socketChannel.connect(new InetSocketAddress("localhost",5001)); 		
					
				}catch(Exception e) {
					if(Env.socketChannel.isOpen()) {stopClient();}
					return;
				}
				receive();
			}
		};
		thread.start();	
	}
/* -------------------------------------------------------------------------------------------------------------------------------------------------------------------------- */	
	/*** 클라이언트 정지 ***/
	public void stopClient() {
		try {			
			if(Env.socketChannel!=null && Env.socketChannel.isOpen()) {
			   Env.socketChannel.close();
			}
		}catch(IOException ioe){}
	}
/* -------------------------------------------------------------------------------------------------------------------------------------------------------------------------- */	
	
	/*** 데이터 수신 ***/
	void receive() {
		
		while(true) {
			try {
					ByteBuffer byteBuffer = ByteBuffer.allocate(Env.bufferSize);
					int readByteCount = Env.socketChannel.read(byteBuffer);
					if(readByteCount ==-1) {throw new IOException();}
					
					byteBuffer.flip();
					
					Charset charset = Charset.forName("UTF-8");
					/***************************************************/
					String data = charset.decode(byteBuffer).toString();
					/***************************************************/
					
					/** 닉네임 성공 **/
					if(data.equals("/connect")) {				
						func.connected(); continue;
					}
					/** 닉네임 실패 **/	
					if(data.equals("/dupName")) {  												
						func.dupName(); continue;
					}
					/** 유저목록 출력 **/
					if(data.indexOf("/list")==0) {				
						func.printlistView(data); 
						continue;
					}
					/** 귓속말 고정 **/
					if(data.indexOf("/w ")==0) {
						func.addSendBoxName(data);
						continue;
					}
					
					func.display(data);
					
			} catch(Exception e) {
				func.stopLog();
				stopClient(); 
				break;
			}
		}
	}
/* -------------------------------------------------------------------------------------------------------------------------------------------------------------------------- */	
	/*** 데이터 송신 ***/
	public void send(String data) {
		Thread thread = new Thread() {
			@Override
			public void run() {
				try {
					if(!func.checkAll(data)) {	return;	}
					
					Charset charset = Charset.forName("UTF-8");
					ByteBuffer byteBuffer = charset.encode(data);
					Env.socketChannel.write(byteBuffer);
					
				}catch(Exception e) {
					func.stopLog();
					stopClient();
				}
			}
		};
		thread.start();
	}
}
