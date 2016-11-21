package model;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
	String name;
	ArrayList<Album> albums;
	Album selectedAlbum;
	
	public User(String name) {
		this.name = name;
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
	
	public String toString() {
		return name;
	}
}
