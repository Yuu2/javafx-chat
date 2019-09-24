package Template;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Function.Base;
import Function.Log;
import Function.LogWriter;
import Function.UserList;
import Model.Service;
import Stage.Blacklist;
import Stage.Config;
import Stage.Notice;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
/* -------------------------------------------------------------------------------------------------------------------------------------------------------------------------- */
public class BaseContoller implements Initializable {

	// 서버시작 or 서버종료 버튼
	@FXML public Button btnStartStop;
	// 새로고침 버튼
	@FXML public Button btnRefresh;
	// 공지사항 버튼
	@FXML public Button btnNotice;
	// 설정 버튼
	@FXML public Button btnConfig;
	// IP차단 버튼
	@FXML public Button btnBlock;
	
	// 출력창 : 디스플레이
	@FXML public TextArea txtDisplay;
	// 출력창 : 스레드 수
	@FXML public TextField threadSize;
	// 출력창 : 접속자 수
	@FXML public TextField clientSize;
	
	// 메인 스테이지
	public Stage primaryStage;
	// 서버 구동 클래스
	private Base base;
	
/* -------------------------------------------------------------------------------------------------------------------------------------------------------------------------- */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		/***  유저목록 실시간 리스트 전달  ***/
		clientSize.textProperty().addListener(new ChangeListener<String>() {	
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// "" or 0이 아닐 때
				if(!newValue.equals("") || !newValue.equals("0")) {
					String data = "/list";
					UserList ul = new UserList();
					ul.set(data);
				}
			}
		});
	}
	/***  서버시작 or 서버종료 버튼  ***/
	public void btnStartStop(ActionEvent event) throws IOException {	
		base = new Base();
		if(btnStartStop.getText().equals("서버시작")) {			
				Log.custom("연결상태를 확인하오니 잠시만 기다려주세요 ... \n");
				base.startServer(); 		
				// 새로고침 버튼 활성화
				btnRefresh.setDisable(false);		
				// 공지 버튼 활성화
				btnNotice.setDisable(false);	
				// 공지 버튼 활성화
				btnBlock.setDisable(false);
				btnStartStop.setText("서버종료");								
		} else if(btnStartStop.getText().equals("서버종료")){     		
				// 접속자 수 초기화
				clientSize.setText("");									
				// 스레드 수 초기화
				threadSize.setText("");
				// 새로고침 버튼 비활성화
				btnRefresh.setDisable(true);
				// 설정 버튼 비활성화 
				btnConfig.setDisable(false);							
				// 공지사항 버튼 비활성화
				btnNotice.setDisable(true);								
				base.stopServer();				
				// 버튼 문자열 수정
				btnStartStop.setText("서버시작");					
		}
	}
	/***  새로고침 버튼 ***/
	public void btnRefresh(ActionEvent event) { 						
		updateStatus();
	}
	/***  새로고침 기능 ***/
	public void updateStatus() {
		Platform.runLater(()-> {
			// 스레드 수
			threadSize.setText(String.valueOf(Service.executorService.getPoolSize()));
			if(Service.connections!=null)
			// 접속자 수
			clientSize.setText(String.valueOf(Service.connections.size()));
		});
	}
	/***  공지사항 버튼 ***/
	public void btnNotice(ActionEvent event) throws IOException {	 		
		Notice notice = new Notice();
		notice.view();
	}	
	/***  IP차단 버튼 ***/
	public void btnBlock(ActionEvent event) throws IOException {
		Blacklist blacklist = new Blacklist();
		blacklist.view();
	}
	/***  설정 버튼 ***/
	public void btnConfig(ActionEvent event) throws IOException {
		Config config = new Config();
		config.view();
	}
	
	/***  로그 버튼 ***/ 
	public void btnLog(ActionEvent event) throws IOException {
		FileChooser extension = new FileChooser();
		
		extension.getExtensionFilters().add(
				new ExtensionFilter("Text Files", "*.txt")
		);
		// File 객체와 로그문 전체를 생성자 매개값으로 전달한다.
			File file = extension.showSaveDialog(primaryStage);	
		// File 객체가  null이 아닐 때 로그 저장.
		if(file!=null) {
			LogWriter log = new LogWriter(file, txtDisplay.getText());
			log.save();
		}
	}
/* -------------------------------------------------------------------------------------------------------------------------------------------------------------------------- */
	/*** 메인 스테이지 초기화 ***/
	public void set(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
}
