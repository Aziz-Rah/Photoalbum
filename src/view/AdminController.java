/**
 * AdminController
 * @author Aziz Rahman
 * @author Amy Guinto
 */
package view;

import java.io.IOException;
import java.util.ArrayList;

import app.PhotoAlbum;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import model.*;

public class AdminController {
	@FXML Button back;
	@FXML Button quit;
	@FXML Button add;
	@FXML Button delete;
	@FXML TextField field2;
	
	//Add User Pop-Up
	@FXML Button ok;
	@FXML Button cancel;
	@FXML TextField field;
	
	//Delete User Pop-Up
	@FXML Button okD;
	@FXML Text user;
	
	@FXML ListView<String> listView;
	
	ArrayList<User> users = PhotoAlbum.getInstance().users;
	ObservableList<String> obsList = FXCollections.observableArrayList("admin");	
	/**
	 * start(Stage stage) overrides method
	 * @param stage
	 */
	public void start(Stage stage) {	
		// for debugging
		
		for(int i = 0; i < users.size(); i++) {
			obsList.add(users.get(i).toString());
		}
		users.add(new User("admin"));
		if(obsList.size() > 0) {
			listView.setItems(obsList);
			listView.getSelectionModel().select(0);
		}
		
		//obsList.add("test");

		listView.setItems(obsList);
		listView.getSelectionModel().select(0);
		
	}
	/**
	 * back() returns to login screen
	 * @throws Exception
	 */
	public void back() throws Exception {
		/*
		Stage stage = (Stage)back.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		*/
		
		Stage stage = (Stage)back.getScene().getWindow();
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/Login.fxml"));
		
		AnchorPane root = (AnchorPane)loader.load();
		
		LoginController loginController = loader.getController();
		//loginController.start(stage);
		Scene scene = new Scene(root);
		stage.setScene(scene);
	}
	/**
	 * quit() terminates the application
	 */
	public void quit() {
		// serialize objects
		Stage stage = (Stage)quit.getScene().getWindow();
		stage.close();
	}
	/**
	 * add() adds a user and updates the listview
	 * @throws IOException
	 */
	public void add() throws IOException{
		
		String s = field2.getText().toLowerCase();
		users.add(new User(s));
		obsList.add(field2.getText().toLowerCase());
		listView.setItems(obsList);
		
		
		/*Stage stage;
		Parent root;
		stage = new Stage();
		root = FXMLLoader.load(getClass().getResource("AddUserPopUp.fxml"));
		stage.setScene(new Scene(root));
		stage.setTitle("Add User");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initOwner(add.getScene().getWindow());
        stage.showAndWait();
        */
	}
	/**
	 * delete() deletes the selected user and updates the listview
	 * @throws IOException
	 */
	public void delete() throws IOException{
		
		for (int i = 0; i < users.size(); i++){
			if (obsList.get(i).toString().equals(field2.getText().toLowerCase())){

				users.remove(i);
				obsList.remove(i);
				break;
			}
		}
		listView.setItems(obsList);
		
	}
		
	/*	Stage stage;
		Parent root;
		stage = new Stage();
		root = FXMLLoader.load(getClass().getResource("DeleteUserPopUp.fxml"));
		stage.setScene(new Scene(root));
		stage.setTitle("Delete User");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initOwner(okD.getScene().getWindow());
        stage.showAndWait();
		
		
	}
	
	public void ok(){
		Stage stage = (Stage)ok.getScene().getWindow();
		users.add(new User(field.getText().toLowerCase()));
		for(int i = 0; i < users.size(); i++) {
			System.out.println("User: " + users.get(i));
		}
		
		obsList.add(field.getText().toLowerCase());
		
		for(int i = 0; i < obsList.size(); i++) {
			System.out.println("from list: " + obsList.get(i).toString());
		}

		if(obsList.size() > 0){
			if (listView == null)
				System.out.println("LISTVIEW IS NULL");
			listView.setItems(obsList);
		}
		stage.close();

	}
	
	public void cancel(){
		Stage stage = (Stage)cancel.getScene().getWindow();
		stage.close();
	}
	
	public void okD(){
	
		Stage stage = (Stage)okD.getScene().getWindow();
		for (int i = 0; i < users.size(); i++){
			if (users.get(i).toString().equals(user.getText()))
				users.remove(i);
		}
		stage.close();
	} */
}
