package edu.ysu.onionuml.core;

/**
 * Represents a parameter to an operation in a UML class diagram.
 */
public class UmlOperationParameter {
	public String name;
	public String dataType;
	
	public UmlOperationParameter(String name, String dataType){
		this.name = name;
		this.dataType = dataType;
	}
	
	@Override
	public String toString(){
		if(dataType != null && !dataType.equals("")){
			return name + " : " + dataType;
		}
		
		return name;
	}
}
