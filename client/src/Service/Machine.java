package Service;

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
        shutdown();
      }
    }
    receive();
  }  
  
  // 소켓 채널 닫기 
  public void shutdown() {
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
          
        if(readByteCount == -1) {
          throw new IOException();
        }
          
        byteBuffer.flip();
          
        Charset charset = Charset.forName("UTF-8");

        String data = charset.decode(byteBuffer).toString();
              
        Display.add(data);
          
      } catch(Exception e) {
        Display.add("서버와 연결이 끊어졌습니다. \n");
        shutdown();
      }
    }
  }
  // 데이터 송신
  public void send(String data) {
    
    Thread thread = new Thread() {
      
        @Override
        public void run() {
            try {
                
                Charset charset = Charset.forName("UTF-8");
                
                ByteBuffer byteBuffer = charset.encode(data);
                
                Env.socketChannel.write(byteBuffer);
                
            } catch(Exception e) {
              Display.add("서버와 연결이 끊어졌습니다. \n");
              shutdown();
            }
        }
    };
    thread.start();
  }
}
