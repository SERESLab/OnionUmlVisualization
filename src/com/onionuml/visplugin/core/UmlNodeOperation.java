package com.onionuml.visplugin.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an operation of a node in a UML class diagram.
 */
public class UmlNodeOperation {
	public Visibility visibility;
	public String name;
	public String returnType;
	public boolean isStatic;
	public boolean isAbstract;
	public List<UmlNodeOperationParameter> parameters;
	
	public UmlNodeOperation(Visibility visibility, String name, String returnType,
			boolean isStatic, boolean isAbstract){
		this.visibility = visibility;
		this.name = name;
		this.returnType = returnType;
		this.isStatic = isStatic;
		this.isAbstract = isAbstract;
		parameters = new ArrayList<UmlNodeOperationParameter>();
	}
}
