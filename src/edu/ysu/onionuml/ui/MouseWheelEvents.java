package edu.ysu.onionuml.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.*;

import java.util.ArrayList;
import java.util.List;




import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.editparts.ScalableRootEditPart;
import org.eclipse.gef.ui.parts.GraphicalEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

import edu.ysu.onionuml.ui.ModelViewer;
import edu.ysu.onionuml.ui.graphics.graphicalmodels.ClassDiagramGraphicalModel;
import edu.ysu.onionuml.ui.handler.OpenClassModelCommandHandler;

@SuppressWarnings("javadoc")
public abstract class MouseWheelEvents extends GraphicalEditor implements 
MouseWheelListener {
	
	/**
	 * The id of the editor.
	 */
	public static final String ID = "edu.ysu.onionuml.ui.modelviewer";
	
	
	// PRIVATE MEMBER VARIABLES ---------------------------
	
	private ClassDiagramGraphicalModel mModel;
	private ModelViewerInput mEditorInput;
	
	
	// Constant ---------------
	
	private double scalingFactor = 0.2;
	
	// CONSTRUCTOR
	
		/**
		 * Constructs a new Viewer object, using the default edit domain.
		 */
	
	public MouseWheelEvents() {
		setEditDomain(new DefaultEditDomain(this));
		this.addMouseWheelListener(new ZoomHandler());
		//This code is not working now, it should be listing to the current active page
		//addListener();
    }
    
	private void addMouseWheelListener(ZoomHandler zoomHandler) {
		// TODO Auto-generated method stub
		
	}

	private class ZoomHandler implements MouseWheelListener {
    public void mouseWheelMoved(MouseWheelEvent e) {
    	double zoom = 1;
    	
    	//Determine if the user has hold down the Crtl key
        if( e.isControlDown()){
        	
        	// Determine the new scaling level
        	//double zoom = getScaling();
        	IWorkbenchPage p = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
                if (e.getWheelRotation() != MouseWheelEvent.WHEEL_UNIT_SCROLL){
                	return;
                }
                if (e.getWheelRotation()< 0){
                	increaseZoom(getPage(), true);
                }
                else{
                	decreaseZoom(getPage(), true);
                }
          
            
                
                    
        }
     }
  }
}
	
	
	