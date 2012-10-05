package com.onionuml.visplugin.ui.graphics;

import org.eclipse.gef.EditPart;

import com.onionuml.visplugin.ui.graphics.editparts.ClassDiagramEditPart;
import com.onionuml.visplugin.ui.graphics.editparts.NodeElementEditPart;
import com.onionuml.visplugin.ui.graphics.editparts.RelationshipElementEditPart;
import com.onionuml.visplugin.ui.graphics.graphicalmodels.ClassDiagramGraphicalModel;
import com.onionuml.visplugin.ui.graphics.graphicalmodels.NodeElementGraphicalModel;
import com.onionuml.visplugin.ui.graphics.graphicalmodels.RelationshipElementGraphicalModel;


/**
 * Factory class to create EditPart objects from the given model.
 */
public class EditPartFactory implements org.eclipse.gef.EditPartFactory {

	@Override
	public EditPart createEditPart(EditPart context, Object model) {
		
		if(model instanceof ClassDiagramGraphicalModel){
			EditPart e = new ClassDiagramEditPart();
			e.setModel(model);
			return e;
		}
		
		if(model instanceof NodeElementGraphicalModel){
			EditPart e = new NodeElementEditPart();
			e.setModel(model);
			return e;
		}
		
		if(model instanceof RelationshipElementGraphicalModel){
			EditPart e = new RelationshipElementEditPart();
			e.setModel(model);
			return e;
		}
		
		return null;
	}

}
