package Controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

import Service.Machine;
import Stage.Login;
import Util.Display;
import Util.MessageUtil;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/* -------------------------------------------------------------------------------------------------------------------------------------------------------------------------- */
public class MainController implements Initializable{
	
    public Stage primaryStage;
	
	// 접속 버튼
	@FXML public Button btnConn;
	// 전송 버튼
	@FXML public Button btnSend;
	// 채팅룸 이동
    @FXML public Button btnWaitRoom;
	// 입력창
	@FXML public TextField txtInput;
	// 출력창
	@FXML public TextArea txtDisplay;
	// 유저 리스트
	@FXML public ListView<String> listView;
	// 귓속말 기능 
	@FXML public ComboBox<String> sendBox;
	
	Machine machine;
	Properties props;

/* -------------------------------------------------------------------------------------------------------------------------------------------------------------------------- */	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
        
		/**	 입력창 글자 수 제한 ( LIMIT 40 ) **/
		txtInput.lengthProperty().addListener(new ChangeListener<Number>() {		
		  
		  @Override
	      public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
	              
	      final int LIMIT = 40;
	            	
	        if (newValue.intValue() > oldValue.intValue()) {
	          if (txtInput.getText().length() >= LIMIT) {
	            StringBuffer sb = new StringBuffer(txtInput.getText());
	            sb.delete(0, LIMIT).toString();
	            txtInput.setText(txtInput.getText().substring(0, LIMIT));
	          }
	        }
	      }
	    });
		
		/**	 전송 :  ENTER **/
		txtInput.setOnKeyPressed(new EventHandler<KeyEvent>() {						
		  @Override
		    public void handle(KeyEvent keyEvent) {
		      if (keyEvent.getCode() == KeyCode.ENTER)  {
		        try {
		          machine.send(txtInput.getText());
    		      txtInput.clear();
		        } catch (Exception e) {}
		      }
		    }
		});
		
		/** 전송 : CLICK　**/
		btnSend.setOnAction(e-> {													
			machine.send(txtInput.getText());
			txtInput.clear();
		});
	}
/* -------------------------------------------------------------------------------------------------------------------------------------------------------------------------- */
	/**	클라이언트 구동 
	 * @throws Exception **/
	public void btnConnAction(ActionEvent event) throws Exception {
		machine = new Machine();
		
		if(btnConn.getText().equals("접속")) {
			
		  Login login = new Login();
			try {
				System.out.print("start");
				login.view();
				
			} catch (Exception e) { 
				
					
			  Display.add(MessageUtil.trans("login.fxml.fail") + " : " + e.toString());
			  return; 
			}
			
			machine.start();	
		}
		
		if(btnConn.getText().equals("종료")) {
		  
			txtInput.setDisable(true);
			btnSend.setDisable(true);
			listView.setItems(null);
			machine.shutdown();
			btnConn.setText("접속");	
		}
	}
	/** 채팅룸 대기방 **/
	public void btnWaitRoomAction(ActionEvent event) throws IOException {
		
		Parent root = FXMLLoader.load(getClass().getResource("../ctrWaitRoom.fxml"));
		
		StackPane stage = (StackPane) btnWaitRoom.getScene().getRoot();
		
		stage.getChildren().add(root);
	}
	
	public void set(Stage primaryStage) {
	  
		this.primaryStage = primaryStage;
	}
}
