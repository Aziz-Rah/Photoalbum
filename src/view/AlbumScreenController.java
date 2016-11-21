package view;

import java.io.IOException;
import java.util.ArrayList;

import app.PhotoAlbum;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.scene.Scene;
import model.*;

public class AlbumScreenController {

	Stage mainStage;

	//AlbumScreen
	@FXML Button add; 
	@FXML Button create;
	@FXML Button remove;
	@FXML Button slideshow;
	@FXML Button edit;
	@FXML Button logout;
	@FXML Button quit;
	@FXML TextField caption1;
	@FXML TextField caption2;
	@FXML ImageView img1;
	@FXML ListView<Album> list = new ListView<Album>();
	//How to get the album that is passed to this window
	
	ArrayList<User> users = PhotoAlbum.getInstance().users;
	private Album album;
	
	public void start(Stage stage){

		mainStage = stage;
		
		for(int i = 0; i < users.size(); i++) {
			if(users.get(i).isCurrentUser()){
				album = users.get(i).getSelectedAlbum();
				break;
			}
		}

        list.setCellFactory(param -> new ListCell<Album>() {
            private ImageView imageView = new ImageView();
            @Override
            public void updateItem(Album photo, boolean empty) {
                super.updateItem(photo, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                	for (int i = 0; album.photos.get(i) != null; i++){
                		imageView.setImage(album.photos.get(i).image);
                		setGraphic(imageView);
                	}
                }
            }
        });
        VBox box = new VBox(list);
        box.setAlignment(Pos.CENTER);
        Scene scene = new Scene(box, 200, 200);
        mainStage.setScene(scene);
        mainStage.show();
	}
	
	public void logout() throws IOException{
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
}