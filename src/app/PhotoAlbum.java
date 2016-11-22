package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import model.User;
import view.LoginController;

public class PhotoAlbum extends Application implements Serializable {
	public ArrayList<User> users;
	public ArrayList<String> storeFiles;
	
	static private PhotoAlbum instance = new PhotoAlbum();
	static public PhotoAlbum getInstance() { return instance; }
	
	public static final String storeDir = "dat";
	public static final String storeFile = "users.dat";
	
	public PhotoAlbum() { 
		users = new ArrayList<User>();
		//storeFiles = new ArrayList<String>();
	}
	
	public static void writeApp(PhotoAlbum app) throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream(storeDir + File.separator + storeFile));
		oos.writeObject(app);
	}
	
	public static PhotoAlbum readApp() throws IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(
				new FileInputStream(storeDir + File.separator + storeFile));
		PhotoAlbum app = (PhotoAlbum)ois.readObject();
		return app;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {                
		FXMLLoader loader = new FXMLLoader();   
		loader.setLocation(getClass().getResource("/view/Login.fxml"));
		
		AnchorPane root = (AnchorPane)loader.load();
		
		LoginController loginController = loader.getController();
		//LoginController.start(primaryStage);

		Scene scene = new Scene(root, 600, 400);
		primaryStage.setTitle("Photo Album");
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show(); 
	}
	
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		//PhotoAlbum app = PhotoAlbum.readApp();
		launch(args);
	}
}
