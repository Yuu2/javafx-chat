package Controller;

import java.io.IOException;

import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ResourceBundle;

import Config.Env;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/* -------------------------------------------------------------------------------------------------------------------------------------------------------------------------- */
public class LoginContoller implements Initializable {
	
	// 로그인 창
	public Stage stage;
	
	@FXML // 메시지 라벨
	private Label lbLogin;
	@FXML // 입력창
	public TextField loginInput;
	@FXML // 확인 버튼
	public Button btnLogin;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		/** 닉네임 중복검사 요청 **/
		btnLogin.setOnAction(event->{
			// JavaFX UI 스레드상에서 작업 스레드로 이전
			Task<Void> task = new Task<Void>() {
				@Override
				protected Void call() throws Exception {
					try {
						
						String data = "/name " + loginInput.getText();
						
						// 공백 포함시 취소
						if(loginInput.getText().contains(" ")) {	
							Platform.runLater(()-> {
								lbLogin.setStyle(
										"-fx-text-fill:red;"
										+ "-fx-font-size:13;");
								lbLogin.setText("공백이 포함되어서는 안됩니다.");	
							});	
							return null;	
						}

						// 입력창 비우기
						Platform.runLater(()-> {
							loginInput.clear();
						});
						// 서버로 전송
						Charset charset = Charset.forName("UTF-8");
						ByteBuffer byteBuffer = charset.encode(data);
						Env.socketChannel.write(byteBuffer);
						
					}catch(IOException ioe) {	
						Platform.runLater(()-> {
							lbLogin.setStyle(
									"-fx-text-fill:red;"
									+ "-fx-font-size:13;");
							lbLogin.setText("서버가 연결되어 있지 않습니다.");	
						});	
					}
					return null;
				}
			};
			Thread thread = new Thread(task);
			thread.start();
		});
		
		/**	 입력창 글자 수 제한 ( LIMIT 12 ) **/
		loginInput.lengthProperty().addListener(new ChangeListener<Number>() {		
			
	            @Override
	            public void changed(ObservableValue<? extends Number> observable,
	                    Number oldValue, Number newValue) {
	            	final int LIMIT = 12;
	                if (newValue.intValue() > oldValue.intValue()) {
	                    if (loginInput.getText().length() >= LIMIT) {
	                    	StringBuffer sb = new StringBuffer(loginInput.getText());
	                    	sb.delete(0, LIMIT).toString();
	                    	loginInput.setText(loginInput.getText().substring(0, LIMIT));
	                   }
	               }
	           }
	    });	
	}
	
	/** 컨트롤러 초기화 **/
	public void set(Stage stage) {
		this.stage = stage;
	}
	
	/** 메시지 라벨 설정 **/
	public void setLabel(String text) {
		lbLogin.setText(text);
	}
}
