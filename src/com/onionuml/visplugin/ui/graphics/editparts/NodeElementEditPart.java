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

import com.onionuml.visplugin.core.UmlNodeAttribute;
import com.onionuml.visplugin.core.UmlNodeElement;
import com.onionuml.visplugin.core.UmlNodeOperation;
import com.onionuml.visplugin.core.UmlNodeOperationParameter;
import com.onionuml.visplugin.ui.graphics.figures.NodeFigure;
import com.onionuml.visplugin.ui.graphics.graphicalmodels.NodeElementGraphicalModel;

/**
 * Represents the view/controller of a uml diagram node to be rendered with the Eclipse
 * Graphical Editing Framework (GEF).
 */
public class NodeElementEditPart extends AbstractGraphicalEditPart{
	
	private static final Color NODE_COLOR = new Color(null, 255, 255, 206);
	private static final Font NAME_FONT = new Font(null, "Arial", 12, SWT.BOLD);
	private static final Font STEREOTYPE_FONT = new Font(null, "Arial", 10, SWT.NORMAL);
	private static final Font NAME_ABSTRACT_FONT = new Font(null, "Arial", 12, SWT.BOLD | SWT.ITALIC);
	private static final Font NORMAL_FONT = new Font(null, "Arial", 12, SWT.NORMAL);
	private static final Font ABSTRACT_FONT = new Font(null, "Arial", 12, SWT.ITALIC);
	private static final Font STATIC_FONT = new Font(null, "Arial", 12, SWT.UNDERLINE_SINGLE);
	
	@Override
	protected IFigure createFigure() {
		return new NodeFigure(NODE_COLOR, NAME_FONT, NORMAL_FONT, STEREOTYPE_FONT);
	}
	
	@Override
	public DragTracker getDragTracker(Request request){
		return null;
	}
	
	@Override
	protected void createEditPolicies() {
	}
	
	@Override
	protected void refreshVisuals() {
		NodeElementGraphicalModel model = (NodeElementGraphicalModel) getModel();
		NodeFigure figure = (NodeFigure) getFigure();
		UmlNodeElement nodeElement = model.getNodeElement();
		
		figure.clear();
		
		if(nodeElement.getIsAbstract()){
			figure.setNameFont(NAME_ABSTRACT_FONT);
		}
		
		figure.setNameString(nodeElement.getName());
		
		String stereotype = nodeElement.getStereotype();
		if(stereotype != null && stereotype.length() > 0){
			figure.setStereotypeString("Ç" + stereotype + "È");
		}
		
		// setup properties
		if(nodeElement.getAttributes().size() == 0){
			figure.addProperty("", null, null);
		}
		else{
			for(UmlNodeAttribute a : nodeElement.getAttributes()){
				
				String visStr = "";
				switch(a.visibility){
					case PRIVATE:
						visStr = "- ";
						break;
					case PROTECTED:
						visStr = "# ";
						break;
					case PUBLIC:
						visStr = "+ ";
						break;
				}
				String str = visStr + a.name;
				if(a.dataType != null && !a.dataType.equals("")){
					str += " : " + a.dataType;
				}
				
				figure.addProperty(str, null, null);
			}
		}
		
		
		// setup operations
		if(nodeElement.getOperations().size() == 0){
			figure.addOperation("", null, null);
		}
		else{
			for(UmlNodeOperation o : nodeElement.getOperations()){
				
				String visStr = "";
				switch(o.visibility){
					case PRIVATE:
						visStr = "- ";
						break;
					case PROTECTED:
						visStr = "# ";
						break;
					case PUBLIC:
						visStr = "+ ";
						break;
				}
				
				String paramStr = "(";
				for(int i=0; i < o.parameters.size(); ++i){
					UmlNodeOperationParameter op = o.parameters.get(i);
					if(i == 0){
						paramStr += op.name;
					}
					else{
						paramStr += ", " + op.name;
					}
					if(op.dataType != null && !op.dataType.equals("")){
						paramStr += " : " + op.dataType;
					}
				}
				paramStr += ")";
				
				String str = visStr + o.name + paramStr;
				if(o.returnType != null && !o.returnType.equals("")){
					str += " : " + o.returnType;
				}
				
				if(o.isAbstract){
					figure.addOperation(str, null, ABSTRACT_FONT);
				}
				else if(o.isStatic){
					figure.addOperation(str, null, STATIC_FONT);
				}
				else{
					figure.addOperation(str, null, null);
				}
			}
		}
		
		model.setSize(figure.getPreferredSize());
		
		((GraphicalEditPart) getParent()).setLayoutConstraint(this, figure,
				new Rectangle(model.getPosition(), new Dimension(-1,-1)));
	}
}
