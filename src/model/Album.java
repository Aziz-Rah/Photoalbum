package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Album implements Serializable {
	private String name;
	ArrayList<Photo> photos;
	
	public Album(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void addPhoto(Photo photo) {
		photos.add(photo);
	}
	
	public int getNumPhotos() {
		return photos.size();
	}
}
