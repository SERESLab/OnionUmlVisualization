package edu.ysu.onionuml.core;

/**
 * Represents a relationship between two elements in a UML class model.
 */
public class UmlRelationshipElement {
	
	/**
	 * Represents the multiplicity of elements on the ends of a relationship.
	 */
	public enum Multiplicity{
		NONE,
		ZERO,
		ONE,
		MANY;
		
		/**
		 * Returns the Multiplicity represented by the specified string, or
		 * NONE if the string is not recognized.
		 */
		public static Multiplicity parseMultiplicity(String s) {
			if(s.equals("0")){
	        	return ZERO;
	        } else if(s.equals("1")){
	        	return ONE;
	        } else if(s.equals("*")){
	        	return MANY;
	        } return NONE;
	    }
		
		@Override
		public String toString(){
			if(this == ZERO){
				return "0";
			} else if(this == ONE){
				return "1";
			} else if(this == MANY){
				return "*";
			} return "none";
		}
	}
	
	
	
	
	// PRIVATE MEMBER VARIABLES --------------------
	
	private String mLabel;
	private String mHeadId;
	private String mTailId;
	private Multiplicity mHeadMultiplicityMin;
	private Multiplicity mHeadMultiplicityMax;
	private Multiplicity mTailMultiplicityMin;
	private Multiplicity mTailMultiplicityMax;
	private RelationshipType mType;
	
	
	
	// PUBLIC METHODS ---------------------------------
	
	/**
	 * Constructs a new relationship element with the specified parameters and 
	 * endpoint element multiplicities of NONE.
	 * @param label the label to be displayed on the relationship, may be null
	 * @param headId the unique id of the head element in the relationship
	 * @param tailId the unique id of the tail element in the relationship
	 * @param type the type of relationship
	 */
	public UmlRelationshipElement(String label, String headId,
			String tailId, RelationshipType type){
		
		mLabel = (label != null ? label : "");
		mHeadId = headId;
		mTailId = tailId;
		mType = type;
		mHeadMultiplicityMin = Multiplicity.NONE;
		mHeadMultiplicityMax = Multiplicity.NONE;
		mTailMultiplicityMin = Multiplicity.NONE;
		mTailMultiplicityMax = Multiplicity.NONE;
	}
	
	/**
	 * Constructs a new relationship element with the specified parameters.
	 * @param label the label to be displayed on the relationship, may be null
	 * @param headId the unique id of the head element in the relationship
	 * @param tailId the unique id of the tail element in the relationship
	 * @param type the type of relationship
	 * @param headMultiplicityMin the minimum multiplicity of the head element
	 * in the relationship
	 * @param headMultiplicityMax the maximum multiplicity of the head element
	 * in the relationship
	 * @param tailMultiplicityMin the minimum multiplicity of the tail element
	 * in the relationship
	 * @param tailMultiplicityMax the maximum multiplicity of the tail element
	 * in the relationship
	 */
	public UmlRelationshipElement(String label, String headId, String tailId,
			RelationshipType type, Multiplicity headMultiplicityMin,
			Multiplicity headMultiplicityMax, Multiplicity tailMultiplicityMin,
			Multiplicity tailMultiplicityMax){
		
		mLabel = (label != null ? label : "");
		mHeadId = headId;
		mTailId = tailId;
		mType = type;
		mHeadMultiplicityMin = headMultiplicityMin;
		mHeadMultiplicityMax = headMultiplicityMax;
		mTailMultiplicityMin = tailMultiplicityMin;
		mTailMultiplicityMax = tailMultiplicityMax;
	}
	
	/**
	 * Returns the label to be displayed on the relationship.
	 */
	public String getLabel(){
		return mLabel;
	}
	
	/**
	 * Sets the label to be displayed on the relationship. May be null.
	 */
	public void setLabel(String label){
		mLabel = (label != null ? label : "");
	}
	
	/**
	 * Returns the id of the head element in the relationship.
	 */
	public String getHeadId(){
		return mHeadId;
	}
	
	/**
	 * Sets the id of the head element in the relationship.
	 */
	public void setHeadId(String headId){
		mHeadId = headId;
	}
	
	/**
	 * Returns the id of the tail element in the relationship.
	 */
	public String getTailId(){
		return mTailId;
	}
	
	/**
	 * Sets the id of the tail element in the relationship.
	 */
	public void setTailId(String tailId){
		mTailId = tailId;
	}
	
	/**
	 * Gets the type of relationship element.
	 */
	public RelationshipType getType(){
		return mType;
	}
	
	/**
	 * Sets the type of the relationship element to the specified type.
	 */
	public void setType(RelationshipType type){
		mType = type;
	}
	
	/**
	 * Returns the minimum multiplicity of the head element in the relationship
	 */
	public Multiplicity getHeadMultiplicityMin(){
		return mHeadMultiplicityMin;
	}
	
	/**
	 * Sets the minimum multiplicity of the head element in the relationship
	 */
	public void setHeadMultiplicityMin(Multiplicity m){
		mHeadMultiplicityMin = m;
	}
	
	/**
	 * Returns the maximum multiplicity of the head element in the relationship
	 */
	public Multiplicity getHeadMultiplicityMax(){
		return mHeadMultiplicityMax;
	}
	
	/**
	 * Sets the maximum multiplicity of the head element in the relationship
	 */
	public void setHeadMultiplicityMax(Multiplicity m){
		mHeadMultiplicityMax = m;
	}
	
	/**
	 * Returns the minimum multiplicity of the tail element in the relationship
	 */
	public Multiplicity getTailMultiplicityMin(){
		return mTailMultiplicityMin;
	}
	
	/**
	 * Sets the minimum multiplicity of the tail element in the relationship
	 */
	public void setTailMultiplicityMin(Multiplicity m){
		mTailMultiplicityMin = m;
	}
	
	/**
	 * Returns the maximum multiplicity of the tail element in the relationship
	 */
	public Multiplicity getTailMultiplicityMax(){
		return mTailMultiplicityMax;
	}
	
	/**
	 * Sets the maximum multiplicity of the tail element in the relationship
	 */
	public void setTailMultiplicityMax(Multiplicity m){
		mTailMultiplicityMax = m;
	}
}
