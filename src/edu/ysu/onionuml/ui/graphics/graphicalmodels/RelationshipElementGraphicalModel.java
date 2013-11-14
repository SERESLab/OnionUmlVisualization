package edu.ysu.onionuml.ui.graphics.graphicalmodels;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;

import edu.ysu.onionuml.core.UmlRelationshipElement;

/**
 * Represents the model of a UML relationship between two elements.
 * This will be rendered with the Eclipse Graphical Editing Framework (GEF).
 */
public class RelationshipElementGraphicalModel implements
		IElementGraphicalModel {
	
	private Point mPosition;
	private Dimension mSize;
	private UmlRelationshipElement mRelationship;
	
	
	
	/**
	 * Constructs a new RelationshipElementGraphicalModel from the given
	 * UmlRelationshipElement object.
	 * 
	 * @param rel 	the UML Relationship Element object representing 
	 * 				the relationship 
	 * @see 		core.UmlRelationshipElement
	 */
	public RelationshipElementGraphicalModel(UmlRelationshipElement rel){
		mRelationship = rel;
		mPosition = new Point();
		mSize = new Dimension();
	}
	
	/**
	 * Gets a reference to the underlying UMLRelationshipElement object.
	 * 
	 * @return		associated UML Relationship Element
	 */
	public UmlRelationshipElement getRelationshipElement(){
		return mRelationship;
	}
	
	@Override
	public Point getPosition() {
		return mPosition;
	}

	@Override
	public Dimension getSize() {
		return mSize;
	}

	@Override
	public void setPosition(Point position) {
		mPosition = position;
	}

	@Override
	public void setSize(Dimension size) {
		mSize = size;
	}
}
