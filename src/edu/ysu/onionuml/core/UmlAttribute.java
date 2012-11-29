package edu.ysu.onionuml.core;

/**
 * Represents an attribute in a UML class diagram.
 */
public class UmlAttribute {
	public Visibility visibility;
	public String name;
	public String dataType;
	
	public UmlAttribute(Visibility visability, String name, String dataType){
		this.visibility = visability;
		this.name = name;
		this.dataType = dataType;
	}
	
	@Override
	public String toString(){
		
		String visStr = "";
		switch(visibility){
			case PRIVATE:
				visStr = "- ";
				break;
			case PROTECTED:
				visStr = "# ";
				break;
			case PUBLIC:
				visStr = "+ ";
				break;
		}
		String str = visStr + name;
		if(dataType != null && !dataType.equals("")){
			str += " : " + dataType;
		}
		
		return str;
	}
}
