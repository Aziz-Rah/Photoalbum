/**
 * Tag class
 * @author Amy Guinto
 * @author Aziz Rahman
 */
package model;

import java.io.Serializable;

public class Tag implements Serializable {
	private String type;
	private String value;
	/**
	 * Tag constructor initializes tag type-value pair
	 * @param type
	 * @param value
	 */
	public Tag(String type, String value) {
		this.type = type;
		this.value = value;
	}
	/**
	 * getType() is a getter for the tag type
	 * @return String
	 */
	public String getType() {
		return type;
	}
	/**
	 * getValue() is a getter for the tag value
	 * @return String
	 */
	public String getValue() {
		return value;
	}
	/**
	 * setType() is a setter for the tag type
	 * @param type
	 */
	public void setType(String type){
		this.type = type;
	}
	/**
	 * setVal() is a setter for the tag value
	 * @param val
	 */
	public void setVal(String val){
		this.value = val;
	}
	/**
	 * equalsType(String) checks if types of tags are equals
	 * @param type
	 * @return boolean
	 */
	public boolean equalsType(String type) {
		return type.equalsIgnoreCase(type);
	}
	/**
	 * equalsVal(String) checks if values of tags are equal
	 * @param val
	 * @return boolean
	 */
	public boolean equalsVal(String val){
		return value.equalsIgnoreCase(val);
	}
}
