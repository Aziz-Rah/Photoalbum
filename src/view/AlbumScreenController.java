package view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import app.PhotoAlbum;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.scene.Scene;
import model.*;

public class AlbumScreenController {

	Stage mainStage;

	@FXML Button copy;
	@FXML Button move;
	@FXML Button add; 
	@FXML Button remove;
	
	@FXML Button choose;
	@FXML ImageView imgdisplay;
	
	@FXML Button editCaption;
	@FXML Button editTag;
	
	@FXML Button logout;
	@FXML Button quit;
	@FXML Button left;
	@FXML Button right;

	@FXML TextField photoDetails;
	@FXML TextField txtCaption;
	@FXML ImageView img;
	@FXML ListView<String> list = new ListView<String>();
	
	@FXML Text albumname;
	
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

        ObservableList<String> items =FXCollections.observableArrayList();
        for (Photo p : album.photos){
        	
        	items.add(p.getCaption());
        }
        list.setItems(items);
		
        list.setCellFactory(param -> new ListCell<String>() {
            private ImageView imageView = new ImageView();
            @Override
            public void updateItem(String caption, boolean empty){
                super.updateItem(caption, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                	
                	for (int i = 0; i < album.getNumPhotos(); i++){
                		Photo photo = album.photos.get(i);
                		
                		/* Java Reflection
                		Field field = null;
                		
                		try {
							field = photo.getClass().getDeclaredField(caption);
						} catch (NoSuchFieldException | SecurityException e) {
							e.printStackTrace();
						}
                		
                		try {
							 photo = (Photo)field.get(photo);
						} catch (IllegalArgumentException | IllegalAccessException e) {
							e.printStackTrace();
						}
                		*/
                		
                		/*if (caption.equals(photo.getCaption())){
                			imageView.setImage(photo.image);
                			setGraphic(imageView);
                			setText(photo.getCaption());
                			break;
                		}*/
                			
                	}    	
                }
            }
        });
       /* VBox box = new VBox(list);
        box.setAlignment(Pos.CENTER);
        Scene scene = new Scene(box, 200, 200);
        mainStage.setScene(scene);
        mainStage.show(); */
        
        
        
        
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
	
	public void add() throws Exception {
		Stage stage;
		Parent root;
		stage = new Stage();
		root = FXMLLoader.load(getClass().getResource("AddPhoto.fxml"));
		stage.setScene(new Scene(root));
		stage.setTitle("Add Photo");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initOwner(add.getScene().getWindow());
        stage.showAndWait();
	}
	
	public void choose() {
		FileChooser fileChooser = new FileChooser();
		
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        
        File file = fileChooser.showOpenDialog(null);
        
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
			imgdisplay.setImage(image);
        } catch (IOException ex) {
            Logger.getLogger(AlbumScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
	
	public void remove(){
		
		int index = list.getSelectionModel().getSelectedIndex();
		album.photos.remove(index);
		
	}
	
	public void editCaption(){
		
		int index = list.getSelectionModel().getSelectedIndex();
		album.photos.get(index).editCaption(txtCaption.getText());
		
	}
	
	public void quit() {
		// serialize objects
		Stage stage = (Stage)quit.getScene().getWindow();
		stage.close();	
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
	
	private void itemSelected(Stage mainStage) { 
				
		int index = list.getSelectionModel().getSelectedIndex();
		String caption = album.photos.get(index).getCaption();
		String date = album.photos.get(index).date;
		
		if (index >= 0) {	
			photoDetails.setText(caption+"\n"+date+"\n");
			for (Tag t: album.photos.get(index).tags){
				
				photoDetails.appendText(t.getType() + ", " + t.getValue());
			}
		}
	}
}