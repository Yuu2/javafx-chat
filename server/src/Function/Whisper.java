package Function;

import java.nio.channels.SocketChannel;

import Model.Cmd;
import Model.Service;

public class Whisper {
	
	/** 귓속말 **/
	public void whisper(SocketChannel socketChannel, String from, String data) {
		try {
			Sender s = new Sender();
			
			StringBuffer sb = new StringBuffer(data);
			// 식별자 제거
			sb = sb.delete(0, Cmd.key3.length()); 

			String message = sb.toString();
			String to; 
					
			/** 귓속말 비고정 or 고정 **/
			// [1] 비고정
			if(message.contains(" ")) {	
				to = sb.substring(0,message.indexOf(" "));  
				// [1-1] 접속 확인
				if(QueryBase.selectUser(to)==null) {
					s.send(socketChannel, "존재하지 않는 대상입니다.");
					return;
				} else {
					// [1-2] 메시지에서 상대방의 이름을 분리
					message = sb.delete(0, to.length() +1 ).toString();
					// [1-3] 상대방 소켓 검색
					SocketChannel toSocketChannel = Finder.socket(Service.connections, to);
					// [1-3-1] 존재하지 않을 시...
					if(toSocketChannel==null) { 
						s.send(socketChannel,"존재하지 않는 대상입니다.");
						return;
					// [1-3-2] 자신에게 귓말 했을 시...
					} else if(toSocketChannel==socketChannel) {
						s.send(socketChannel,"자신에게 귓속말을 할 수 없습니다.");
						return;
					}
					// [1-4] 상대방에게 전송
					s.send(toSocketChannel,"[From.] " + from + " : "+ message);
				}
			// [2] 고정
			} else {
				to = sb.toString();
				// [2-1] 나에게 전송
				s.send(socketChannel,"/w " + to);
			}
		} catch (Exception e){}
	}
}
