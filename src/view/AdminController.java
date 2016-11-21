package view;

import java.io.IOException;

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
	
	private ObservableList<User> obsList = FXCollections.observableArrayList();
	
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
		obsList.add(new User(field.getText()));
		stage.close();
		
	}
	
	public void cancel(){
		Stage stage = (Stage)cancel.getScene().getWindow();
		stage.close();
	}
	
	public void okD(){
	
		Stage stage = (Stage)okD.getScene().getWindow();
		for (int i = 0; obsList.get(i) != null; i++){
			if (obsList.get(i).toString().equals(user.getText()))
				obsList.remove(i);
		}
		stage.close();
	}
<<<<<<< HEAD
}
=======
}
>>>>>>> bfea11279b463d34fdf1a5ac5c8b3bb79a1af174
