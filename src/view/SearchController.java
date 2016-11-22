package view;

import java.util.ArrayList;

import app.PhotoAlbum;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SearchController {
	@FXML Button createalbum;
	@FXML Button logout;
	@FXML Button quit;
	@FXML TextField fieldA;
	
	ArrayList<User> users = PhotoAlbum.getInstance().users;
	
	public void create() throws Exception {
		
		String a = fieldA.getText().toLowerCase();
		for (int i = 0; i < users.size(); i++){
			if (users.get(i).isCurrentUser()){
				users.get(i).addAlbum(new Album(a));
				break;
			}
		}		
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
