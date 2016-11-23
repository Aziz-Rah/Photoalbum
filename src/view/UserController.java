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
import javafx.scene.layout.AnchorPane;

public class UserController {
	@FXML Button create;
	@FXML Button delete;
	@FXML Button rename;
	@FXML Button open;
	@FXML Button search;
	@FXML Button logout;
	@FXML Button quit;
	@FXML TextField field;
	@FXML ListView<String> listView;
	
	ArrayList<User> users = PhotoAlbum.getInstance().users;
	User user;
	ArrayList<Album> albums;
	
	ObservableList<String> list = FXCollections.observableArrayList();	
	
	public void start(Stage stage) {
		
		for(int i = 0; i < users.size(); i++) {
			if(users.get(i).isCurrentUser()){
				user = users.get(i);
			}
		}
		albums = user.getAlbums();
		
		String s;
		for(int i = 0; i < albums.size(); i++) { //only needed for serialization
			// get strings and put them in listview
			s = albums.get(i).getName() + "\n" + albums.get(i).photos.size() + " Photos";
			list.add(s);
			
		}
		
		listView.setItems(list);
		listView.getSelectionModel().select(0);
	}
	
	public void delete(){
		
		int index = listView.getSelectionModel().getSelectedIndex();
		albums.remove(index);
		refresh();
	}
	
	public void open() throws IOException{
		
		int index = listView.getSelectionModel().getSelectedIndex();
		user.selectAlbum(index);
		Stage stage;
		AnchorPane root;
		stage = (Stage)open.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/AlbumScreen.fxml"));
		root = (AnchorPane)loader.load();
		AlbumScreenController asc = loader.getController();
		asc.start(stage);
		Scene scene = new Scene(root);
		stage.setScene(scene);
	}
	
	public void rename(){
		
		int index = listView.getSelectionModel().getSelectedIndex();
		albums.get(index).setName(field.getText().toLowerCase());
		refresh();
	}
	
	
	public void refresh(){
		String s;
		list.clear();
		for(int i = 0; i < albums.size(); i++) { 
			// get strings and put them in listview
			s = albums.get(i).getName() + "\n" + albums.get(i).photos.size() + " Photos";
			list.add(s);
		}
		listView.setItems(list);
	}
	
	public void search() throws Exception {
		
		Stage stage;
		AnchorPane root;
		stage = (Stage)open.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/Search.fxml"));
		root = (AnchorPane)loader.load();
		SearchController searchController = loader.getController();
		searchController.start(stage);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		
		/*Stage stage = (Stage)search.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("/view/Search.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show(); */
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
		
		Album album = new Album(field.getText().toLowerCase());
		album.setName(field.getText().toLowerCase());
		user.addAlbum(album);
		//list.add(album.getName());
		refresh();
	}
	
	public void quit() {
		// serialize objects
		Stage stage = (Stage)quit.getScene().getWindow();
		stage.close();
	}
}
