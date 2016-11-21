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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.*;

public class AdminController {
	@FXML Button back;
	@FXML Button quit;
	@FXML Button add;
	@FXML Button delete;
	
	//Add User Pop-Up
	@FXML Button ok;
	@FXML Button cancel;
	@FXML TextField field;
	
	//Delete User Pop-Up
	@FXML Button okD;
	@FXML Text user;
	
	ArrayList<User> users = PhotoAlbum.getInstance().users;
	
	public void back() throws Exception {
		Stage stage = (Stage)back.getScene().getWindow();
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
	
	public void add() throws IOException{
		
		Stage stage;
		Parent root;
		stage = new Stage();
		root = FXMLLoader.load(getClass().getResource("AddUserPopUp.fxml"));
		stage.setScene(new Scene(root));
		stage.setTitle("Add User");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initOwner(add.getScene().getWindow());
        stage.showAndWait();
	}
	
	public void delete() throws IOException{
		
		Stage stage;
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
	}
}
