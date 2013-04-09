package edu.ysu.onionuml.ui.graphics.graphicalmodels;

import java.util.List;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;

import edu.ysu.onionuml.core.RelationshipType;
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
	
	private List<RelationshipType> mChildRelationships;
	private UmlPackageElement mPackageElement;
	private UmlClassElement mClassElement;
	private Point mPosition;
	private Dimension mSize;
	private boolean mIsCompacted;
	private boolean mIsParentCompacted;
	private boolean mIsVisible = true;
	private boolean mIsHyper = false;
	private ClassElementGraphicalModel mActualHead = null;
	
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
	
	/**
	 * Sets whether this class element is a hyper class.
	 */
	public void setIsHyper(boolean isHyper, ClassElementGraphicalModel actualHead){
		mIsHyper = isHyper;
		mActualHead = isHyper ? actualHead : null;
	}
	
	/**
	 * Gets whether this class element is a hyper class.
	 */
	public boolean isHyper(){
		return mIsHyper;
	}
	
	/**
	 * Gets the hyper class's actual head, or null if this is not a hyper class.
	 */
	public ClassElementGraphicalModel getActualHead(){
		return mActualHead;
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
	 * Sets the relationships with children of this class for displaying the
	 * compacted version of this element. The specified list may be empty
	 * or null. Relationships will be displayed from left to right in the
	 * order given by the list.
	 */
	public void setChildRelationships(List<RelationshipType> childRelationships){
		mChildRelationships = childRelationships;
	}
	
	/**
	 * Gets a list of relationships with the children of this class element. List
	 * may be empty or null.
	 */
	public List<RelationshipType> getChildRelationships(){
		return mChildRelationships;
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
	
	/**
	 * Returns the compaction status of the class element's parent class
	 * or false if the class element has no parent.
	 */
	public boolean isParentCompacted(){
		return mIsParentCompacted;
	}
	
	/**
	 * Sets whether the class element's parent class element is compacted.
	 */
	public void setIsParentCompacted(boolean isParentCompacted){
		mIsParentCompacted = isParentCompacted;
	}
	
	/**
	 * Returns true if the class element has been marked visible (true by default) and
	 * the parent class is not compacted.
	 */
	public boolean isVisible(){
		return (mIsVisible && !mIsParentCompacted);
	}
	
	/**
	 * Sets whether the class element is visible. True by default.
	 */
	public void setIsVisible(boolean isVisible){
		mIsVisible = isVisible;
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
