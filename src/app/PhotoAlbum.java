package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
//import view.Controller;

public class PhotoAlbum extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {                
		FXMLLoader loader = new FXMLLoader();   
		loader.setLocation(getClass().getResource("/view/Login.fxml"));
		
		AnchorPane root = (AnchorPane)loader.load();

		//Controller controller = loader.getController();
		//controller.start(primaryStage);

		Scene scene = new Scene(root, 400, 600);
		primaryStage.setTitle("Photo Album");
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show(); 
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
