package view;

import java.util.ArrayList;

import app.PhotoAlbum;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.User;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class SearchController {
	@FXML Button createalbum;
	@FXML Button logout;
	@FXML Button quit;
	
	ArrayList<User> users = PhotoAlbum.getInstance().users;
	
	public void create() throws Exception {
		Stage stage;
		Parent root;
		stage = new Stage();
		root = FXMLLoader.load(getClass().getResource("CreateAlbumPopUp.fxml"));
		stage.setScene(new Scene(root));
		stage.setTitle("Create Album");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initOwner(createalbum.getScene().getWindow());
        stage.showAndWait();
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
