package edu.ysu.onionuml.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.part.ViewPart;

/**
 * ViewPart for controlling the visualization of the UML diagram.
 */
public class DiagramControlView extends ViewPart {

	// CONSTANTS -----------------------
	private static final int PADDING = 10;
	private static final String TEXT_PACKAGES_CONTROLLER = "View Packages";
	private static final String TEXT_COMPACTION_CONTROLLER = "Onion Compaction";
	private static final String TEXT_SELECT_ALL = "View All";
	private static final String TEXT_SELECT_NONE = "View None";
	private static final String TEXT_COMPACT_SELECTED = "Compact Selected";
	private static final String TEXT_EXPAND_SELECTED = "Expand Selected";
	private static final String TEXT_COMPACT_ALL = "Compact All";
	private static final String TEXT_EXPAND_ALL = "Expand All";
	private static final String TEXT_RESET_ALL = "Reset All";
	
	
	
	
	// OVERRIDE METHODS --------------------------------

	@Override
	public void createPartControl(Composite parent) {
		
		parent.setLayout(new FillLayout(SWT.HORIZONTAL));
		createPackagesController(parent);
		createCompactionController(parent);
	}

	@Override
	public void setFocus() {
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
		
		Button selectNoneButton = new Button(buttonGroup, SWT.PUSH);
		selectNoneButton.setText(TEXT_SELECT_NONE);
		selectNoneButton.setLayoutData(new RowData());
		
		Table packageTable = new Table(packageControllerGroup,
				SWT.CHECK | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		FormData data = new FormData();
		data.top = new FormAttachment(buttonGroup);
		data.left = new FormAttachment(0, PADDING);
		data.right = new FormAttachment(100 -PADDING);
		data.bottom = new FormAttachment(100, -PADDING);
		packageTable.setLayoutData(data);
		
		for (int i = 0; i < 8; i++) {
			TableItem item = new TableItem(packageTable, SWT.NONE);
			/* temp */ item.setText("com.package." + i);
		}
		
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
		
		Button expandSelected = new Button(selectedButtonGroup, SWT.PUSH);
		expandSelected.setText(TEXT_EXPAND_SELECTED);
		expandSelected.setLayoutData(new RowData());
		
		// button group for operations on all classes
		Composite allButtonGroup = new Composite(compactionControllerGroup, SWT.NONE);
		layout = new RowLayout(SWT.VERTICAL);
		layout.fill = true;
		allButtonGroup.setLayout(layout);
		allButtonGroup.setLayoutData(new RowData());
		
		Button compactAll = new Button(allButtonGroup, SWT.PUSH);
		compactAll.setText(TEXT_COMPACT_ALL);
		compactAll.setLayoutData(new RowData());
		
		Button expandAll = new Button(allButtonGroup, SWT.PUSH);
		expandAll.setText(TEXT_EXPAND_ALL);
		expandAll.setLayoutData(new RowData());
		
		Button resetAll = new Button(allButtonGroup, SWT.PUSH);
		resetAll.setText(TEXT_RESET_ALL);
		resetAll.setLayoutData(new RowData());
		
		return compactionControllerGroup;
	}
}
