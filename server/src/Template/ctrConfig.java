package Template;
import java.net.URL;
import java.util.ResourceBundle;

import Model.Service;
import Model.SysInfo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/* -------------------------------------------------------------------------------------------------------------------------------------------------------------------------- */
/***** 설정 컨트롤러 *****/
public class ctrConfig implements Initializable {


@FXML private Stage config;						
@FXML private Button btnOk;
@FXML private Button btnNo;

@FXML private TextField fxSERVER_IP;
@FXML private TextField fxSERVER_PORT;
@FXML private TextField fxDB_IP;
@FXML private TextField fxDB_PORT;
@FXML private TextField fxDB_NAME;
@FXML private TextField fxDB_ID;
@FXML private TextField fxDB_PW;
@FXML private ChoiceBox<String> fxBuffer;

public Stage stage;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/** 확인 버튼 클릭시 매개값 전달 **/
		btnOk.setOnAction((event)-> { 
			try {
				// SERVER IP
				SysInfo.setSERVER_IP( fxSERVER_IP.getText());
				// SERVER PORT
				SysInfo.setSERVER_PORT(fxSERVER_PORT.getText());
				// DB IP
				SysInfo.setDB_IP(fxDB_IP.getText());
				// DB PORT
				SysInfo.setDB_PORT(fxDB_PORT.getText());
				// DB NAME
				SysInfo.setDB_NAME(fxDB_NAME.getText());
				// DB ID
				SysInfo.setDB_ID(fxDB_ID.getText());
				// DB PW
				SysInfo.setDB_PW(fxDB_PW.getText());
				
				// BYTE BUFFER
				String byteBuffer = fxBuffer.getValue();
				Service.bufferSize = Integer.parseInt(byteBuffer);
				
				// 설정 화면을 종료
				config = (Stage) btnOk.getScene().getWindow();
				config.close();
				
			} catch (Exception e) {}
		});
		/** 취소 버튼 클릭시 종료**/
		btnNo.setOnAction((event)-> {
			config = (Stage) btnNo.getScene().getWindow();
			config.close();
		});
		
	}
	/** 설정 스테이지 초기화 **/
	public void set(Stage stage) {
		this.stage = stage;
	}
/* -------------------------------------------------------------------------------------------------------------------------------------------------------------------------- */
}
