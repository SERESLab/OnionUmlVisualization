package edu.ysu.onionuml.core;

/**
 * Represents the type of relationship between two elements in a
 * UML class model.
 */
public enum RelationshipType {
	AGGREGATION,
	ASSOCIATION,
	DIRECTEDASSOCIATION,
	COMPOSITION,
	DEPENDENCY,
	REALIZATION,
	GENERALIZATION;
	
	/**
	 * Returns the RelationshipType represented by the specified string.
	 */
	public static RelationshipType parseRelationshipType(String s) {
        if(s.equalsIgnoreCase("aggregation")){
        	return AGGREGATION;
        }
        else if(s.equalsIgnoreCase("association")){
        	return ASSOCIATION;
        }
        else if(s.equalsIgnoreCase("directedAssociation")){
        	return DIRECTEDASSOCIATION;
        }
        else if(s.equalsIgnoreCase("composition")){
        	return COMPOSITION;
        }
        else if(s.equalsIgnoreCase("dependency")){
        	return DEPENDENCY;
        }
        else if(s.equalsIgnoreCase("realization")){
        	return REALIZATION;
        }
        else if(s.equalsIgnoreCase("generalization")){
        	return GENERALIZATION;
        }
        
        return null;
    }
	
	@Override
	public String toString(){
		if(this == DIRECTEDASSOCIATION){
			return "directedAssociation";
		}
		else{
			return super.toString().toLowerCase();
		}
	}
}
