package edu.ysu.onionuml.ui;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.prefs.Preferences;

import javax.swing.JOptionPane;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.part.ViewPart;

import edu.ysu.onionuml.core.UmlClassElement;
import edu.ysu.onionuml.core.UmlClassModel;
import edu.ysu.onionuml.core.UmlPackageElement;
import edu.ysu.onionuml.preferences.VisibilityPreferencePage;
import edu.ysu.onionuml.ui.graphics.editparts.ClassDiagramEditPart;
import edu.ysu.onionuml.ui.graphics.editparts.ClassElementEditPart;
import edu.ysu.onionuml.ui.graphics.graphicalmodels.ClassDiagramGraphicalModel;
import edu.ysu.onionuml.ui.graphics.graphicalmodels.ClassElementGraphicalModel;

/**
 * ViewPart for controlling the visualization of a UML diagram.
 */
public class DiagramControlView extends ViewPart {

	// CONSTANTS -----------------------
	
	/**
	 * The ID of the view.
	 */
	public static final String ID = "edu.ysu.onionuml.ui.diagramcontrolview";
	
	private static final int PADDING = 10;
	private static final String TEXT_PACKAGES_CONTROLLER = "View Packages";
	private static final String TEXT_COMPACTION_CONTROLLER = "Onion Compaction";
	private static final String TEXT_VISIBILITY_PREFERENCE_CONTROLLER = "Visibility Preference";
	private static final String TEXT_SELECT_ALL = "View All";
	private static final String TEXT_SELECT_NONE = "View None";
	private static final String TEXT_COMPACT_SELECTED = "Compact Selected";
	private static final String TEXT_EXPAND_SELECTED = "Expand Selected";
	private static final String TEXT_COMPACT_ALL = "Compact All";
	private static final String TEXT_EXPAND_ALL = "Expand All";
	private static final String TEXT_RESET_ALL = "Reset All";
	private static final String TEXT_NO_DIAGRAM = "No class diagram currently in view.";
	private static final String TEXT_UPDATE_DIAGRAM = "Update Diagram";
	
	private Table mPackageTable;
	private Composite mDefaultView;
	private Composite mPackagesView;
	private Composite mCompactionControlView;
	private Composite mParentComposite;
	private Composite mVisibilityPreference;
	private ClassDiagramEditPart mCurrentClassDiagram = null;
	
	// PUBLIC METHODS --------------------------------

	@Override
	public void createPartControl(Composite parent) {
		
		parent.setLayout(new FillLayout(SWT.HORIZONTAL));
		mParentComposite = parent;
		
	}

	@Override
	public void setFocus() {
	}
	
	/**
	 * Sets the current class diagram to the specified diagram, or null to disable
	 * control of any diagram.
	 * @param diagram 
	 */
	public void setCurrentClassDiagram(ClassDiagramEditPart diagram){
		mCurrentClassDiagram = diagram;
		
		if(diagram != null){
			if(mPackagesView == null && mCompactionControlView == null){
				mPackagesView = createPackagesController(mParentComposite);
				mCompactionControlView = createCompactionController(mParentComposite);
				mVisibilityPreference = createVisibilityPreference(mParentComposite);
				populatePackageTable();
			}
			if(mDefaultView != null){
				mDefaultView.dispose();
				mDefaultView = null;
			}
		}
		else{
			if(mPackagesView != null && mCompactionControlView != null){
				mPackagesView.dispose();
				mCompactionControlView.dispose();
				mVisibilityPreference.dispose();
				mPackagesView = null;
				mCompactionControlView = null;
				mVisibilityPreference = null;
			}
			if(mDefaultView == null){
				mDefaultView = createDefaultView(mParentComposite);
			}
		}
		
		mParentComposite.pack(true);
	}
	
	/**
	 * Returns the current class diagram that the view is currently controlling
	 * or null if the view is not controlling any diagram.
	 * @return This returns the current class diagram. 
	 */
	public ClassDiagramEditPart getCurrentClassDiagram(){
		return mCurrentClassDiagram;
	}
	
	
	// PRIVATE METHODS ---------------------------------------------
	
