package Function;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import Config.Env;
import Util.Station;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/* -------------------------------------------------------------------------------------------------------------------------------------------------------------------------- */
public class Function {
		
	/** [1] 서버에서 받은 유저명들을 리스트화 시킨다. **/
	List<String> setListView(String data) {	

		List<String> nameList = new Vector<String>();
		
		StringBuffer sb = new StringBuffer(data);
		
		data = sb.delete(0,"/list ".length()).toString();

		StringTokenizer st = new StringTokenizer(data, " ");
		
		while(st.hasMoreTokens()) {
			nameList.add(st.nextToken());
		}

		return nameList;
	}
	
	/** [2] 서버로 부터 유저목록을 받아 화면에 출력한다. **/
	public void printlistView(String namelist) {
		
		List<String> userList = setListView(namelist);
		
		ObservableList<String> nameList = FXCollections.observableList(userList);
		// JavaFX UI 스레드
		Platform.runLater(()-> {
			Station.ctr0.listView.setItems(nameList);
		});	
	}
/* -------------------------------------------------------------------------------------------------------------------------------------------------------------------------- */	
	/** 닉네임성공 -> 화면종료 **/	
	public void connected() {
		Platform.runLater(()-> {
			Station.ctr1.stage.close(); 
			Station.ctr0.txtInput.setDisable(false);
			Station.ctr0.btnSend.setDisable(false);
			Station.ctr0.btnConn.setText("종료");
		});
	}
	/** 닉네임실패 -> 메시지갱신 **/	
	public void dupName() {
		// JavaFX UI 스레드
		Platform.runLater(()-> {
			Station.ctr1.setLabel("중복된 닉네임입니다.");
		});
	}
/* -------------------------------------------------------------------------------------------------------------------------------------------------------------------------- */	
	/** 채팅 출력 **/
	public void display(String data) {
		// JavaFX UI 스레드
		Platform.runLater(()-> {
			Station.ctr0.txtDisplay.appendText(data + "\n");
		});
	}
/* -------------------------------------------------------------------------------------------------------------------------------------------------------------------------- */	
	/** 종료문 출력 **/
	public void stopLog() {
		// JavaFX UI 스레드
		Platform.runLater(()-> {
			Station.ctr0.txtDisplay.appendText("서버와 연결이 끊어졌습니다.\n");
		});
	}
/* -------------------------------------------------------------------------------------------------------------------------------------------------------------------------- */	
	/** 샌드박스 체크 **/
	public Boolean checkAll(String data) {
		if(Station.ctr0.sendBox.getValue().equals("전체")) {
			return true;
		} else {
			// 귓속말 고정
			sendStaticWhisper(data);
			return false;
		}
	}
	/** 샌드박스 추가 **/
	public void addSendBoxName(String data) {
		StringBuffer sb = new StringBuffer(data);
		// [1] 식별자 제거
		String name = sb.delete(0, "/w ".length()).toString();
		
		Platform.runLater(()->{
			// [2] 유저명 추가
			Station.ctr0.sendBox.getItems().add(name);
		});
	}
	/** 귓속말 고정 **/
	public void sendStaticWhisper(String data) {
		
		Thread thread = new Thread() {
			@Override
			public void run() {
				try {
					String message = "/w " + Station.ctr0.sendBox.getValue() + " " + data;
					
					Charset charset = Charset.forName("UTF-8");
					ByteBuffer byteBuffer = charset.encode(message);
					Env.socketChannel.write(byteBuffer);
					
				} catch(Exception e) {
					Station.ctr0.txtDisplay.appendText("전송 할 수 없습니다.\n");
				}
			}
		};
		thread.start();
	}
/* -------------------------------------------------------------------------------------------------------------------------------------------------------------------------- */	
	
	
	
	
	
}
