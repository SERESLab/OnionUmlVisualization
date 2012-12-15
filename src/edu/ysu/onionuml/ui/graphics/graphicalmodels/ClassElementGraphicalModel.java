package edu.ysu.onionuml.ui.graphics.graphicalmodels;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;

import edu.ysu.onionuml.core.UmlClassElement;
import edu.ysu.onionuml.core.UmlPackageElement;
import edu.ysu.onionuml.ui.graphics.IEventListener;
import edu.ysu.onionuml.ui.graphics.IEventRegistrar;

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
	
	private UmlPackageElement mPackageElement;
	private UmlClassElement mClassElement;
	private Point mPosition;
	private Dimension mSize;
	private boolean mIsCompacted;
	
	private IEventListener mListener;
	
	
	// PUBLIC METHODS --------------------------------------
	
	/**
	 * Contructs a new ClassGraphicalModel from the given class and package.
	 */
	public ClassElementGraphicalModel(UmlClassElement c, UmlPackageElement p){
		mClassElement = c;
		mPosition = new Point();
		mSize = new Dimension();
		mPackageElement = p;
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
	
	/**
	 * Gets a reference to the underlying UmlPackageElement to which the class belongs.
	 */
	public UmlPackageElement getPackageElemet(){
		return mPackageElement;
	}
	
	/**
	 * Returns the compaction status of the class element.
	 */
	public boolean isCompacted(){
		return mIsCompacted;
	}
	
	/**
	 * Sets whether the class element is compacted.
	 */
	public void setIsCompacted(boolean isCompacted){
		mIsCompacted = isCompacted;
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
