package com.onionuml.visplugin.ui.handler;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import com.onionuml.visplugin.core.UmlClassModel;
import com.onionuml.visplugin.ui.ModelViewer;
import com.onionuml.visplugin.ui.ModelViewerInput;
import com.onionuml.visplugin.ui.graphics.graphicalmodels.ClassDiagramGraphicalModel;

/**
 * Command that prompts the user to open a class model and then loads
 * the selected model into the workspace.
 */
public class OpenClassModelCommandHandler implements IHandler {
	
	private static final String OPEN_DIALOG_TITLE = "Open Class Model";
	private static final String[] OPEN_FILTER_NAMES = {"ClassML files"};
	private static final String[] OPEN_FILTER_EXT = {"*.xml"};
	
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		Shell shell = new Shell(Display.getDefault());
		
		FileDialog fd = new FileDialog(shell, SWT.OPEN);
        fd.setText(OPEN_DIALOG_TITLE);
        fd.setFilterNames(OPEN_FILTER_NAMES);
        fd.setFilterExtensions(OPEN_FILTER_EXT);
        String filename = fd.open();
		
        if(filename != null){
        	
        	UmlClassModel model = null;
        	try{
        		model = UmlClassModel.fromFile(filename);
        	}
        	catch(Exception e){
        		MessageBox messageBox = new MessageBox(shell, SWT.ERROR);
        	    messageBox.setMessage(e.getMessage());
        	    messageBox.open();
        	}
        	
        	if(model != null){
	        	ClassDiagramGraphicalModel graphicalModel = new ClassDiagramGraphicalModel(model);
	        	ModelViewerInput viewerInput = new ModelViewerInput(graphicalModel);
	        	
	        	IWorkbenchPage p = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
	        	try {
					p.openEditor(viewerInput, ModelViewer.ID);
				} catch (PartInitException e) {
					throw new ExecutionException("Could not open model viewer: " + e.getMessage());
				}
        	}
        }
        
		return null;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	@Override
	public boolean isHandled() {
		return true;
	}
	
	@Override
	public void addHandlerListener(IHandlerListener handlerListener) {
	}
	
	@Override
	public void removeHandlerListener(IHandlerListener handlerListener) {
	}
	
	@Override
	public void dispose() {
	}
}