	/*
	 * Creates the composite for displaying package view options.
	 */
	private Composite createPackagesController(Composite parent){
		Group packageControllerGroup = new Group(parent, SWT.SHADOW_NONE);
		packageControllerGroup.setText(TEXT_PACKAGES_CONTROLLER);
		packageControllerGroup.setLayout(new FormLayout());
		
		Composite buttonGroup = new Composite(packageControllerGroup, SWT.NONE);
		buttonGroup.setLayout(new RowLayout(SWT.HORIZONTAL));
		
		Button selectAllButton = new Button(buttonGroup, SWT.PUSH);
		selectAllButton.setText(TEXT_SELECT_ALL);
		selectAllButton.setLayoutData(new RowData());
		selectAllButton.addMouseListener(new MouseListener(){
			@Override
			public void mouseDoubleClick(MouseEvent e) {}
			@Override
			public void mouseDown(MouseEvent e) {}
			@Override
			public void mouseUp(MouseEvent e) {
				onViewAllPressed();
			}
		});
		
		Button selectNoneButton = new Button(buttonGroup, SWT.PUSH);
		selectNoneButton.setText(TEXT_SELECT_NONE);
		selectNoneButton.setLayoutData(new RowData());
		selectNoneButton.addMouseListener(new MouseListener(){
			@Override
			public void mouseDoubleClick(MouseEvent e) {}
			@Override
			public void mouseDown(MouseEvent e) {}
			@Override
			public void mouseUp(MouseEvent e) {
				onViewNonePressed();
			}
		});
		
		Button updateDiagramButton = new Button(buttonGroup, SWT.PUSH);
		updateDiagramButton.setText(TEXT_UPDATE_DIAGRAM);
		updateDiagramButton.setLayoutData(new RowData());
		updateDiagramButton.addMouseListener(new MouseListener(){
			@Override
			public void mouseDoubleClick(MouseEvent e) {}
			@Override
			public void mouseDown(MouseEvent e) {}
			@Override
			public void mouseUp(MouseEvent e) {
				onUpdateDiagramPressed();
			}
		});
		
		mPackageTable = new Table(packageControllerGroup,
				SWT.CHECK | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		FormData data = new FormData();
		data.top = new FormAttachment(buttonGroup);
		data.left = new FormAttachment(0, PADDING);
		data.right = new FormAttachment(100 -PADDING);
		data.bottom = new FormAttachment(100, -PADDING);
		mPackageTable.setLayoutData(data);
		
		return packageControllerGroup;
	}
	
	/*
	 * Creates the composite for displaying onion compaction options.
	 */
	private Composite createCompactionController(Composite parent){
		Group compactionControllerGroup = new Group(parent, SWT.VERTICAL);
		compactionControllerGroup.setText(TEXT_COMPACTION_CONTROLLER);
		
		RowLayout layout = new RowLayout(SWT.HORIZONTAL);
		layout.marginLeft = PADDING;
		layout.marginRight = PADDING;
		compactionControllerGroup.setLayout(layout);
		
		// button group for operations on selected classes
		Composite selectedButtonGroup = new Composite(compactionControllerGroup, SWT.NONE);
		layout = new RowLayout(SWT.VERTICAL);
		layout.fill = true;
		selectedButtonGroup.setLayout(layout);
		selectedButtonGroup.setLayoutData(new RowData());
		
		Button compactSelected = new Button(selectedButtonGroup, SWT.PUSH);
		compactSelected.setText(TEXT_COMPACT_SELECTED);
		compactSelected.setLayoutData(new RowData());
		compactSelected.addMouseListener(new MouseListener(){
			@Override
			public void mouseDoubleClick(MouseEvent e) {}
			@Override
			public void mouseDown(MouseEvent e) {}
			@Override
			public void mouseUp(MouseEvent e) {
				onCompactSelectedPressed();
			}
		});
		
		Button expandSelected = new Button(selectedButtonGroup, SWT.PUSH);
		expandSelected.setText(TEXT_EXPAND_SELECTED);
		expandSelected.setLayoutData(new RowData());
		expandSelected.addMouseListener(new MouseListener(){
			@Override
			public void mouseDoubleClick(MouseEvent e) {}
			@Override
			public void mouseDown(MouseEvent e) {}
			@Override
			public void mouseUp(MouseEvent e) {
				onExpandSelectedPressed();
			}
		});
		
		// button group for operations on all classes
		Composite allButtonGroup = new Composite(compactionControllerGroup, SWT.NONE);
		layout = new RowLayout(SWT.VERTICAL);
		layout.fill = true;
		allButtonGroup.setLayout(layout);
		allButtonGroup.setLayoutData(new RowData());
		
		Button compactAll = new Button(allButtonGroup, SWT.PUSH);
		compactAll.setText(TEXT_COMPACT_ALL);
		compactAll.setLayoutData(new RowData());
		compactAll.addMouseListener(new MouseListener(){
			@Override
			public void mouseDoubleClick(MouseEvent e) {}
			@Override
			public void mouseDown(MouseEvent e) {}
			@Override
			public void mouseUp(MouseEvent e) {
				onCompactAllPressed();
			}
		});
		
		Button expandAll = new Button(allButtonGroup, SWT.PUSH);
		expandAll.setText(TEXT_EXPAND_ALL);
		expandAll.setLayoutData(new RowData());
		expandAll.addMouseListener(new MouseListener(){
			@Override
			public void mouseDoubleClick(MouseEvent e) {}
			@Override
			public void mouseDown(MouseEvent e) {}
			@Override
			public void mouseUp(MouseEvent e) {
				onExpandAllPressed();
			}
		});
		
		Button resetAll = new Button(allButtonGroup, SWT.PUSH);
		resetAll.setText(TEXT_RESET_ALL);
		resetAll.setLayoutData(new RowData());
		
		return compactionControllerGroup;
	}
	
	/*
	 * Creates the composite for displaying Visibility Preference options.
	 */	
	private Composite createVisibilityPreference(Composite parent){
		Group createVisibilityPreference = new Group(parent, SWT.VERTICAL);
		createVisibilityPreference.setText(TEXT_VISIBILITY_PREFERENCE_CONTROLLER);
	
		RowLayout layout = new RowLayout(SWT.HORIZONTAL);
		layout.marginLeft = PADDING;
		layout.marginRight = PADDING;
		createVisibilityPreference.setLayout(layout);
		
		return createVisibilityPreference;
	}
	
	
	/*
	 * Creates the blank default composite.
	 */
	private Composite createDefaultView(Composite parent){
		Composite comp = new Composite(parent, SWT.NONE);
		comp.setLayout(new FillLayout(SWT.HORIZONTAL));
		Label l = new Label(comp, SWT.NONE);
		l.setText(TEXT_NO_DIAGRAM);
		return comp;
	}
	
	/*
	 * Populates the package table with the packages in the current class diagram.
	 */
	private void populatePackageTable(){
				mPackageTable.removeAll();
		UmlClassModel model = ((ClassDiagramGraphicalModel)mCurrentClassDiagram.getModel())
				.getClassModel();
		
		Map<String,UmlPackageElement> packages = model.getPackages();
		Iterator<Entry<String,UmlPackageElement>>  itPackages = packages.entrySet().iterator();
		while (itPackages.hasNext()) {
			Entry<String,UmlPackageElement> pkgPairs = 
					(Entry<String,UmlPackageElement>)itPackages.next();
			UmlPackageElement p = pkgPairs.getValue();
			TableItem item = new TableItem(mPackageTable, SWT.NONE);
			item.setText(p.getName());
			item.setChecked(true);
		}
	}
	
	/*
	 * Checks all packages from the Package Table when View All is pressed.
	 */
	private void onViewAllPressed(){
		if(mPackageTable != null){
			for(TableItem item : mPackageTable.getItems()){
				item.setChecked(true);
	
			}
		}
	}
	
	/*
	 * Unchecks all packages from the Package Table when View None is pressed.
	 */
	private void onViewNonePressed(){
		if(mPackageTable != null){
			for(TableItem item : mPackageTable.getItems()){
				item.setChecked(false);
				
			}
		}
	}
	
	/*
	 * Update graph after selecting/deselecting packages
	 */
	private void onUpdateDiagramPressed(){
		UmlClassModel model = ((ClassDiagramGraphicalModel)mCurrentClassDiagram.getModel()).getClassModel();
		Map<String,UmlPackageElement> packages = model.getPackages();
		Iterator<Entry<String,UmlPackageElement>>  itPackages = packages.entrySet().iterator();
		while (itPackages.hasNext()) {
			Entry<String,UmlPackageElement> pkgPairs = (Entry<String,UmlPackageElement>)itPackages.next();
			UmlPackageElement p = pkgPairs.getValue();
			for(TableItem item : mPackageTable.getItems()){
				if(item.getText() == p.getName() && item.getChecked() == false){
					for(UmlClassElement c : p.getClasses().values()){
						String s = c.getName();
						JOptionPane.showMessageDialog( null, s,"title",JOptionPane.OK_CANCEL_OPTION); 
					}
				}
			}
		}
		((ClassDiagramGraphicalModel)mCurrentClassDiagram.getModel()).update();
	}
	

	
	/*
	 * Called when the compact selected button is pressed.
	 */
	private void onCompactSelectedPressed(){
		
		if(mCurrentClassDiagram != null){
			for(ClassElementEditPart element : mCurrentClassDiagram.getSelectedClasses()){
				((ClassElementGraphicalModel)element.getModel()).setIsCompacted(true);
			}
			((ClassDiagramGraphicalModel)mCurrentClassDiagram.getModel()).update();
		}
	}
	
	/*
	 * Called when the expand selected button is pressed.
	 */
	private void onExpandSelectedPressed(){
		
		if(mCurrentClassDiagram != null){
			for(ClassElementEditPart element : mCurrentClassDiagram.getSelectedClasses()){
				((ClassElementGraphicalModel)element.getModel()).setIsCompacted(false);
			}
			((ClassDiagramGraphicalModel)mCurrentClassDiagram.getModel()).update();
		}
	}
	
	/*
	 * Called when the compact all button is pressed.
	 */
	private void onCompactAllPressed(){

		((ClassDiagramGraphicalModel)mCurrentClassDiagram.getModel()).update();
	}
	
	/*
	 * Called when the expand all button is pressed.
	 */
	private void onExpandAllPressed(){
	/*	for(){
			//
			//((ClassDiagramEditPart)c.getParent()).removeFromSelection(c); // c is class
		}*/
	}
}


/*   brandis notes
		if(item.getText() == p.getName() && item.getChecked() == false){
			for(UmlClassElement c : p.getClasses().values()){
				String s = c.getName();
				JOptionPane.showMessageDialog( null, s,"title",JOptionPane.OK_CANCEL_OPTION); 
		
	}
}*/
