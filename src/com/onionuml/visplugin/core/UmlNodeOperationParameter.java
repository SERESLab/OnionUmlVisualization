package com.onionuml.visplugin.core;

/**
 * Represents a parameter to an operation of a node in a UML
 * class diagram.
 */
public class UmlNodeOperationParameter {
	public String name;
	public String dataType;
	
	public UmlNodeOperationParameter(String name, String dataType){
		this.name = name;
		this.dataType = dataType;
	}
}
