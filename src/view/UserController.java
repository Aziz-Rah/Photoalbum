package view;

import java.util.ArrayList;

import app.PhotoAlbum;
import model.Album;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.User;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class UserController {
	@FXML Button create;
	@FXML Button delete;
	@FXML Button rename;
	@FXML Button open;
	@FXML Button search;
	@FXML Button logout;
	@FXML Button quit;
	
	ArrayList<User> users = PhotoAlbum.getInstance().users;
	User user;
	ArrayList<Album> albums;
	
	public void start(Stage stage) {
		for(int i = 0; i < users.size(); i++) {
			if(users.get(i).isCurrentUser())
				user = users.get(i);
		}
		albums = user.getAlbums();
	}
	
	public void search() throws Exception {
		Stage stage = (Stage)search.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("/view/Search.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void logout() throws Exception {
		// serialize
		
		for(int i = 0; i < users.size(); i++) {
			if(users.get(i).isCurrentUser())
				users.get(i).deselectUser();
		}
		
		Stage stage = (Stage)logout.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void quit() {
		// serialize objects
		Stage stage = (Stage)quit.getScene().getWindow();
		stage.close();
	}
}
