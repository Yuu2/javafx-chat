package Template;
import java.net.URL;
import java.util.ResourceBundle;

import Function.Sender;
import Model.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/* -------------------------------------------------------------------------------------------------------------------------------------------------------------------------- */
/***** 공지사항 컨트롤러 *****/
public class ctrNotice implements Initializable {
	
@FXML private Button btnSend;
@FXML private TextField ntInput;
	
public Stage stage;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnSend.setOnAction(e-> {

			play(ntInput.getText());
			ntInput.clear();
			stage = (Stage) btnSend.getScene().getWindow();
			stage.close();
		});
	}
	
/* -------------------------------------------------------------------------------------------------------------------------------------------------------------------------- */
	/** 공지사항 스테이지 초기화 **/
	public void set(Stage stage) {
		this.stage = stage;
	}
	
	/** 공지사항 생성 **/
	public void play(String data) {						
		
		Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				Sender s = new Sender();
				s.sendItem(data);
				return null;
			}
		};
		Service.executorService.submit(task);
	}
}
