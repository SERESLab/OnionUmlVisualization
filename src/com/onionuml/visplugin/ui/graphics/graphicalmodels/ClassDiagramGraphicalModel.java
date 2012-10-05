package com.onionuml.visplugin.ui.graphics.graphicalmodels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.draw2d.geometry.Point;

import com.onionuml.visplugin.core.UmlClassModel;
import com.onionuml.visplugin.core.UmlNodeElement;
import com.onionuml.visplugin.core.UmlRelationshipElement;
import com.onionuml.visplugin.ui.graphics.IEventListener;
import com.onionuml.visplugin.ui.graphics.IEventRegistrar;
import com.onionuml.visplugin.ui.graphics.editparts.ClassDiagramEditPart;


/**
 * Represents the model of a uml class diagram to be displayed with the
 * Eclipse Graphical Editing Framework (GEF).
 */
public class ClassDiagramGraphicalModel implements IEventListener, IEventRegistrar {
	
	// PRIVATE MEMBER VARIABLES ----------------------------------
	
	private IEventListener mListener = null;
	private List<IElementGraphicalModel> mElements;
	private Map<String,NodeElementGraphicalModel> mNodeIdMap =
			new HashMap<String,NodeElementGraphicalModel>();
	private Map<String,RelationshipElementGraphicalModel> mRelationshipIdMap =
			new HashMap<String,RelationshipElementGraphicalModel>();
	private UmlClassModel mClassModel;
	
	private boolean mNodeSizeChanged = false;
	
	
	
	// PUBLIC METHODS ----------------------------------------
	
	/**
	 * Constructs a new ClassDiagramGraphicalModel object from the
	 * given UmlClassModel.
	 */
	public ClassDiagramGraphicalModel(UmlClassModel classModel){
		mElements = new ArrayList<IElementGraphicalModel>();
		mClassModel = classModel;
		
		Iterator<Entry<String,UmlNodeElement>> itNodes = classModel.getNodes().entrySet().iterator();
		while (itNodes.hasNext()) {
			Entry<String,UmlNodeElement> pairs = (Entry<String,UmlNodeElement>)itNodes.next();
			NodeElementGraphicalModel n = new NodeElementGraphicalModel(pairs.getValue());
			n.registerEventListener(this);
			mElements.add(n);
			mNodeIdMap.put(pairs.getKey(), n);
		}
		
		Iterator<Entry<String,UmlRelationshipElement>> itRel =
				classModel.getRelationships().entrySet().iterator();
		while (itRel.hasNext()) {
			Entry<String,UmlRelationshipElement> pairs = (Entry<String,UmlRelationshipElement>)itRel.next();
			RelationshipElementGraphicalModel r = new RelationshipElementGraphicalModel(pairs.getValue());
			mElements.add(r);
			mRelationshipIdMap.put(pairs.getKey(), r);
		}
	}
	
	@Override
	public void registerEventListener(IEventListener listener){
		mListener = listener;
	}
	
	@Override
	public void unregisterEventListener(){
		mListener = null;
	}

	@Override
	public void eventOccured(String evt) {
		
		if(evt.equals(NodeElementGraphicalModel.EVENT_SIZE_CHANGED)){
			mNodeSizeChanged = true;
		}
		else if(evt.equals(ClassDiagramEditPart.EVENT_ACTIVATED)
				&& mNodeSizeChanged && mListener != null){
			layoutElements();
			mNodeSizeChanged = false;
			mListener.eventOccured(ClassDiagramEditPart.EVENT_REFRESH_REQUIRED);
		}
	}
	
	/**
	 * Returns the collection of elements managed by the DiagramModel.
	 */
	public List<IElementGraphicalModel> getElements(){
		return mElements;
	}
	
	/**
	 * Gets a reference to the underlying UmlClassModel object.
	 */
	public UmlClassModel getClassModel(){
		return mClassModel;
	}
	
	/**
	 * Finds the id assigned to the specified element graphical model, or null if the
	 * element cannot be found.
	 */
	public String lookupIdByGraphicalModel(IElementGraphicalModel element){
		
		Iterator<Entry<String,NodeElementGraphicalModel>> itNodes =
				mNodeIdMap.entrySet().iterator();
		while (itNodes.hasNext()) {
			Entry<String,NodeElementGraphicalModel> pairs =
					(Entry<String,NodeElementGraphicalModel>)itNodes.next();
			if(pairs.getValue().equals(element)){
				return pairs.getKey();
			}
		}
		Iterator<Entry<String,RelationshipElementGraphicalModel>> itRelationships =
				mRelationshipIdMap.entrySet().iterator();
		while (itNodes.hasNext()) {
			Entry<String,RelationshipElementGraphicalModel> pairs =
					(Entry<String,RelationshipElementGraphicalModel>)itRelationships.next();
			if(pairs.getValue().equals(element)){
				return pairs.getKey();
			}
		}
		
		return null;
	}
	
	/**
	 * Finds the element graphical model associated with the specified id, or null if an
	 * element cannot be found.
	 */
	public IElementGraphicalModel lookupGraphicalModelById(String id){
		
		IElementGraphicalModel node = mNodeIdMap.get(id);
		return (node != null ? node : mRelationshipIdMap.get(id));
	}
	
	
	
	// PRIVATE METHODS ----------------------------------------
	
	private void layoutElements(){
		
		
		
		
		// *********** temp ***********
		
		
		
		
		
		
		int maxWidth = 1000;
		int padding = 100;
		
		int longestHeight = padding;
		int curY = padding;
		
		mElements.get(0).setPosition(new Point(padding, padding));
		longestHeight = mElements.get(0).getSize().height;
		
		for(int i=1; i < mElements.size(); ++i){
			IElementGraphicalModel prev = mElements.get(i-1);
			IElementGraphicalModel cur = mElements.get(i);
			
			int x = prev.getPosition().x + prev.getSize().width + padding;
			int y = curY;
			
			if(x+cur.getSize().width > maxWidth){
				x = padding;
				y = curY + longestHeight + padding;
				curY = y;
			}
			
			cur.setPosition(new Point(x, y));
			
			if(cur.getSize().height > longestHeight){
				longestHeight = cur.getSize().height;
			}
		}
	}
}
