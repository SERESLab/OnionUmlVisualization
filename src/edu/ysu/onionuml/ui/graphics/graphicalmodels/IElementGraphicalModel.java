package edu.ysu.onionuml.ui.graphics.graphicalmodels;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;

/**
 * Defines an element that is part of a UML class diagram.
 * Methods defined here are shared by graphical models for
 * both Class Elements and Relationship Elements.
 */
public interface IElementGraphicalModel {
	
	/**
	 * Gets the current position of this element.
	 * 
	 * @return 			this element's location
	 * @see 			org.eclipse.draw2d.geometry.Point
	 */
	public Point getPosition();
	
	/**
	 * Gets the current size of this element.
	 * 
	 * @return 			this element's size
	 * @see 			org.eclipse.draw2d.geometry.Dimension
	 */
	public Dimension getSize();
	
	/**
	 * Sets the position of this element to the specified Point.
	 * 
	 * @param position	this element's location
	 * @see 			org.eclipse.draw2d.geometry.Point
	 */
	public void setPosition(Point position);
	
	/**
	 * Sets the size of this element to the specified Dimension.
	 * 
	 * @param size		this element's size
	 * @see 			org.eclipse.draw2d.geometry.Dimension
	 */
	public void setSize(Dimension size);
}
