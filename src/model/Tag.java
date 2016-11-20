package model;

import java.io.Serializable;

public class Tag implements Serializable {
	private String type;
	private String value;
	
	public Tag(String type, String value) {
		this.type = type;
		this.value = value;
	}
	
	public String getType() {
		return type;
	}
	
	public String getValue() {
		return value;
	}
	
	public boolean equals(Tag tag) {
		return type.equals(tag.getType()) && value.equals(tag.getValue());
	}
}
