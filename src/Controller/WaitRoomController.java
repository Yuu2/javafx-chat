package Controller;

import java.net.URL;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
/* -------------------------------------------------------------------------------------------------------------------------------------------------------------------------- */
public class WaitRoomController implements Initializable{
	
	@FXML 
	public Button btnBack;
	@FXML 
	private StackPane waitRoomStage;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		
	
		
		
		
	}
	
	/*** 대기방 -> 메인 ***/
	public void btnBackAction(ActionEvent e) {
	
		StackPane root = (StackPane) btnBack.getScene().getRoot();
		root.getChildren().remove(waitRoomStage);
	}	
}
