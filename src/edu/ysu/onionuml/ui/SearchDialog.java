package edu.ysu.onionuml.ui;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import edu.ysu.onionuml.core.UmlClassElement;
import edu.ysu.onionuml.core.UmlClassModel;
import edu.ysu.onionuml.core.UmlPackageElement;
import edu.ysu.onionuml.ui.graphics.graphicalmodels.ClassDiagramGraphicalModel;
import edu.ysu.onionuml.ui.graphics.editparts.ClassDiagramEditPart;


public class SearchDialog extends Window {
	private static final String SEARCH_BUTTON_TEXT = "Search";
	private static final String CLOSE_BUTTON_TEXT = "Close";
	
	private static final int PADDING = 10;
	private static final String TEXT_CLASSES_CONTROLLER = "View Classes";
	private static final String TEXT_SELECT_ALL = "View All";
	private static final String TEXT_SELECT_NONE = "View None";
	
	private Table mClassTable;
	private ClassDiagramEditPart mCurrentClassDiagram = null;

	public SearchDialog(Shell parentShell) {
		super(parentShell);
	}

	public Control createContents(Composite parent) {
		parent.setLayout(new FillLayout(SWT.VERTICAL));

		//Search input box.
		Text searchBox = new Text(parent, SWT.SEARCH);

		//Search button and close button.
		Composite buttons_container = new Composite(parent, 0);
		buttons_container.setLayout(new FillLayout(SWT.HORIZONTAL));

		Button search_button = new Button(buttons_container, SWT.PUSH);
		search_button.setText(SEARCH_BUTTON_TEXT);

		Button close_button = new Button(buttons_container, SWT.PUSH);
		close_button.setText(CLOSE_BUTTON_TEXT);

		//Search results.
		createClassFilterListBoxController(parent);

		parent.pack();
		return parent;
	}
	
	/*
	 * 
	 * Class Filter List Box
	 */
	
	//TODO: put in dummy data
	
	private Composite createClassFilterListBoxController(Composite parent){
		Group classControllerGroup = new Group(parent, SWT.SHADOW_NONE);
		classControllerGroup.setText(TEXT_CLASSES_CONTROLLER);
		classControllerGroup.setLayout(new FormLayout());
		
		Composite buttonGroup = new Composite(classControllerGroup, SWT.NONE);
		buttonGroup.setLayout(new RowLayout(SWT.HORIZONTAL));
		
		Button selectAllButton = new Button(buttonGroup, SWT.PUSH);
		selectAllButton.setText(TEXT_SELECT_ALL);
		selectAllButton.setLayoutData(new RowData());
		
		Button selectNoneButton = new Button(buttonGroup, SWT.PUSH);
		selectNoneButton.setText(TEXT_SELECT_NONE);
		selectNoneButton.setLayoutData(new RowData());
		
		mClassTable = new Table(classControllerGroup,
				SWT.CHECK | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		FormData data = new FormData();
		data.top = new FormAttachment(buttonGroup);
		data.left = new FormAttachment(0, PADDING);
		data.right = new FormAttachment(100 -PADDING);
		data.bottom = new FormAttachment(100, -PADDING);
		mClassTable.setLayoutData(data);
		
		return classControllerGroup;
	}

	private void populateClassTable(){		
		mClassTable.removeAll();
		UmlClassModel model = ((ClassDiagramGraphicalModel)mCurrentClassDiagram.getModel())
				.getClassModel();
		
		Map<String,UmlClassElement> classes = model.getClasses();
		Iterator<Entry<String,UmlClassElement>>  itClasses = classes.entrySet().iterator();
		while (itClasses.hasNext()) {
			Entry<String,UmlClassElement> classPairs = 
					(Entry<String,UmlClassElement>)itClasses.next();
			UmlClassElement c = classPairs.getValue();
			TableItem item = new TableItem(mClassTable, SWT.NONE);
			item.setText(c.getName());
			item.setChecked(true);
		}
	}

}
