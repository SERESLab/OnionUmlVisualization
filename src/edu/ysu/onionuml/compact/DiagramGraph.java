package edu.ysu.onionuml.compact;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.TreeMap;

import edu.ysu.onionuml.core.RelationshipType;
import edu.ysu.onionuml.ui.graphics.graphicalmodels.ClassElementGraphicalModel;
import edu.ysu.onionuml.ui.graphics.graphicalmodels.IElementGraphicalModel;
import edu.ysu.onionuml.ui.graphics.graphicalmodels.RelationshipElementGraphicalModel;

/**
 * Models a set of directed multigraphs where each node is a class element
 * and each edge is a relationship. No class element can be added more than once
 * and no two classes can have more than one relationship between them.
 */
public class DiagramGraph {
	
	/*
	 * Models a node storing a class element and parent/child references.
	 */
	private class Node {
		boolean visited;
		List<Node> parents;
		ClassElementGraphicalModel classElement;
		Map<Node,RelationshipElementGraphicalModel> children;
		Node(ClassElementGraphicalModel classElement){
			this.classElement = classElement;
			this.parents = new ArrayList<Node>();
			this.children = new HashMap<Node,RelationshipElementGraphicalModel>();
		}
	}
	

	private List<Node> mRootNodes = new LinkedList<Node>();
	private List<IElementGraphicalModel> mElements =
			new ArrayList<IElementGraphicalModel>();
	private Map<ClassElementGraphicalModel,Node> mClassNodeMap =
			new HashMap<ClassElementGraphicalModel,Node>();
	
	
	
	// PUBLIC METHODS --------------------------------------
	
	/**
	 * Adds the specified class element as a new root node.
	 * @param classElement the class element to add
	 */
	public void addElement(ClassElementGraphicalModel classElement){
		
		if(mClassNodeMap.containsKey(classElement)){
			throw new RuntimeException("Element has already been added.");
		}
		
		Node node = new Node(classElement);
		mClassNodeMap.put(classElement, node);
		mRootNodes.add(node);
		mElements.add(classElement);
	}
	
	/**
	 * Adds a relationship between the specified class elements.
	 * @param relationToParent the element representing the relationship from
	 * the child to the parent.
	 * @param child the child element in the relationship; must be added before
	 * the relationship
	 * @param parent the parent element in the relationship; must be added before
	 * the relationship
	 */
	public void addRelationship(RelationshipElementGraphicalModel relationToParent,
			ClassElementGraphicalModel child, ClassElementGraphicalModel parent){
		
		Node parentNode = mClassNodeMap.get(parent);
		Node childNode = mClassNodeMap.get(child);
		
		if(relationToParent == null){
			throw new NullPointerException("Relationship cannot be null.");
		}
		if(parentNode == null){
			throw new RuntimeException("Parent does not exist for relationship being added.");
		}
		if(childNode == null){
			throw new RuntimeException("Child does not exist for relationship being added.");
		}
		if(parentNode.children.containsKey(childNode)
				|| childNode.parents.contains(parentNode)){
			throw new RuntimeException("A relationship already exists between specified elements.");
		}
		parentNode.children.put(childNode, relationToParent);
		childNode.parents.add(parentNode);
		mRootNodes.remove(childNode);
		mElements.add(relationToParent);
	}
	
	
	
	/**
	 * Updates the graphical state of each tree in the collection.
	 */
	public void update(){
		updateGraph();
	}
	
	
	
	
	// PRIVATE METHODS --------------------------------------
	
	/*
	 * Updates the specified node and its children in a breadth-first traversal.
	 */
	private void updateGraph(){
		
		TreeMap<Integer,RelationshipType> positionRelationMap =
				new TreeMap<Integer,RelationshipType>();
		
		// sweep nodes to mark as unvisited
		Iterator<Entry<ClassElementGraphicalModel,Node>> itNodes =
				mClassNodeMap.entrySet().iterator();
		while (itNodes.hasNext()) {
			Entry<ClassElementGraphicalModel,Node> pairs =
					(Entry<ClassElementGraphicalModel,Node>)itNodes.next();
			Node n = pairs.getValue();
			n.visited = false;
		}
		
		Queue<Node> nodeQueue = new LinkedList<Node>();
		for(Node root : mRootNodes){
			nodeQueue.add(root);
			while(!nodeQueue.isEmpty()){
				Node currentNode = nodeQueue.remove();
				if(currentNode.visited){
					continue;
				}
				
				boolean isParentCompacted = false;
				for(Node parent : currentNode.parents){
					// if a parent is compacted then set this node to compacted but
					// do not expand unless explicitly chosen
					if(parent.classElement.isCompacted()){
						currentNode.classElement.setIsCompacted(true);
						isParentCompacted = true;
					}
				}
				currentNode.classElement.setIsParentCompacted(isParentCompacted);
				
				if(currentNode.classElement.isCompacted()){
					positionRelationMap.clear();
				}
				
				// process children of the current element and add to queue
				Iterator<Entry<Node,RelationshipElementGraphicalModel>> itChildren =
						currentNode.children.entrySet().iterator();
				while (itChildren.hasNext()) {
					Entry<Node,RelationshipElementGraphicalModel> pairs =
							(Entry<Node,RelationshipElementGraphicalModel>)itChildren.next();
					Node child = pairs.getKey();
					RelationshipElementGraphicalModel relationship = pairs.getValue();
					if(!nodeQueue.contains(child)){
						nodeQueue.add(child);
					}
					// add child relationship to map sorted by X position
					if(currentNode.classElement.isCompacted()){
						Integer pos = Integer.valueOf(child.classElement.getPosition().x);
						RelationshipType rel = relationship.getRelationshipElement().getType();
						positionRelationMap.put(pos, rel);
					}
				}
				
				if(currentNode.classElement.isCompacted()){
					List<RelationshipType> childRelationships =
							currentNode.classElement.getChildRelationships();
					if(childRelationships == null){
						childRelationships = new ArrayList<RelationshipType>();
						currentNode.classElement.setChildRelationships(childRelationships);
					}
					else{
						childRelationships.clear();
					}
					for(RelationshipType rel: positionRelationMap.values()){
						childRelationships.add(rel);
					}
				}
				
				currentNode.visited = true;
			}
		}
	}
}
