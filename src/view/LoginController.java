package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
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
		AnchorPane root;
		if(user.equals("admin")) {
			stage = (Stage)login.getScene().getWindow();
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/Admin.fxml"));
			root = (AnchorPane)loader.load();
			AdminController adminController = loader.getController();
			adminController.start(stage);
			Scene scene = new Scene(root);
			stage.setScene(scene);
		} else {
			boolean isUser = false;
			
			for(int i = 0; i < users.size(); i++) {
				if(user.equals(users.get(i).toString())) {
					isUser = true;
					users.get(i).selectUser();
				}
			}
			
			if(isUser) {
				stage = (Stage)login.getScene().getWindow();
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/view/User.fxml"));
				root = (AnchorPane)loader.load();
				UserController userController = loader.getController();
				userController.start(stage);
				Scene scene = new Scene(root);
				stage.setScene(scene);
				/*FXMLLoader loader = new FXMLLoader();
				root = FXMLLoader.load(getClass().getResource("/view/User.fxml"));
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show(); */
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
