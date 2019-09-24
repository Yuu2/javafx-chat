package Stage;

import java.io.IOException;

import Template.Station;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
/* -------------------------------------------------------------------------------------------------------------------------------------------------------------------------- */
public class Blacklist {

	/*** 블랙리스트 뷰 ***/
	public void view() throws IOException {
		
		Stage stage = new Stage();
		stage.setTitle("IP차단");
		stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/ico.png")));
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../ctrBlacklist.fxml"));
		Parent root = (Parent) loader.load();
		
		Station.ctr3 = loader.getController();
		Station.ctr3.set(stage);
		
		Scene scene = new Scene(root);
		
		stage.setScene(scene);
		stage.setResizable(false);
		stage.sizeToScene();
		stage.show();
	}

}
