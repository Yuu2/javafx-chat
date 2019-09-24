package Stage;
import java.io.IOException;

import Template.Station;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/* -------------------------------------------------------------------------------------------------------------------------------------------------------------------------- */
public class Config  {
	/*** 설정 뷰 ***/
	public void view() throws IOException {
		Stage stage = new Stage();
		stage.setTitle("설정");
		stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/ico.png")));
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../ctrConfig.fxml"));
		Parent root = (Parent) loader.load();
		
		Station.ctr1 = loader.getController();
		Station.ctr1.set(stage);
		
		Scene scene = new Scene(root);
		
		stage.setScene(scene);
		stage.setResizable(false);
		stage.sizeToScene();
		stage.show();
	}
}
