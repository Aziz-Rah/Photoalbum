/**
 * User class
 * @author Amy Guinto
 * @author Aziz Rahman
 */

package model;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
	String name;
	ArrayList<Album> albums;
	Album selectedAlbum;
	boolean currentUser;
	
	/**
	 * User constructor initializes name, ArrayList of Albums, and the boolean isCurrentUser
	 * @param name
	 */
	public User(String name) {
		this.name = name;
		albums = new ArrayList<Album>();
		currentUser = false;
	}
	
	/**
	 * addAlbum(Album) adds an album
	 * @param name
	 */
	public void addAlbum(Album name) {

		albums.add(name);
	}
	
	/**
	 * selectAlbum(int) gets the album at the specified index
	 * @param index
	 */
	public void selectAlbum(int index) {
		selectedAlbum =  albums.get(index);
	}
	
	/**
	 * getSelectedAlbum() returns the selected album
	 * @return selectedAlbum
	 */
	public Album getSelectedAlbum() {
		return selectedAlbum;
	}
	/**
	 * getAlbums() returns the ArrayList of Albums
	 * @return albums
	 */
	public ArrayList<Album> getAlbums() {
		return albums;
	}
	
	/**
	 * selectUser() selects the current user (only one user selected at a time)
	 */
	public void selectUser() {
		currentUser = true;
	}
	/**
	 * deselectUser() deselects the user
	 */
	public void deselectUser() {
		currentUser = false;
	}
	/**
	 * isCurrentUser() determines if the user is the current user
	 * @return currentUser, a boolean
	 */
	public boolean isCurrentUser() {
		return currentUser;
	}
	
	public String toString() {
		return name;
	}
}
