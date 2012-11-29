package edu.ysu.onionuml.core;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a UML package as a collection of classes.
 */
public class UmlPackageElement {
	
	private String mName;
	private Map<String,UmlClassElement> mClasses =
			new HashMap<String,UmlClassElement>();
	
	
	/**
	 * Constructs a new package element with the specified non-null name.
	 */
	public UmlPackageElement(String name){
		if(name == null){
			throw new InvalidParameterException();
		}
		mName = name;
	}
	
	/**
	 * Constructs a new package element from the specified parameters.
	 * @param name name of the package, may not be null
	 * @param classes an existing map of class ids to classes
	 */
	public UmlPackageElement(String name, Map<String,UmlClassElement> classes){
		if(name == null){
			throw new InvalidParameterException();
		}
		mName = name;
		mClasses = classes;
	}
	
	/**
	 * Returns the name of the package.
	 */
	public String getName(){
		return mName;
	}
	
	/**
	 * Sets the name to the specified non-null string.
	 */
	public void setName(String name){
		if(name == null){
			throw new InvalidParameterException();
		}
		mName = name;
	}
	
	/**
	 * Gets a reference to the map of classes in the package.
	 */
	public Map<String,UmlClassElement> getClasses(){
		return mClasses;
	}
	
	/**
	 * Adds the specified class element with the specified class id to the
	 * package's collection of classes.
	 */
	public void addClass(String id, UmlClassElement c){
		if(id == null || c == null){
			throw new InvalidParameterException();
		}
		mClasses.put(id, c);
	}
}
