package com.onionuml.visplugin.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an operation in a UML class diagram.
 */
public class UmlOperation {
	public Visibility visibility;
	public String name;
	public String returnType;
	public boolean isStatic;
	public boolean isAbstract;
	public List<UmlOperationParameter> parameters;
	
	public UmlOperation(Visibility visibility, String name, String returnType,
			boolean isStatic, boolean isAbstract){
		this.visibility = visibility;
		this.name = name;
		this.returnType = returnType;
		this.isStatic = isStatic;
		this.isAbstract = isAbstract;
		parameters = new ArrayList<UmlOperationParameter>();
	}
}
