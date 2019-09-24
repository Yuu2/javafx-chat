package Stage;
import java.io.IOException;

import Template.Station;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/* -------------------------------------------------------------------------------------------------------------------------------------------------------------------------- */
public class Notice  {
	/*** 공지사항 뷰 ***/
	public void view() throws IOException {
		Stage stage = new Stage();
		stage.setTitle("공지사항");
		stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/notice.png")));
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../ctrNotice.fxml"));
		Parent root = (Parent) loader.load();
		
		Station.ctr2 = loader.getController();
		Station.ctr2.set(stage);
		
		Scene scene = new Scene(root);
		
		stage.setScene(scene);
		stage.setResizable(false);
		stage.sizeToScene();
		stage.show();
	}
}
