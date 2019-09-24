package Function;

import java.nio.channels.SocketChannel;
import java.util.Map;

import Model.Service;
/* -------------------------------------------------------------------------------------------------------------------------------------------------------------------------- */
public class Finder {
	/** 해시테이블 소켓(K) 검색**/
	public static SocketChannel socket(Map<SocketChannel, String> m, Object v) {
		for(SocketChannel socketChannel : m.keySet()) {
			if(m.get(socketChannel).equals(v)) {
				return socketChannel;
			}
		}
		return null;
	}
	
	/** 해시테이블 닉네임(V) 검색 **/ 
	public static String user(SocketChannel socketChannel) {
		return Service.connections.get(socketChannel);
	}
}
