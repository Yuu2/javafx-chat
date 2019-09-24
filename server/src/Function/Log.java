package Function;
import java.io.IOException;
import java.nio.channels.SocketChannel;

import Template.Station;
import javafx.application.Platform;
/* -------------------------------------------------------------------------------------------------------------------------------------------------------------------------- */
public class Log {

	private Log() {}
	
	/*** 연결 로그 ***/
	public static void connect(SocketChannel socketChannel, String curThread) {
		Platform.runLater(()-> {
			try {	Station.ctr0.txtDisplay.appendText("connect " + socketChannel.getRemoteAddress() +":" + curThread + "\n");	} catch (IOException ioe) {}
		});	
	}
	/*** 해제 로그 ***/
	public static void disconnect(SocketChannel socketChannel, String curThread) {
		Platform.runLater(()-> {
			try {	Station.ctr0.txtDisplay.appendText("disconnect " + socketChannel.getRemoteAddress() +":" + curThread + "\n");	} catch (IOException ioe) {}
		});	
	}
	
	/*** 송신 로그 ***/
	public static void send(SocketChannel socketChannel, String curThread) { 
		Platform.runLater(()-> {
			try {	Station.ctr0.txtDisplay.appendText("send  " + socketChannel.getRemoteAddress()  + curThread + "\n");	} catch (IOException e) {}
		});
	}
	/*** 수신 로그 ***/
	public static void receive(SocketChannel socketChannel, String curThread) {
		Platform.runLater(()-> {
			try {	Station.ctr0.txtDisplay.appendText("receive " + socketChannel.getRemoteAddress()  + curThread + "\n");	} catch (IOException e) {}
		});
	}
	
	/*** 커스텀 로그 ***/
	public static void custom(String text) {
		Platform.runLater(()-> {	  
			Station.ctr0.txtDisplay.appendText(text + "\n");
		});
	}
}
