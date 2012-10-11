package com.onionuml.visplugin.core;

/**
 * Represents an attribute in a UML class diagram.
 */
public class UmlAttribute {
	public Visibility visibility;
	public String name;
	public String dataType;
	
	public UmlAttribute(Visibility visability, String name, String dataType){
		this.visibility = visability;
		this.name = name;
		this.dataType = dataType;
	}
}
