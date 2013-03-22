package edu.ysu.onionuml.ui.graphics.editparts;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;

import edu.ysu.onionuml.ui.graphics.graphicalmodels.ClassElementGraphicalModel;

/**
 * Represents the view/controller of a hyperclass to be rendered with the Eclipse
 * Graphical Editing Framework (GEF). A hyperclass is the connection point for multiple
 * relationships to one class.
 */
public class HyperClassElementEditPart extends ClassElementEditPart{
	
	
	@Override
	protected IFigure createFigure() {
		IFigure f = new Ellipse();
		f.setBackgroundColor(ColorConstants.black);
		f.setSize(6, 6);
		return f;
	}
	
	
	@Override
	protected void createEditPolicies() {
	}
	
	@Override
	protected void refreshVisuals() {
		ClassElementGraphicalModel model = (ClassElementGraphicalModel) getModel();
		IFigure figure = getFigure();
		
		if(!model.isVisible()){
			figure.setVisible(false);
		}
		else{
			figure.setVisible(true);
		}
		
		((GraphicalEditPart) getParent()).setLayoutConstraint(this, figure,
				new Rectangle(model.getPosition(), new Dimension(-1,-1)));
	}
}