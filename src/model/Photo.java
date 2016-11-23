/**
 * Photo class
 * @author Amy Guinto
 * @author Aziz Rahman
 */
package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.Calendar;

public class Photo implements Serializable {
	public Image image;
	private String caption;
	public LocalDate date;
	public ArrayList<Tag> tags;
	private Calendar cal;
	
	/**
	 * Photo constructor adds the image, creates the ArrayList of Tags, and sets the date
	 * @param image
	 */
	public Photo(Image image) {
		this.image = image;
		tags = new ArrayList<Tag>();
		this.cal = Calendar.getInstance();
		this.cal.set(Calendar.MILLISECOND,0);
		this.date = cal.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
	
	/**
	 * datesStr() is a getter for the date
	 * @return String
	 */
	public String dateStr(){
		DateTimeFormatter d = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		return date.format(d);
	}
	
	/**
	 * isBetween(LocalDate, LocalDate) determines if the photo is between two specified dates
	 * @param min, the start date
	 * @param max, the end date
	 * @return boolean
	 */
	public boolean isBetween(LocalDate min, LocalDate max){
		return (date.isAfter(min) && date.isBefore(max)) || date.equals(min) || date.equals(max);
	}
	
	/**
	 * addCaption(String) adds a caption
	 * @param caption
	 */
	public void addCaption(String caption) {
		this.caption = caption;
	}
	
	/**
	 * editCaption(String) edits the caption
	 * @param caption
	 */
	public void editCaption(String caption) {
		this.caption = caption;
	}
	
	/**
	 * getCaption() is a getter for the caption
	 * @return String
	 */
	public String getCaption(){
		return caption;
	}
	
	/**
	 * addTag(Tag) adds a tag and returns true on successful add
	 * @param tag
	 * @return boolean
	 */
	public boolean addTag(Tag tag) {
		for(Tag t: tags) {
			if(tag.equals(t))
				return false;
		}
		
		tags.add(tag);
		return true;
	}
	
	public void deleteTag(Tag tag){
		for (Tag t : tags){
			if (tag.getType().equals(t.getType()))
				tags.remove(t);
			else if (tag.getValue().equals(t.getValue()))
				tags.remove(t);
		}
	}
}
