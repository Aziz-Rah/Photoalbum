package view;

import java.util.ArrayList;

import app.PhotoAlbum;
import model.Album;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.User;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class UserController {
	@FXML Button create;
	@FXML Button delete;
	@FXML Button rename;
	@FXML Button open;
	@FXML Button search;
	@FXML Button logout;
	@FXML Button quit;
	@FXML ListView<String> listView;
	
	ArrayList<User> users = PhotoAlbum.getInstance().users;
	User user;
	ArrayList<Album> albums;
	
	ObservableList<String> list = FXCollections.observableArrayList();	
	
	public void start(Stage stage) {
		for(int i = 0; i < users.size(); i++) {
			if(users.get(i).isCurrentUser())
				user = users.get(i);
		}
		albums = user.getAlbums();
		String s;
		for(int i = 0; i < albums.size(); i++) {
			// get strings and put them in listview
			s = albums.get(i).getName() + "\n" + albums.get(i).photos.size() + " Photos";
			list.add(s);
		}
		
		listView.setItems(list);
		listView.getSelectionModel().select(0);
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
