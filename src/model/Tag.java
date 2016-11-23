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
	
	public void setType(String type){
		this.type = type;
	}
	
	public void setVal(String val){
		this.value = val;
	}
	
	public boolean equalsType(String type) {
		return type.equalsIgnoreCase(type);
	}
	
	public boolean equalsVal(String val){
		return value.equalsIgnoreCase(val);
	}
}
