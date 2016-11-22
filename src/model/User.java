package model;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
	String name;
	ArrayList<Album> albums;
	Album selectedAlbum;
	boolean currentUser;
	
	public User(String name) {
		this.name = name;
		albums = new ArrayList<Album>();
		currentUser = false;
	}
	
	public void addAlbum(Album name) {

		albums.add(name);
	}
	
	public void selectAlbum(int index) {
		selectedAlbum =  albums.get(index);
	}
	
	public Album getSelectedAlbum() {
		return selectedAlbum;
	}
	
	public ArrayList<Album> getAlbums() {
		return albums;
	}
	
	public void selectUser() {
		currentUser = true;
	}
	
	public void deselectUser() {
		currentUser = false;
	}
	
	public boolean isCurrentUser() {
		return currentUser;
	}
	
	public String toString() {
		return name;
	}
}
