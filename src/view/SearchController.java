package view;

import java.io.IOException;
import java.util.ArrayList;

import app.PhotoAlbum;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class SearchController {

	Stage mainStage;

	@FXML Button logout;
	@FXML Button quit;
	@FXML Button back;

	@FXML TextField fieldA;
	@FXML Button createAlbum;
	@FXML Button search;

	@FXML DatePicker startDate;
	@FXML DatePicker endDate;
	@FXML TextField tagType;
	@FXML TextField tagVal;

	@FXML ListView<Photo> list = new ListView<Photo>();
	@FXML ImageView img;

	ArrayList<User> users = PhotoAlbum.getInstance().users;
	ObservableList<Photo> items = FXCollections.observableArrayList();
	Album album;
	User user;

	public void start(Stage stage){

		mainStage = stage;
		for(int i = 0; i < users.size(); i++) {
			if(users.get(i).isCurrentUser()){
				user = users.get(i);
				album = users.get(i).getSelectedAlbum();
				break;
			}
		}
		
		refresh();
		
	}

	public void refresh(){

		if (items != null)
			items.clear();
		for (Photo p : album.photos){

			items.add(p);
		}
		list.setItems(items);

		list.setCellFactory(param -> new ListCell<Photo>() {
			private ImageView imageView = new ImageView();
			@Override
			public void updateItem(Photo p, boolean empty){
				super.updateItem(p, empty);
				if (empty) {
					setText(null);
					setGraphic(null);
				} else {
					imageView.setFitHeight(50);
					imageView.setFitWidth(50);
					imageView.setImage(p.image);
					setText(p.getCaption());
					setGraphic(imageView);
				}
			}
		});
		list.getSelectionModel().select(0);
		itemSelected(mainStage);

		list
		.getSelectionModel()
		.selectedItemProperty()
		.addListener(
				(obs, oldVal, newVal) -> 
				itemSelected(mainStage));

		mainStage.show();
	}

	public void search(){
		
	}
	
	public void create() throws Exception {

		String a = fieldA.getText().toLowerCase();
		for (int i = 0; i < users.size(); i++){
			if (users.get(i).isCurrentUser()){
				Album album = new Album(fieldA.getText().toLowerCase());
				album.setName(a);
				users.get(i).addAlbum(album);
				break;
			}
		}		
	}

	public void back() throws IOException{
		
		Stage stage;
		AnchorPane root;
		stage = (Stage)back.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/User.fxml"));
		root = (AnchorPane)loader.load();
		UserController userController = loader.getController();
		userController.start(stage);
		Scene scene = new Scene(root);
		stage.setScene(scene);
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

	private void itemSelected(Stage mainStage) { 

		if (items.size() > 0){

			int index = list.getSelectionModel().getSelectedIndex();
			if (index >= 0) {	
				img.setImage(items.get(index).image);
			}

		}
	}
}
