package Stage;

import java.io.IOException;

import Util.Station;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Login  {
/* -------------------------------------------------------------------------------------------------------------------------------------------------------------------------- */
  public void view() throws IOException {
	System.out.print(0);
		
    Stage stage = new Stage();	
	stage.setTitle("로그인 화면");
	stage.getIcons().add(new Image(getClass().getResourceAsStream("../../Style/img/ico.png")));
	System.out.print(3);
	FXMLLoader loader = new FXMLLoader(getClass().getResource("../Template/fxml/Login.fxml"));
	Parent root = (Parent) loader.load();
	Station.login = loader.getController();
	Station.login.set(stage);
			
	Scene scene = new Scene(root);
			
	stage.setScene(scene);
	stage.setResizable(false);
	stage.show();	
  }
}
