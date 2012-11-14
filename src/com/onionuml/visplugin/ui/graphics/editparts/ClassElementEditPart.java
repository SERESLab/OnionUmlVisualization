package com.onionuml.visplugin.ui.graphics.editparts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;

import com.onionuml.visplugin.core.UmlAttribute;
import com.onionuml.visplugin.core.UmlClassElement;
import com.onionuml.visplugin.core.UmlOperation;
import com.onionuml.visplugin.ui.graphics.figures.ClassFigure;
import com.onionuml.visplugin.ui.graphics.graphicalmodels.ClassElementGraphicalModel;

/**
 * Represents the view/controller of a uml class to be rendered with the Eclipse
 * Graphical Editing Framework (GEF).
 */
public class ClassElementEditPart extends AbstractGraphicalEditPart{
	
	private static final Color CLASS_COLOR = new Color(null, 255, 255, 206);
	private static final Font NAME_FONT = new Font(null, "Arial", 12, SWT.BOLD);
	private static final Font STEREOTYPE_FONT = new Font(null, "Arial", 10, SWT.NORMAL);
	private static final Font NAME_ABSTRACT_FONT = new Font(null, "Arial", 12, SWT.BOLD | SWT.ITALIC);
	private static final Font NORMAL_FONT = new Font(null, "Arial", 12, SWT.NORMAL);
	private static final Font ABSTRACT_FONT = new Font(null, "Arial", 12, SWT.ITALIC);
	private static final Font STATIC_FONT = new Font(null, "Arial", 12, SWT.UNDERLINE_SINGLE);
	
	@Override
	protected IFigure createFigure() {
		return new ClassFigure(CLASS_COLOR, NAME_FONT, NORMAL_FONT, STEREOTYPE_FONT);
	}
	
	@Override
	public DragTracker getDragTracker(Request request){
		return super.getDragTracker(request);
	}
	
	@Override
	protected void createEditPolicies() {
	}
	
	@Override
	protected void refreshVisuals() {
		ClassElementGraphicalModel model = (ClassElementGraphicalModel) getModel();
		ClassFigure figure = (ClassFigure) getFigure();
		UmlClassElement classElement = model.getClassElement();
		
		figure.clear();
		
		if(classElement.getIsAbstract()){
			figure.setNameFont(NAME_ABSTRACT_FONT);
		}
		
		figure.setNameString(classElement.getName());
		
		String stereotype = classElement.getStereotype();
		if(stereotype != null && stereotype.length() > 0){
			figure.setStereotypeString("Ç" + stereotype + "È");
		}
		
		// setup properties
		if(classElement.getAttributes().size() == 0){
			figure.addProperty("", null, null);
		}
		else{
			for(UmlAttribute a : classElement.getAttributes()){
				figure.addProperty(a.toString(), null, null);
			}
		}
		
		
		// setup operations
		if(classElement.getOperations().size() == 0){
			figure.addOperation("", null, null);
		}
		else{
			for(UmlOperation o : classElement.getOperations()){
				
				if(o.isAbstract){
					figure.addOperation(o.toString(), null, ABSTRACT_FONT);
				}
				else if(o.isStatic){
					figure.addOperation(o.toString(), null, STATIC_FONT);
				}
				else{
					figure.addOperation(o.toString(), null, null);
				}
			}
		}
		
		model.setSize(figure.getPreferredSize());
		
		((GraphicalEditPart) getParent()).setLayoutConstraint(this, figure,
				new Rectangle(model.getPosition(), new Dimension(-1,-1)));
	}
}
