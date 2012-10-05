package com.onionuml.visplugin.ui;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.ui.parts.GraphicalEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;

import com.onionuml.visplugin.Activator;
import com.onionuml.visplugin.ui.graphics.EditPartFactory;
import com.onionuml.visplugin.ui.graphics.graphicalmodels.ClassDiagramGraphicalModel;

/**
 * Editor for viewing and interacting with diagrams.
 */
public final class ModelViewer extends GraphicalEditor {
	
	// PUBLIC MEMBER VARIABLES ---------------------------
	
	/**
	 * The id of the editor.
	 */
	public static final String ID = Activator.PACKAGE_BASE + ".ui.modelviewer";
	
	
	
	// PRIVATE MEMBER VARIABLES ---------------------------
	
	private ClassDiagramGraphicalModel mModel;
	private ModelViewerInput mEditorInput;
	
	
	
	// CONSTRUCTOR
	
	/**
	 * Constructs a new ModelViewer object, using the default edit domain.
	 */
	public ModelViewer(){
		setEditDomain(new DefaultEditDomain(this));
	}
	
	
	
	// OVERRIDE METHODS -----------------------------
	
	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		super.init(site, input);
		
		if (!(input instanceof ModelViewerInput)) {
			throw new RuntimeException("Input not of ModelViewerInput type.");
		}
		
		mEditorInput = ((ModelViewerInput)input);
		mModel = mEditorInput.getModel();
		setPartName(mModel.getClassModel().getName());
	}
	
	@Override
	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();
		getGraphicalViewer().setEditPartFactory(new EditPartFactory());
	}

	@Override
	protected void initializeGraphicalViewer() {
		
		getGraphicalViewer().setContents(mModel);
	}
	
	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);
		
	}
	
	@Override
	public void dispose() {
		if(mEditorInput != null){
			mEditorInput.dispose();
		}
		super.dispose();
	}
	
	@Override
	public boolean isDirty() {
		return false;
	}
	
	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}
	
	@Override
	public void doSave(IProgressMonitor monitor) {
		// no save
	}
	
	@Override
	public void doSaveAs() {
		// no save
	}
}
