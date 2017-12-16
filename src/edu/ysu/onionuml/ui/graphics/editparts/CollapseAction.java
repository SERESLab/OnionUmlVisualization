package edu.ysu.onionuml.ui.graphics.editparts;

import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.ActionFactory;

import edu.ysu.onionuml.ui.DiagramControlView;


/*
 * Action to make a Collapse Function appear on right click context menu
 * To see tutorial I was trying to follow go here: 
 * https://www.inf.ed.ac.uk/teaching/courses/ip/resources/GEF/GEF_Tutorial_2up.pdf
 */
public class CollapseAction extends SelectionAction {

	public CollapseAction(IWorkbenchPart part) {
		super(part);
		setLazyEnablementCalculation(false);
		}
	
	protected void init() {
		setText("Collapse Selected...");
		setToolTipText("Collapse");
		
		setId(ActionFactory.UNDO.getId());
		
		setEnabled(false);
		}
	
	@Override
	protected boolean calculateEnabled() {
		return true;
	}
//	public Command createCollapseCommand() {
//		Request collapseReq = new Request("collapse");
//		HashMap<String, String> reqData = new HashMap<String, String>();
//		reqData.put("newName", name);
//		renameReq.setExtendedData(reqData);
//		EditPart object = (EditPart)getSelectedObjects().get(0);
//		Command cmd = object.getCommand(renameReq);
//		return cmd;
//		}
	
	public void run() {
		DiagramControlView.onCompactSelectedPressed();
	}
	
}
