package com.onionuml.visplugin.ui.graphics.graphicalmodels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.draw2d.geometry.Point;

import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.util.mxPoint;
import com.mxgraph.view.mxGraph;
import com.onionuml.visplugin.core.UmlClassElement;
import com.onionuml.visplugin.core.UmlClassModel;
import com.onionuml.visplugin.core.UmlPackageElement;
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
	private Map<String,ClassElementGraphicalModel> mClassIdMap =
			new HashMap<String,ClassElementGraphicalModel>();
	private Map<String,RelationshipElementGraphicalModel> mRelationshipIdMap =
			new HashMap<String,RelationshipElementGraphicalModel>();
	private UmlClassModel mClassModel;
	
	private boolean mClassSizeChanged = false;
	
	
	
	// PUBLIC METHODS ----------------------------------------
	
	/**
	 * Constructs a new ClassDiagramGraphicalModel object from the
	 * given UmlClassModel.
	 */
	public ClassDiagramGraphicalModel(UmlClassModel classModel){
		mElements = new ArrayList<IElementGraphicalModel>();
		mClassModel = classModel;
		
		Iterator<Entry<String,UmlPackageElement>> itPackages = classModel.getPackages().entrySet().iterator();
		while (itPackages.hasNext()) {
			Entry<String,UmlPackageElement> packagePairs = (Entry<String,UmlPackageElement>)itPackages.next();
			
			Iterator<Entry<String,UmlClassElement>> itClasses =
					packagePairs.getValue().getClasses().entrySet().iterator();
			while (itClasses.hasNext()) {
				Entry<String,UmlClassElement> classPairs = (Entry<String,UmlClassElement>)itClasses.next();
				ClassElementGraphicalModel n = new ClassElementGraphicalModel(classPairs.getValue(), packagePairs.getValue());
				n.registerEventListener(this);
				mElements.add(n);
				mClassIdMap.put(classPairs.getKey(), n);
			}
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
		
		if(evt.equals(ClassElementGraphicalModel.EVENT_SIZE_CHANGED)){
			mClassSizeChanged = true;
		}
		else if(evt.equals(ClassDiagramEditPart.EVENT_ACTIVATED)
				&& mClassSizeChanged && mListener != null){
			layout();
			mClassSizeChanged = false;
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
		
		Iterator<Entry<String,ClassElementGraphicalModel>> itClasses =
				mClassIdMap.entrySet().iterator();
		while (itClasses.hasNext()) {
			Entry<String,ClassElementGraphicalModel> pairs =
					(Entry<String,ClassElementGraphicalModel>)itClasses.next();
			if(pairs.getValue().equals(element)){
				return pairs.getKey();
			}
		}
		Iterator<Entry<String,RelationshipElementGraphicalModel>> itRelationships =
				mRelationshipIdMap.entrySet().iterator();
		while (itRelationships.hasNext()) {
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
		
		IElementGraphicalModel c = mClassIdMap.get(id);
		return (c != null ? c : mRelationshipIdMap.get(id));
	}
	
	
	
	// PRIVATE METHODS ------------------------------
	
	/*
	 * Lays out the classes and relationships using the JGraphX library.
	 */
	private void layout(){
		
		mxGraph graph = new mxGraph();
		mxHierarchicalLayout layout = new mxHierarchicalLayout(graph);
		Object defaultParent = graph.getDefaultParent();
		Map<String,Object> vertexIdMap = new HashMap<String,Object>();
		Map<String,Object> edgeIdMap = new HashMap<String,Object>();
		
		graph.getModel().beginUpdate();
		try{
			Iterator<Entry<String,RelationshipElementGraphicalModel>> itRelationships =
					mRelationshipIdMap.entrySet().iterator();
			while (itRelationships.hasNext()) {
				Entry<String,RelationshipElementGraphicalModel> pairs =
						(Entry<String,RelationshipElementGraphicalModel>)itRelationships.next();
				
				// get head and tail classes for each relationship
				RelationshipElementGraphicalModel rel = pairs.getValue();
				String relId = pairs.getKey();
				String headId = rel.getRelationshipElement().getHeadId();
				String tailId = rel.getRelationshipElement().getTailId();
				IElementGraphicalModel head = lookupGraphicalModelById(headId);
				IElementGraphicalModel tail = lookupGraphicalModelById(tailId);
				
				// add head and tail classes to layout graph
				Object headVertex;
				Object tailVertex;
				if(vertexIdMap.containsKey(headId)){
					headVertex = vertexIdMap.get(headId);
				}
				else{
					headVertex = graph.insertVertex(defaultParent, null, headId,
							head.getPosition().x, head.getPosition().y, 
							head.getSize().width, head.getSize().height);
					vertexIdMap.put(headId, headVertex);
				}
				
				if(vertexIdMap.containsKey(tailId)){
					tailVertex = vertexIdMap.get(tailId);
				}
				else{
					tailVertex = graph.insertVertex(defaultParent, null, tailId,
							tail.getPosition().x, tail.getPosition().y, 
							tail.getSize().width, tail.getSize().height);
					vertexIdMap.put(tailId, tailVertex);
				}
				Object edge = graph.insertEdge(defaultParent, null, relId, headVertex, tailVertex);
				edgeIdMap.put(relId, edge);
			}
			
			// add remaining classes to layout graph
			Iterator<Entry<String,ClassElementGraphicalModel>> itClasses =
					mClassIdMap.entrySet().iterator();
			while (itClasses.hasNext()) {
				Entry<String,ClassElementGraphicalModel> pairs =
						(Entry<String,ClassElementGraphicalModel>)itClasses.next();
				String id = pairs.getKey();
				ClassElementGraphicalModel c = pairs.getValue();
				if(!vertexIdMap.containsKey(id)){
					Object v = graph.insertVertex(defaultParent, null, id,
							c.getPosition().x, c.getPosition().y, 
							c.getSize().width, c.getSize().height);
					vertexIdMap.put(id, v);
				}
			}
			
			layout.execute(graph.getDefaultParent());
		}
		finally{
			graph.getModel().endUpdate();
		}
		
		// get new position of each class
		Iterator<Entry<String,Object>> itVertices = vertexIdMap.entrySet().iterator();
		while (itVertices.hasNext()) {
			Entry<String,Object> pairs = (Entry<String,Object>)itVertices.next();
			IElementGraphicalModel gm = lookupGraphicalModelById(pairs.getKey());
			mxGeometry geom = graph.getModel().getGeometry(pairs.getValue());
			gm.setPosition(new Point((int)geom.getX(), (int)geom.getY()));
		}
		
		// get new points of each edge
		Iterator<Entry<String, Object>> itEdges = edgeIdMap.entrySet().iterator();
		while (itEdges.hasNext()) {
			Entry<String, Object> pairs = (Entry<String, Object>) itEdges.next();
			List<mxPoint> points = graph.getModel().getGeometry(pairs.getValue()).getPoints();
			// TODO set control points for drawing edges
		}
	}
}
