/**
 * AlbumScreenController class
 * @author Aziz Rahman
 * @author Amy Guinto
 */
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
import javafx.scene.layout.AnchorPane;
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
	@FXML TextField fieldCap;
	@FXML TextField fieldTag;
	@FXML TextField newAlbum;
	
	@FXML TextField preT;
	@FXML TextField preV;
	@FXML TextField newType;
	@FXML TextField newVal;
	
	@FXML Button editCaption;
	@FXML Button editTag;
	@FXML Button deleteTag;
	@FXML Button addTag;
	
	@FXML Button logout;
	@FXML Button quit;
	@FXML Button back;

	@FXML TextField photoDetails;
	@FXML TextField txtCaption;
	@FXML ImageView img;
	@FXML ListView<Photo> list = new ListView<Photo>();

	@FXML Label albumName;

	ArrayList<User> users = PhotoAlbum.getInstance().users;
	ObservableList<Photo> items = FXCollections.observableArrayList();

	private Album album;
	private User user;
	/**
	 * start(Stage) overrides method
	 * @param stage
	 */
	public void start(Stage stage){

		mainStage = stage;
		for(int i = 0; i < users.size(); i++) {
			if(users.get(i).isCurrentUser()){
				user = users.get(i);
				album = users.get(i).getSelectedAlbum();
				break;
			}
		}

		albumName.setText(album.getName());

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
	/**
	 * move() moves the selected photo to the specified album
	 */
	public void move(){

		String s = newAlbum.getText().toLowerCase();
		int index = list.getSelectionModel().getSelectedIndex();
		Photo p = items.get(index);

		for (int i = 0; i < users.size(); i++){
			if (s.equals(user.getAlbums().get(i).getName())){
				user.getAlbums().get(i).addPhoto(p);
				remove();
				break;
			}
		}
	}
	/**
	 * back() returns to the user overview screen
	 * @throws IOException
	 */
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
	/**
	 * copy() copies the photo to the specified album
	 */
	public void copy(){

		String s = newAlbum.getText().toLowerCase();
		int index = list.getSelectionModel().getSelectedIndex();
		Photo p = items.get(index);

		for (int i = 0; i < users.size(); i++){
			if (s.equals(user.getAlbums().get(i).getName())){
				user.getAlbums().get(i).addPhoto(p);
				break;
			}
		}
	}

	/*public void add() throws Exception {
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
	 */
	/**
	 * editTag() updates the tag of the selected photo
	 */
	public void editTag(){
	
		String oldType = preT.getText().toLowerCase();
		String oldVal = preV.getText().toLowerCase();
		String type = newType.getText().toLowerCase();
		String val = newVal.getText().toLowerCase();
		int index = list.getSelectionModel().getSelectedIndex();
		ArrayList<Tag> tag = items.get(index).tags;
		for (Tag t : tag){
			if (oldType.equals(t.getType()))
				t.setType(type);
			if (oldVal.equals(t.getValue()))
				t.setVal(val);
		}
		refresh();
	}
	/**
	 * addTag() adds a tag to the selected photo
	 */
	public void addTag(){
		
		String type = newType.getText().toLowerCase();
		String val = newVal.getText().toLowerCase();
		int index = list.getSelectionModel().getSelectedIndex();
		Photo p = items.get(index);
		p.addTag(new Tag(type,val));
		refresh();

	}
	/**
	 * deleteTag() deletes the tag of the selected photo
	 */
	public void deleteTag(){
		
		String type = preT.getText().toLowerCase();
		String val = preV.getText().toLowerCase();
		int index = list.getSelectionModel().getSelectedIndex();
		Photo p = items.get(index);
		Tag tag = new Tag(type,val);
		
		for (Tag t : p.tags){
			if (tag.getType().equals(t.getType()))
				items.get(index).tags.remove(t);
			else if (tag.getValue().equals(t.getValue()))
				items.get(index).tags.remove(t);
		}
		
	//	p.deleteTag(tag);
		refresh();
	}
	/**
	 * refresh() updates the scene to reflect any changes
	 */
	public void refresh(){

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
	/**
	 * add() adds a photo to the album selected by the user through a filechooser
	 */
	public void add() {
		FileChooser fileChooser = new FileChooser();

		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

		File file = fileChooser.showOpenDialog(null);

		try {
			BufferedImage bufferedImage = ImageIO.read(file);
			Image image = SwingFXUtils.toFXImage(bufferedImage, null);
			img.setImage(image);
			Photo p = new Photo(image);
			String tagT,tagL;
			if (!fieldTag.getText().equals("")){
				String text = fieldTag.getText().toLowerCase();
				tagT = text.substring(0,text.indexOf(' '));
				tagL = text.substring(text.indexOf(' ')+1);
				if (!tagL.equals(tagT))
					p.addTag(new Tag(tagT,tagL));
			}
			if (!fieldCap.getText().equals("")){
				p.addCaption(fieldCap.getText().toLowerCase());
			}
			album.photos.add(p);

		} catch (IOException ex) {
			Logger.getLogger(AlbumScreenController.class.getName()).log(Level.SEVERE, null, ex);
		}

		refresh();
	}
	/**
	 * remove() removes the selected photo
	 */
	public void remove(){

		int index = list.getSelectionModel().getSelectedIndex();
		album.photos.remove(index);
		refresh();
		try {
			if (album.photos.get(0) != null)
				img.setImage(album.photos.get(0).image);
		}
		catch (IndexOutOfBoundsException e){
			Image none = null;
			img.setImage(none);
		}
	}
	/**
	 * editCaption() edits the caption of the selected photo
	 */
	public void editCaption(){

		int index = list.getSelectionModel().getSelectedIndex();
		album.photos.get(index).editCaption(txtCaption.getText().toLowerCase());
		refresh();

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
	 * logout() returns to the login screen
	 * @throws IOException
	 */
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

		if (items.size() > 0){

			int index = list.getSelectionModel().getSelectedIndex();
			String caption = album.photos.get(index).getCaption();
			String date = album.photos.get(index).dateStr();
			if (date == null)
				date = "No set date";

			if (index >= 0) {	
				photoDetails.setText("Caption: " + caption +" \n  "+ "Date: " + date +" \n ");
				for (Tag t: album.photos.get(index).tags){

					photoDetails.appendText("\n Tags: " + t.getType() + ", " + t.getValue());
				}
				img.setImage(items.get(index).image);
			}

		}
	}
}