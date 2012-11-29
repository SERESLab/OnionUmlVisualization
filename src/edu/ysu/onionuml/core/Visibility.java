package edu.ysu.onionuml.core;

/**
 * Represents the visibility of an element in a UML class diagram.
 */
public enum Visibility {
	PUBLIC,
	PROTECTED,
	PRIVATE;
	
	/**
	 * Returns the Visibility represented by the specified string. If the
	 * string is not one of (case-insensitive): "public", "protected", or
	 * "private", PRIVATE is returned by default.
	 */
	public static Visibility parseVisibility(String s) {
        if(s.equalsIgnoreCase("public")){
        	return PUBLIC;
        }
        else if(s.equalsIgnoreCase("protected")){
        	return PROTECTED;
        }
        return PRIVATE;
    }
	
	@Override
	public String toString(){
		return super.toString().toLowerCase();
	}
}
