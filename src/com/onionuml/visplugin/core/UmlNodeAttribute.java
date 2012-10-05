package com.onionuml.visplugin.core;

/**
 * Represents an attribute of a node in a UML class diagram.
 */
public class UmlNodeAttribute {
	public Visibility visibility;
	public String name;
	public String dataType;
	
	public UmlNodeAttribute(Visibility visability, String name, String dataType){
		this.visibility = visability;
		this.name = name;
		this.dataType = dataType;
	}
}
