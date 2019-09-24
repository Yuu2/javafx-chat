
import Template.Station;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/*** 메인 클래스 ***/
public class Main extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		// FXML로더 호출
		FXMLLoader loader =  new FXMLLoader(getClass().getResource("ctrBase.fxml"));
		Parent root = loader.load();
		// 컨트롤러 정적변수. 어느 곳에서나 JAVA UI 참조 할 수 있게 해준다. 
		Station.ctr0 = loader.getController();
		Station.ctr0.set(primaryStage);
		
		Scene scene = new Scene(root);
		
		// 사이즈 재조정 불가능.
		primaryStage.setResizable(false);
		// 무대에 장면을 매개값으로 세팅
		primaryStage.setScene(scene);
		// 화면 창에 맞게 재설정
		primaryStage.sizeToScene();
		primaryStage.setOnCloseRequest(e->{
			if(Station.ctr1!=null) {Station.ctr1.stage.close();} 
			if(Station.ctr2!=null) {Station.ctr2.stage.close();} 
			if(Station.ctr3!=null) {Station.ctr3.stage.close();}
		});
		// 타이틀
		primaryStage.setTitle("채팅 서버");
		// 아이콘
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("Images/ico.png")));
		primaryStage.show();
	}

	@Override
	/** 종료되기 직전에 호출되는 stop 메소드 **/
	public void stop() {
 		System.exit(0);
	}
/* -------------------------------------------------------------------------------------------------------------------------------------------------------------------------- */	
	public static void main(String[] args) {
		launch(args);
	}
}

