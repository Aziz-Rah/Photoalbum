package view;

import java.io.IOException;
import java.util.ArrayList;

import app.PhotoAlbum;
import model.Album;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.User;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class UserController {
	@FXML Button create;
	@FXML Button delete;
	@FXML Button rename;
	@FXML Button open;
	@FXML Button search;
	@FXML Button logout;
	@FXML Button quit;
	@FXML Button okC;
	@FXML Button cancelC;
	@FXML TextField field;
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
	
	public void create() throws IOException{
		
		Stage stage;
		Parent root;
		stage = new Stage();
		root = FXMLLoader.load(getClass().getResource("CreateAlbumPopUp.fxml"));
		stage.setScene(new Scene(root));
		stage.setTitle("Create Album");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initOwner(create.getScene().getWindow());
        stage.showAndWait();
	}
	
	public void okC(){
		Stage stage = (Stage)okC.getScene().getWindow();
		Album album = new Album(field.getText());
		user.addAlbum(album);
		stage.close();
		
	}
	
	public void cancelC(){
		Stage stage = (Stage)cancelC.getScene().getWindow();
		stage.close();
	}
	
	public void quit() {
		// serialize objects
		Stage stage = (Stage)quit.getScene().getWindow();
		stage.close();
	}
}
