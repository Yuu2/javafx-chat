package Function;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

import Config.Env;
import Util.Display;

public class Machine {
  
  // 소켓 채널 열기
  public void start() {    
    try {
      Env.socketChannel = SocketChannel.open();
      Env.socketChannel.configureBlocking(true);
      Env.socketChannel.connect(new InetSocketAddress("localhost",5001));
    
    } catch(Exception e) {
      if(Env.socketChannel.isOpen()) { 
        stop();
      }
    }
    receive();
  }  
  
  // 소켓 채널 닫기 
  public void stop() {
    try {
      if(Env.socketChannel!=null && Env.socketChannel.isOpen()) {
        Env.socketChannel.close();
      }
    } catch(IOException ioe){}
  }
  
  // 데이터 수신
  public void receive() {
    while(true) {
      try {  
        
        ByteBuffer byteBuffer = ByteBuffer.allocate(Env.bufferSize);
          
        int readByteCount = Env.socketChannel.read(byteBuffer);
          
        if(readByteCount ==-1) {
          throw new IOException();
        }
          
        byteBuffer.flip();
          
        Charset charset = Charset.forName("UTF-8");

        String data = charset.decode(byteBuffer).toString();

          
//          /** 닉네임 성공 **/
//          if(data.equals("/connect")) {       
//            func.connected(); continue;
//          }
//          /** 닉네임 실패 **/  
//          if(data.equals("/dupName")) {                         
//            func.dupName(); continue;
//          }
//          /** 유저목록 출력 **/
//          if(data.indexOf("/list")==0) {        
//            func.printlistView(data); 
//            continue;
//          }
//          /** 귓속말 고정 **/
//          if(data.indexOf("/w ")==0) {
//            func.addSendBoxName(data);
//            continue;
//          }
          
          Display.add(data);
          
      } catch(Exception e) {
        func.stopLog();
        stopClient(); 
        break;
      }
  }
}
