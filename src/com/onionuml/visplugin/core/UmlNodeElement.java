package com.onionuml.visplugin.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a node element in a UML class model.
 */
public class UmlNodeElement {
	
	// PRIVATE MEMBER VARIABLES ------------------------------
	
	private String mName;
	private String mStereotype;
	private boolean mIsAbstract = false;
	
	private List<UmlNodeAttribute> mAttributes = new ArrayList<UmlNodeAttribute>();
	private List<UmlNodeOperation> mOperations = new ArrayList<UmlNodeOperation>();
	
	
	
	// PUBLIC METHODS --------------------------------------
	
	/**
	 * Constructs a new ModelNodeElement with a blank name
	 * and stereotype.
	 */
	public UmlNodeElement(){
		mName = "";
		mStereotype = "";
	}
	
	/**
	 * Constructs a new UmlNodeElement with the given name and blank stereotype.
	 * @param name name of the node
	 */
	public UmlNodeElement(String name){
		mName = name;
		mStereotype = "";
	}
	
	/**
	 * Constructs a new UmlNodeElement with the given name and stereotype.
	 * @param name name of the node
	 * @param stereotype stereotype of the node
	 */
	public UmlNodeElement(String name, String stereotype){
		mName = name;
		mStereotype = stereotype;
	}
	
	/**
	 * Sets the name of this node.
	 */
	public void setName(String name){
		mName = name;
	}
	
	/**
	 * Gets the name of this node.
	 */
	public String getName(){
		return mName;
	}
	
	/**
	 * Sets whether this node is abstract.
	 */
	public void setIsAbstract(boolean isAbstract){
		mIsAbstract = isAbstract;
	}
	
	/**
	 * Gets whether the node is abstract.
	 */
	public boolean getIsAbstract(){
		return mIsAbstract;
	}
	
	/**
	 * Sets the sterotype of this node to the specified stereotype.
	 */
	public void setStereotype(String stereotype){
		mStereotype = stereotype;
	}
	
	/**
	 * Gets a string specifying the stereotype of this node.
	 */
	public String getStereotype(){
		return mStereotype;
	}
	
	/**
	 * Adds the given attribute to the list of attributes for this node.
	 */
	public void addAttribute(UmlNodeAttribute a){
		mAttributes.add(a);
	}
	
	/**
	 * Returns a reference to the list of attributes in this node.
	 */
	public List<UmlNodeAttribute> getAttributes(){
		return mAttributes;
	}
	
	/**
	 * Adds the given operation to the list of operations for this node.
	 */
	public void addOperation(UmlNodeOperation o){
		mOperations.add(o);
	}
	
	/**
	 * Returns a reference to the list of operations in this node.
	 */
	public List<UmlNodeOperation> getOperations(){
		return mOperations;
	}
	
}
