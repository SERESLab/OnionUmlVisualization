package com.onionuml.visplugin.core;

/**
 * Represents the visibility of a node attribute or operation.
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
}
