package edu.ysu.onionuml.ui.graphics.editparts;

import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.actions.ActionFactory;

/*
 * This is the context menu that will pop up when you right click on the program.
 * Need to change what UNDO and REDO do in order to get the actions to work with OnionUML
 * 
 */

public class AppContextMenuProvider extends ContextMenuProvider{
	private static ActionRegistry actionRegistry;

	public AppContextMenuProvider(EditPartViewer viewer, ActionRegistry registry) {
		super(viewer);
		setActionRegistry(registry);
	}

	@Override
	public void buildContextMenu(IMenuManager menu) {
		IAction action;
		GEFActionConstants.addStandardActionGroups(menu);
		action = getActionRegistry().getAction(ActionFactory.UNDO.getId());
		menu.appendToGroup(GEFActionConstants.GROUP_UNDO, action);
		action = getActionRegistry().getAction(ActionFactory.REDO.getId());
		menu.appendToGroup(GEFActionConstants.GROUP_UNDO, action);
	}
	
	public static ActionRegistry getActionRegistry() {
		return actionRegistry;
	}
	
	private void setActionRegistry(ActionRegistry registry) {
		actionRegistry = registry;
	}

}
