package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class LoginController {
	@FXML Button login;
	@FXML Button quit;
	@FXML TextField username;
	@FXML Label errormsg;
	
	public void login() throws Exception {
		String user = username.getText();
		Stage stage;
		Parent root;
		if(user.equals("admin")) {
			stage = (Stage)login.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("/view/Admin.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} else {
			// if user -> go to user screen
			// else --> enable error message
		}
	}
	
	public void quit() {
		// serialize objects
		Stage stage = (Stage)quit.getScene().getWindow();
		stage.close();
	}
}
