package edu.ysu.onionuml.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.editparts.ScalableRootEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.ui.parts.GraphicalEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.FileEditorInput;

import edu.ysu.onionuml.core.UmlClassModel;
import edu.ysu.onionuml.ui.graphics.EditPartFactory;
import edu.ysu.onionuml.ui.graphics.editparts.AppContextMenuProvider;
import edu.ysu.onionuml.ui.graphics.editparts.ClassDiagramEditPart;
import edu.ysu.onionuml.ui.graphics.graphicalmodels.ClassDiagramGraphicalModel;

/**
 * Editor for viewing and interacting with diagrams.
 */
public final class ModelViewer extends GraphicalEditor {
	
	// CONSTANTS ---------------------------
	
	/**
	 * The id of the editor.
	 */
	public static final String ID = "edu.ysu.onionuml.ui.modelviewer";
	
	private static double[] ZOOM_LEVELS = {0.05, 0.1, 0.25, 0.5, 0.75, 1.0};
	
	
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
			if(!(input instanceof FileEditorInput)){
				throw new RuntimeException("Input could not be opened.");
			}
			
			UmlClassModel model = UmlClassModel.fromFile(((FileEditorInput)input).getPath().toString());
			mModel = new ClassDiagramGraphicalModel(model);
        	mEditorInput = new ModelViewerInput(mModel);
        	setPartName(((FileEditorInput)input).getName());
		}
    	else{
    		mEditorInput = ((ModelViewerInput)input);
    		mModel = mEditorInput.getModel();
    		setPartName(mModel.getClassModel().getName());
    	}
		
		final IWorkbenchPage page = site.getPage();
		page.addPartListener(new IPartListener2(){

			@Override
			public void partActivated(IWorkbenchPartReference partRef) {}

			@Override
			public void partBroughtToTop(IWorkbenchPartReference partRef) {}

			@Override
			public void partClosed(IWorkbenchPartReference partRef) {}

			@Override
			public void partDeactivated(IWorkbenchPartReference partRef) {}

			@Override
			public void partOpened(IWorkbenchPartReference partRef) {
//				if(partRef.getId().equals(ID)){
//					try {
//						page.showView(DiagramControlView.ID);
//					} catch (PartInitException e) {}
//				}
			}

			@Override
			public void partHidden(IWorkbenchPartReference partRef) {
				// when a ModelViewer view becomes hidden disable control
				// of its ClassDiagramEditPart
				if(partRef.getId().equals(ID)){
					ModelViewer viewer = (ModelViewer)partRef.getPart(false);
					if(viewer != null){
						DiagramControlView controlView = (DiagramControlView)page
								.findView(DiagramControlView.ID);
						if(controlView != null){
							ClassDiagramEditPart diagram = 
									(ClassDiagramEditPart)viewer.getGraphicalViewer().getContents();
							if(controlView.getCurrentClassDiagram() == diagram){
								controlView.setCurrentClassDiagram(null);
							}
						}
					}
				}
			}

			@Override
			public void partVisible(IWorkbenchPartReference partRef) {
				// when a ModelViewer view becomes visible enable control of
				// its ClassDiagramEditPart in the control view until it is hidden
				if(partRef.getId().equals(ID)){
					ModelViewer viewer = (ModelViewer)partRef.getPart(false);
					if(viewer != null){
						DiagramControlView controlView = (DiagramControlView)page
								.findView(DiagramControlView.ID);
						if(controlView != null){
							ClassDiagramEditPart diagram = 
									(ClassDiagramEditPart)viewer.getGraphicalViewer().getContents();
							
								controlView.setCurrentClassDiagram(diagram);
							
						}
					}
				}
			}

			@Override
			public void partInputChanged(IWorkbenchPartReference partRef) {}
		});
	}
	
	@Override
	public Object getAdapter(@SuppressWarnings("rawtypes") Class type){
		
		// add support for the ZoomManager
		if (type == ZoomManager.class){
			return getGraphicalViewer().getProperty(ZoomManager.class.toString());
		}
		return super.getAdapter(type);
	}
	
	@Override
	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();
		getGraphicalViewer().setEditPartFactory(new EditPartFactory());
		getGraphicalViewer().setContextMenu(new AppContextMenuProvider(getGraphicalViewer(), getActionRegistry()));
		
		ScalableRootEditPart root = new ScalableRootEditPart();
		getGraphicalViewer().setRootEditPart(root);
		List<String> contributions = new ArrayList<String>();
		contributions.add(ZoomManager.FIT_ALL);
		root.getZoomManager().setZoomLevelContributions(contributions);
		root.getZoomManager().setZoomLevels(ZOOM_LEVELS);
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

	public ClassDiagramGraphicalModel getModel() {
		return mModel;
	}
}
