package Function;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Set;
import java.util.stream.Stream;

import Model.Service;
import Template.Station;

public class Sender {	
	/** 송신 : 일반채팅 **/
	public void sendAll(SocketChannel socketChannel, String message) {
		
		Set<SocketChannel> keySet = Service.connections.keySet();
		Stream<SocketChannel> keySetStream = keySet.parallelStream();
		keySetStream.forEach(k-> {
			send(k, Finder.user(socketChannel) + " : " + message);
		});
	}
	/** 송신 : 아이템 **/
	public void sendItem(String data) {
		Set<SocketChannel> keySet = Service.connections.keySet();
		Stream<SocketChannel> keySetStream = keySet.parallelStream();
		keySetStream.forEach(k-> {
			send(k, data);
		});
	}
	
	public void send(SocketChannel socketChannel, String data) {
		/** 송신 : 개인 **/
		Runnable runnable =  new Runnable() {
		 @Override
		 	public void run() {

				try {
					ByteBuffer byteBuffer = Service.charset.encode(data);
					socketChannel.write(byteBuffer);
					Log.send(socketChannel, Thread.currentThread().getName());

				} catch(Exception e) {
					try {	
						Service.connections.remove(socketChannel);						
						Log.disconnect(socketChannel, Thread.currentThread().getName());
						Station.ctr0.updateStatus();
						socketChannel.close();
						 
					}catch(IOException ioe) {}
				}		
		 	}
		};
		Service.executorService.submit(runnable);
	}
}
