package edu.ysu.onionuml.ui.graphics.editparts;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.tools.MarqueeDragTracker;

import edu.ysu.onionuml.ui.DiagramControlView;
import edu.ysu.onionuml.ui.graphics.IEventListener;
import edu.ysu.onionuml.ui.graphics.IEventRegistrar;
import edu.ysu.onionuml.ui.graphics.figures.ClassFigure;
import edu.ysu.onionuml.ui.graphics.graphicalmodels.ClassDiagramGraphicalModel;
import edu.ysu.onionuml.ui.graphics.graphicalmodels.IElementGraphicalModel;


/**
 * Represents the view/controller of a uml class diagram to be displayed with the
 * Eclipse Graphical Editing Framework (GEF).
 */
public class ClassDiagramEditPart extends AbstractGraphicalEditPart
	implements IEventListener{
	
	/**
	 * Event string specifying that a visual refresh is required.
	 */
	public static final String EVENT_REFRESH_REQUIRED = "EVENT_REFRESH_REQUIRED";
	
	/**
	 * Event string specifying that the EditPart has been activated. Automatically
	 * broadcast on activation.
	 */
	public static final String EVENT_ACTIVATED = "EVENT_ACTIVATED";
	
	
	private Set<ClassElementEditPart> mSelectedClasses = new HashSet<ClassElementEditPart>();

	@Override
	protected IFigure createFigure() {
		Figure f = new Figure();
        f.setOpaque(true);
        f.setLayoutManager(new XYLayout());
        return f;
	}
	
	@Override
	public void activate(){
		super.activate();
		((IEventRegistrar)getModel()).registerEventListener(this);
		((IEventListener)getModel()).eventOccured(EVENT_ACTIVATED);
	}
	
	@Override
	public void deactivate(){
		super.deactivate();
		((IEventRegistrar)getModel()).unregisterEventListener();
	}
	
	@Override
	public DragTracker getDragTracker(Request request){
		return new MarqueeDragTracker();
	}
	
	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new XYLayoutEditPolicy(){
			
			@Override
			protected Command getCreateCommand(CreateRequest request) {
				return null;
			}
			@Override
			protected EditPolicy createChildEditPolicy(EditPart child) {
				return new NonResizableEditPolicy(){
					@Override
					public boolean isDragAllowed(){
						return false;
					}
					@Override
					public boolean understandsRequest(Request request){
						return false;
					}
				};
			}
		});
	}
	
	@Override
	protected List<IElementGraphicalModel> getModelChildren() {
		if(getModel() != null){
			return ((ClassDiagramGraphicalModel) getModel()).getElements();
		}
		return null;
    }

	@Override
	public void eventOccured(String evt) {
		
		if(evt.equals(EVENT_REFRESH_REQUIRED)){
			
			for(Object child : getChildren()){
				((AbstractGraphicalEditPart)child).refresh();
			}
		}
	}
	
	/**
	 * Looks up and returns the child edit part assigned to the specified id,
	 * or null if it cannot be found.
	 */
	public AbstractGraphicalEditPart lookupEditPartById(String id){
		
		ClassDiagramGraphicalModel diagramModel = (ClassDiagramGraphicalModel)getModel();
		IElementGraphicalModel model = diagramModel.lookupGraphicalModelById(id);
		
		for(Object child : getChildren()){
			AbstractGraphicalEditPart e = (AbstractGraphicalEditPart)child;
			if(model.equals(e.getModel())){
				return e;
			}
		}
		
		return null;
	}
	
	/**
	 * Adds the specified class element to the set of selected classes.
	 */
	public void addToSelection(ClassElementEditPart c){
		mSelectedClasses.add(c);
	}
	
	/**
	 * Removes the specified class element from the set of selected classes if it is
	 * in the list.
	 */
	public void removeFromSelection(ClassElementEditPart c){
		mSelectedClasses.remove(c);
	}
	
	/**
	 * Gets a reference to the set of selected class elements.
	 */
	public Set<ClassElementEditPart> getSelectedClasses(){
		return mSelectedClasses;
	}
	
	/**
	 * Adds all parts to selected
	 */
	public void addAllToSelected() {
		ClassDiagramEditPart parent = ((ClassDiagramEditPart)getParent());
		parent.setSelected(EditPart.SELECTED);
		mSelectedClasses = parent.getSelectedClasses();
		//return parent.getSelectedClasses();
	}
}
