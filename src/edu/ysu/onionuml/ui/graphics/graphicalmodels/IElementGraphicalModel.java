package edu.ysu.onionuml.ui.graphics.graphicalmodels;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;

/**
 * Defines an element that is part of a UML class diagram.
 */
public interface IElementGraphicalModel {
	
	/**
	 * Gets the current position of the element.
	 */
	public Point getPosition();
	
	/**
	 * Gets the current size of the element.
	 */
	public Dimension getSize();
	
	/**
	 * Sets the position of the element to the specified point.
	 */
	public void setPosition(Point position);
	
	/**
	 * Sets the size of the element to the specified size.
	 */
	public void setSize(Dimension size);
}
