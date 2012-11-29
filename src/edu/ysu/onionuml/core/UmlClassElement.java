package edu.ysu.onionuml.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a class element in a UML class model.
 */
public class UmlClassElement {
	
	// PRIVATE MEMBER VARIABLES ------------------------------
	
	private String mName;
	private String mStereotype;
	private boolean mIsAbstract = false;
	private Visibility mVisibility = Visibility.PUBLIC;
	
	private List<UmlAttribute> mAttributes = new ArrayList<UmlAttribute>();
	private List<UmlOperation> mOperations = new ArrayList<UmlOperation>();
	
	
	
	// PUBLIC METHODS --------------------------------------
	
	/**
	 * Constructs a new UmlClassElement with a blank name
	 * and stereotype.
	 */
	public UmlClassElement(){
		mName = "";
		mStereotype = "";
	}
	
	/**
	 * Constructs a new UmlClassElement with the given name and blank stereotype.
	 * @param name name of the class
	 */
	public UmlClassElement(String name){
		mName = name;
		mStereotype = "";
	}
	
	/**
	 * Constructs a new UmlClassElement with the given name and stereotype.
	 * @param name name of the class
	 * @param stereotype stereotype of the class
	 */
	public UmlClassElement(String name, String stereotype){
		mName = name;
		mStereotype = stereotype;
	}
	
	/**
	 * Sets the name of this class.
	 */
	public void setName(String name){
		mName = name;
	}
	
	/**
	 * Gets the name of this class.
	 */
	public String getName(){
		return mName;
	}
	
	/**
	 * Sets whether this class is abstract.
	 */
	public void setIsAbstract(boolean isAbstract){
		mIsAbstract = isAbstract;
	}
	
	/**
	 * Gets whether the class is abstract.
	 */
	public boolean getIsAbstract(){
		return mIsAbstract;
	}
	
	/**
	 * Sets the sterotype of this class to the specified stereotype.
	 */
	public void setStereotype(String stereotype){
		mStereotype = stereotype;
	}
	
	/**
	 * Gets a string specifying the stereotype of this class.
	 */
	public String getStereotype(){
		return mStereotype;
	}
	
	/**
	 * Adds the given attribute to the list of attributes for this class.
	 */
	public void addAttribute(UmlAttribute a){
		mAttributes.add(a);
	}
	
	/**
	 * Returns a reference to the list of attributes in this class.
	 */
	public List<UmlAttribute> getAttributes(){
		return mAttributes;
	}
	
	/**
	 * Adds the given operation to the list of operations for this class.
	 */
	public void addOperation(UmlOperation o){
		mOperations.add(o);
	}
	
	/**
	 * Returns a reference to the list of operations in this class.
	 */
	public List<UmlOperation> getOperations(){
		return mOperations;
	}
	
	/**
	 * Gets the visibility of the class.
	 */
	public Visibility getVisibility(){
		return mVisibility;
	}
	
	/**
	 * Sets the visibility of the class.
	 */
	public void setVisibility(Visibility v){
		mVisibility = v;
	}
	
	@Override
	public String toString(){
		String eol = System.getProperty("line.separator");
		String str = mName + " : ";
		
		switch(mVisibility){
			case PRIVATE:
				str += "private";
				break;
			case PROTECTED:
				str += "protected";
				break;
			case PUBLIC:
				str += "public";
				break;
		}
		
		if(mIsAbstract){
			str += ", abstract";
		}
		
		if(mStereotype != null && mStereotype.length() > 0){
			str += ", <<" + mStereotype + ">>";
		}
		
		str += eol + "Attributes:" + eol;
		if(mAttributes.size() == 0){
			str += "  (none)" + eol;
		}
		else{
			for(UmlAttribute a : mAttributes){
				str += a.toString() + eol;
			}
		}
		
		str += "Operations:";
		if(mOperations.size() == 0){
			str += eol + "  (none)";
		}
		else{
			for(UmlOperation o : mOperations){
				str += eol + o.toString();
			}
		}
		
		return str;
	}
}
