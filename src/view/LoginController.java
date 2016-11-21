package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import app.PhotoAlbum;
import model.User;
import java.util.ArrayList;

public class LoginController {
	@FXML Button login;
	@FXML Button quit;
	@FXML TextField username;
	@FXML Label errormsg;
	
	ArrayList<User> users = PhotoAlbum.getInstance().users;
	
	public void login() throws Exception {
		String user = username.getText().toLowerCase();
		Stage stage;
		Parent root;
		if(user.equals("admin")) {
			stage = (Stage)login.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("/view/Admin.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} else {
			boolean isUser = false;
			
			for(int i = 0; i < users.size(); i++) {
				if(username.equals(users.get(i).toString()))
					isUser = true;
			}
			
			if(isUser) {
				// go to user screen
			} else {
				errormsg.setOpacity(1);
			}
		}
	}
	
	public void quit() {
		// serialize objects
		Stage stage = (Stage)quit.getScene().getWindow();
		stage.close();
	}
}
