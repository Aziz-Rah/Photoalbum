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
		currentUser = false;
	}
	
	public boolean addAlbum(Album name) {
		boolean add = true;
		for(int i = 0; i < albums.size(); i++) {
			if(name.equals(albums.get(i).getName()))
				add = false;
		}
		
		if(add) {
			albums.add(name);
			return true;
		}
		
		return false;
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
