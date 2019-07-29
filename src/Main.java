
import Util.Station;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
/* -------------------------------------------------------------------------------------------------------------------------------------------------------------------------- */

/***** 메인 클래스 *****/
public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Style/fxml/Main.fxml"));	
		Parent root = loader.load();
		Station.main= loader.getController();
		Station.main.set(primaryStage);
		Scene scene = new Scene(root);	
		
		primaryStage.sizeToScene();
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.setTitle("채팅 클라이언트");

		
		primaryStage.show();  
	}

	@Override
	public void stop() throws Exception {
		System.exit(0);
	}
	
/* -------------------------------------------------------------------------------------------------------------------------------------------------------------------------- */
	public static void main(String[] args) {
		launch(args);
	}
}
