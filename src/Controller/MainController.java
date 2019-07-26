package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Dummy.DummyController;
import View.Login;

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
	
	// 접속 버튼
	@FXML public Button btnConn;
	// 전송 버튼
	@FXML public Button btnSend;
	// 입력창
	@FXML public TextField txtInput;
	// 출력창
	@FXML public TextArea txtDisplay;
	// 유저 리스트
	@FXML public ListView<String> listView;
	// 귓속말 기능 
	@FXML public ComboBox<String> sendBox;
	// 채팅룸 대기방
	@FXML public Button btnWaitRoom;
	
	public Stage primaryStage;
	
	
	DummyController base;
	
/* -------------------------------------------------------------------------------------------------------------------------------------------------------------------------- */	
	@Override
	public void initialize(URL location, ResourceBundle resources) {	
		/**	 입력창 글자 수 제한 ( LIMIT 40 ) **/
		txtInput.lengthProperty().addListener(new ChangeListener<Number>() {		
	            @Override
	            public void changed(ObservableValue<? extends Number> observable,
	                    Number oldValue, Number newValue) {
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
		/**	 키보드 ENTER -> 전송 **/
		txtInput.setOnKeyPressed(new EventHandler<KeyEvent>() {						
		    @Override
		    public void handle(KeyEvent keyEvent) {
		        if (keyEvent.getCode() == KeyCode.ENTER)  {
		        	try {
		        	base.send(txtInput.getText());
		            txtInput.clear();
		        	} catch (Exception e) {}
		        }
		    }
		});
		/** 마우스클릭 -> 전송　**/
		btnSend.setOnAction(e-> {													
			base.send(txtInput.getText());
			txtInput.clear();
		});
	}
/* -------------------------------------------------------------------------------------------------------------------------------------------------------------------------- */	
	/**	 클라이언트 시작 or 종료 버튼 **/
	public void btnConnAction(ActionEvent e) {
		base = new DummyController();
		if(btnConn.getText().equals("접속")) {
			Login login = new Login();
			try {
				login.view();
			} catch (IOException ioe) {}
			
			base.startClient();
			
		} else if(btnConn.getText().equals("종료")) {
			txtInput.setDisable(true);
			btnSend.setDisable(true);
			listView.setItems(null);
			base.stopClient();
			btnConn.setText("접속");	
		}
	}
	/** 채팅룸 대기방 **/
	public void btnWaitRoomAction(ActionEvent e) throws IOException {
		
		Parent root = FXMLLoader.load(getClass().getResource("../ctrWaitRoom.fxml"));
		StackPane stage = (StackPane) btnWaitRoom.getScene().getRoot();
		stage.getChildren().add(root);
		
	}
	
	public void set(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
}
