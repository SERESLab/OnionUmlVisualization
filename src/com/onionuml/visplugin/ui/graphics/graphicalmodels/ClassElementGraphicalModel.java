package com.onionuml.visplugin.ui.graphics.graphicalmodels;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;

import com.onionuml.visplugin.core.UmlClassElement;
import com.onionuml.visplugin.ui.graphics.IEventListener;
import com.onionuml.visplugin.ui.graphics.IEventRegistrar;

/**
 * Represents the model of a uml class to be rendered with the Eclipse
 * Graphical Editing Framework (GEF).
 */
public class ClassElementGraphicalModel implements IElementGraphicalModel, IEventRegistrar {
	
	// PUBLIC MEMBER CONSTANTS ------------------------
	
	/**
	 * Event string specifying that the class's size has changed.
	 */
	public static final String EVENT_SIZE_CHANGED = "EVENT_SIZE_CHANGED";
	
	
	// PRIVATE MEMBER VARIABLES ---------------------------
	
	private UmlClassElement mClassElement;
	private Point mPosition;
	private Dimension mSize;
	
	private IEventListener mListener;
	
	
	// PUBLIC METHODS --------------------------------------
	
	/**
	 * Contructs a new ClassGraphicalModel from the given class.
	 */
	public ClassElementGraphicalModel(UmlClassElement c){
		mClassElement = c;
		mPosition = new Point();
		mSize = new Dimension();
	}
	
	@Override
	public void registerEventListener(IEventListener listener) {
		mListener = listener;
	}

	@Override
	public void unregisterEventListener() {
		mListener = null;
	}
	
	/**
	 * Gets a reference to the underlying UmlClassElement object.
	 */
	public UmlClassElement getClassElement(){
		return mClassElement;
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
	public void setPosition(Point position){
		mPosition = position;
	}
	
	@Override
	public void setSize(Dimension size){
		Dimension oldSize = mSize;
		mSize = size;
		
		if(!oldSize.equals(mSize) && mListener != null){
			mListener.eventOccured(EVENT_SIZE_CHANGED);
		}
	}
}
