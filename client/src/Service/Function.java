package Service;
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

public class Function {
		
  // 
  public void connected() {
    Platform.runLater(()-> {
        Station.login.stage.close(); 
        Station.main.txtInput.setDisable(false);
        Station.main.btnSend.setDisable(false);
        Station.main.btnConn.setText("종료");
    });
  }

  public void duplicated() {  
      Platform.runLater(()-> {
          Station.login.setLabel("중복된 닉네임입니다.");
      });
  }
  
  
//	/** [1] 서버에서 받은 유저명들을 리스트화 시킨다. **/
//	List<String> setListView(String data) {	
//
//		List<String> nameList = new Vector<String>();
//		
//		StringBuffer sb = new StringBuffer(data);
//		
//		data = sb.delete(0,"/list ".length()).toString();
//
//		StringTokenizer st = new StringTokenizer(data, " ");
//		
//		while(st.hasMoreTokens()) {
//			nameList.add(st.nextToken());
//		}
//
//		return nameList;
//	}
//	
//	/** [2] 서버로 부터 유저목록을 받아 화면에 출력한다. **/
//	public void printlistView(String namelist) {
//		
//		List<String> userList = setListView(namelist);
//		
//		ObservableList<String> nameList = FXCollections.observableList(userList);
//		
//		Platform.runLater(()-> {
//			Station.main.listView.setItems(nameList);
//		});	
//	}


/* -------------------------------------------------------------------------------------------------------------------------------------------------------------------------- */	
//	/** 샌드박스 체크 **/
//	public Boolean checkAll(String data) {
//		if(Station.main.sendBox.getValue().equals("전체")) {
//			return true;
//		} else {
//			// 귓속말 고정
//			sendStaticWhisper(data);
//			return false;
//		}
//	}
//	/** 샌드박스 추가 **/
//	public void addSendBoxName(String data) {
//		StringBuffer sb = new StringBuffer(data);
//		// [1] 식별자 제거
//		String name = sb.delete(0, "/w ".length()).toString();
//		
//		Platform.runLater(()->{
//			// [2] 유저명 추가
//			Station.main.sendBox.getItems().add(name);
//		});
//	}
//	/** 귓속말 고정 **/
//	public void sendStaticWhisper(String data) {
//		
//		Thread thread = new Thread() {
//			@Override
//			public void run() {
//				try {
//					String message = "/w " + Station.main.sendBox.getValue() + " " + data;
//					
//					Charset charset = Charset.forName("UTF-8");
//					ByteBuffer byteBuffer = charset.encode(message);
//					Env.socketChannel.write(byteBuffer);
//					
//				} catch(Exception e) {
//					Station.main.txtDisplay.appendText("전송 할 수 없습니다.\n");
//				}
//			}
//		};
//		thread.start();
//	}
/* -------------------------------------------------------------------------------------------------------------------------------------------------------------------------- */	
	
	
	
	
	
}
