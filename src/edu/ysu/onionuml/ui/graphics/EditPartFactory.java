package edu.ysu.onionuml.ui.graphics;

import org.eclipse.gef.EditPart;

import edu.ysu.onionuml.ui.graphics.editparts.ClassDiagramEditPart;
import edu.ysu.onionuml.ui.graphics.editparts.ClassElementEditPart;
import edu.ysu.onionuml.ui.graphics.editparts.HyperClassElementEditPart;
import edu.ysu.onionuml.ui.graphics.editparts.RelationshipElementEditPart;
import edu.ysu.onionuml.ui.graphics.graphicalmodels.ClassDiagramGraphicalModel;
import edu.ysu.onionuml.ui.graphics.graphicalmodels.ClassElementGraphicalModel;
import edu.ysu.onionuml.ui.graphics.graphicalmodels.RelationshipElementGraphicalModel;


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
		
		if(model instanceof ClassElementGraphicalModel){
			EditPart e;
			if(!((ClassElementGraphicalModel)model).isHyper()){
				e = new ClassElementEditPart();
			}
			else{
				e = new HyperClassElementEditPart();
			}
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
