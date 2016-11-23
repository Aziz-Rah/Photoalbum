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
	
	public Photo(Image image) {
		this.image = image;
		tags = new ArrayList<Tag>();
		this.cal = Calendar.getInstance();
		this.cal.set(Calendar.MILLISECOND,0);
		this.date = cal.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
	
	public String dateStr(){
		DateTimeFormatter d = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		return date.format(d);
	}
	
	public boolean isBetween(LocalDate min, LocalDate max){
		return (date.isAfter(min) && date.isBefore(max)) || date.equals(min) || date.equals(max);
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
