/**
 * Album class
 * @author Amy Guinto
 * @author Aziz Rahman
 */

package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Album implements Serializable {
	private String name;
	public ArrayList<Photo> photos;
	
	/**
	 * Album constructor initializes the name and creates an ArrayList of Photos
	 * @param name
	 */
	public Album(String name) {
		this.name = name;
		photos = new ArrayList<Photo>();
	}
	
	/**
	 * getName() is a getter for the album name
	 * @return String
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * setName() is a setter for the album name
	 * @param name
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * addPhoto(Photo) adds a photo to the ablum's ArrayList of Photos
	 * @param photo
	 */
	public void addPhoto(Photo photo) {
		photos.add(photo);
	}
	
	/**
	 * getNumPhotos() is a getter for the number of photos in the album
	 * @return int
	 */
	public int getNumPhotos() {
		return photos.size();
	}
	
	/**
	 * getAllTags() is a getter for the ArrayList of Tags
	 * @return ArrayList<Tag>
	 */
	public ArrayList<Tag> getAllTags(){
		ArrayList<Tag> list = new ArrayList<Tag>();
		for (Photo p : photos){
			for (Tag t : p.tags){
				list.add(t);
			}
		}
		return list;
	}
}
