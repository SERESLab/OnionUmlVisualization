package edu.ysu.onionuml.ui.graphics.editparts;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.ConnectionEndpointLocator;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.ManhattanConnectionRouter;
import org.eclipse.draw2d.MidpointLocator;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.Request;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.swt.SWT;

import edu.ysu.onionuml.core.RelationshipType;
import edu.ysu.onionuml.core.UmlRelationshipElement;
import edu.ysu.onionuml.core.UmlRelationshipElement.Multiplicity;
import edu.ysu.onionuml.ui.graphics.graphicalmodels.ClassElementGraphicalModel;
import edu.ysu.onionuml.ui.graphics.graphicalmodels.RelationshipElementGraphicalModel;

/**
 * Represents the view/controller of a uml diagram relationship to be rendered with the Eclipse
 * Graphical Editing Framework (GEF).
 */
public class RelationshipElementEditPart extends AbstractGraphicalEditPart {
	
	
	// PRIVATE MEMBER CONSTANTS ----------------------------
	
	private static float[] LINE_DASH_STYLE = new float[]{8.0f, 8.0f};
	private static int LABEL_DISTANCE = 12;
	
	
	// OVERRIDE METHODS -------------------------------
	
	@Override
	protected IFigure createFigure() {
		return new PolylineConnection();
	}
	
	@Override
	public DragTracker getDragTracker(Request request){
		return null;
	}
	
	@Override
	protected void createEditPolicies() {
	}
	
	@Override
	public boolean isSelectable(){
		return false;
	}
	
	@Override
	protected void refreshVisuals() {
		RelationshipElementGraphicalModel model = (RelationshipElementGraphicalModel) getModel();
		UmlRelationshipElement rel = model.getRelationshipElement();
		PolylineConnection connection = (PolylineConnection) getFigure();
		
		// lookup head and tail figures to set connection anchors
		ClassDiagramEditPart diagramPart = (ClassDiagramEditPart)getParent();
		ClassElementEditPart headPart = (ClassElementEditPart)diagramPart.lookupEditPartById(rel.getHeadId());
		ClassElementEditPart tailPart = (ClassElementEditPart)diagramPart.lookupEditPartById(rel.getTailId());
		
		if(!((ClassElementGraphicalModel)headPart.getModel()).isVisible()
				|| !((ClassElementGraphicalModel)tailPart.getModel()).isVisible()){
			connection.setVisible(false);
		}
		else{
			connection.setVisible(true);
			
			IFigure headFig = headPart.getFigure();
			IFigure tailFig = tailPart.getFigure();
			
			connection.setTargetAnchor(new ChopboxAnchor(headFig));
			connection.setSourceAnchor(new ChopboxAnchor(tailFig));
			connection.setConnectionRouter(new ManhattanConnectionRouter());
	
			connection.setTargetDecoration(makeHeadDecoration(rel.getType()));
	
			if (rel.getType() == RelationshipType.DEPENDENCY
					|| rel.getType() == RelationshipType.REALIZATION
					|| rel.getType() == RelationshipType.HYPER_DEPENDENCY
					|| rel.getType() == RelationshipType.HYPER_REALIZATION) {
				connection.setLineStyle(SWT.LINE_CUSTOM);
				connection.setLineDash(LINE_DASH_STYLE);
			}
	
			String headLabelStr = makeMultiplicityString(
					rel.getHeadMultiplicityMin(), rel.getHeadMultiplicityMax());
			if (headLabelStr != null && headLabelStr.length() > 0) {
				Label l = new Label(headLabelStr);
				ConnectionEndpointLocator locator = new ConnectionEndpointLocator(
						connection, true);
				locator.setVDistance(LABEL_DISTANCE);
				connection.add(l, locator);
			}
	
			String tailLabelStr = makeMultiplicityString(
					rel.getTailMultiplicityMin(), rel.getTailMultiplicityMax());
			if (tailLabelStr != null && tailLabelStr.length() > 0) {
				Label l = new Label(tailLabelStr);
				ConnectionEndpointLocator locator = new ConnectionEndpointLocator(
						connection, false);
				locator.setVDistance(LABEL_DISTANCE);
				connection.add(l, locator);
			}
	
			String relLabelStr = rel.getLabel();
			if (relLabelStr != null && relLabelStr.length() > 0) {
				Label l = new Label(relLabelStr);
				MidpointLocator locator = new MidpointLocator(connection, 0);
				locator.setGap(LABEL_DISTANCE);
				locator.setRelativePosition(PositionConstants.NORTH_WEST);
				connection.add(l, locator);
			}
			model.setSize(connection.getPreferredSize());
			model.setPosition(connection.getLocation());
		}

	}
	
	
	
	
	// PRIVATE METHODS -----------------------------
	
	private PolygonDecoration makeHeadDecoration(RelationshipType relType){
		
		PolygonDecoration decoration = new PolygonDecoration();
		PointList points = new PointList();
		
		switch(relType){
			case AGGREGATION:
				decoration.setBackgroundColor(ColorConstants.white);
			case COMPOSITION:
				points.addPoint(0,0);
				points.addPoint(-3,3);
				points.addPoint(-6,0);
				points.addPoint(-3,-3);
				break;
			case DIRECTEDASSOCIATION:
			case DEPENDENCY:
				points.addPoint(0,0);
				points.addPoint(-2,4);
				points.addPoint(0,0);
				points.addPoint(-2,-4);
				break;
			case REALIZATION:
			case GENERALIZATION:
				decoration.setBackgroundColor(ColorConstants.white);
				points.addPoint(0,0);
				points.addPoint(-2,4);
				points.addPoint(-2,-4);
				break;
		}
		
		if(points.size() > 0){
			decoration.setTemplate(points);
			return decoration;
		}
		
		return null;
	}
	
	
	private String makeMultiplicityString(Multiplicity min, Multiplicity max){
		
		String s = "";
		
		switch(min){
			case ZERO:
				s = "0";
				break;
			case ONE:
				s = "1";
				break;
			case MANY:
				s = "*";
				break;
		}
		
		if(max != Multiplicity.NONE){
			s += "...";
		}
		
		switch (max){
			case ZERO:
				s += "0";
				break;
			case ONE:
				s += "1";
				break;
			case MANY:
				s += "*";
				break;
		}
		
		return s;
	}
}
