package edu.ysu.onionuml.core;

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
		
		String paramStr = "(";
		for(int i=0; i < parameters.size(); ++i){
			UmlOperationParameter op = parameters.get(i);
			if(i == 0){
				paramStr += op.toString();
			}
			else{
				paramStr += ", " + op.toString();
			}
		}
		paramStr += ")";
		
		String str = visStr + name + paramStr;
		if(returnType != null && !returnType.equals("")){
			str += " : " + returnType;
		}
		
		return str;
	}
}
