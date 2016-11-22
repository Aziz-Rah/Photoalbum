package model;

import java.io.Serializable;
import javafx.scene.image.Image;
import java.util.ArrayList;

public class Photo implements Serializable {
	public Image image;
	private String caption;
	public String date;
	public ArrayList<Tag> tags;
	
	public Photo(Image image) {
		this.image = image;
	}
	
	public void addCaption(String caption) {
		this.caption = caption;
	}
	
	public void editCaption(String caption) {
		this.caption = caption;
	}
	
	public String getCaption(){
		return caption;
	}
	
	public boolean addTag(Tag tag) {
		for(Tag t: tags) {
			if(tag.equals(t))
				return false;
		}
		
		tags.add(tag);
		return true;
	}
}
