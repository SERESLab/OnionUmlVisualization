package edu.ysu.onionuml.ui;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
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
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

import edu.ysu.onionuml.core.UmlClassElement;
import edu.ysu.onionuml.core.UmlClassModel;
import edu.ysu.onionuml.core.UmlPackageElement;
import edu.ysu.onionuml.ui.graphics.graphicalmodels.ClassDiagramGraphicalModel;
import edu.ysu.onionuml.ui.graphics.editparts.ClassDiagramEditPart;


/**
 * This is the class the builds the search dialog window.
 *
 */
public class SearchDialog extends Window {
	private static final String SEARCH_BUTTON_TEXT = "Search";
	private static final String CLOSE_BUTTON_TEXT = "Close";
	
	private static final int PADDING = 10;
	private static final String TEXT_CLASSES_CONTROLLER = "View Classes";
	private static final String TEXT_SELECT_ALL = "View All";
	private static final String TEXT_SELECT_NONE = "View None";
	
	private Table mClassTable;
	private UmlClassModel mModel = null;

	/**
	 * @param parentShell
	 */
	public SearchDialog(Shell parentShell) {
		super(parentShell);

		IEditorPart editorPart = PlatformUI.getWorkbench().
				getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		if (editorPart instanceof ModelViewer)
			mModel = ((ModelViewer) editorPart).getModel().getClassModel();
	}

	public Control createContents(Composite parent) {
		parent.setLayout(new GridLayout());

		//Everything in this should have fixed size.
		Composite fixedSize = new Composite(parent, SWT.PUSH);
		fixedSize.setLayoutData(new GridData(GridData.FILL, GridData.BEGINNING,
				true, false));
		fixedSize.setLayout(new FillLayout(SWT.VERTICAL));
		
		//Search input box.
		final Text searchBox = new Text(fixedSize, SWT.SEARCH);

		//Search button and close button.
		Composite buttons_container = new Composite(fixedSize, 0);
		buttons_container.setLayout(new FillLayout(SWT.HORIZONTAL));

		Button search_button = new Button(buttons_container, SWT.PUSH);
		search_button.setText(SEARCH_BUTTON_TEXT);
		search_button.addMouseListener(new MouseListener() {
			@Override
			public void mouseDoubleClick(MouseEvent event) {
				//Do nothing.
			}

			@Override
			public void mouseDown(MouseEvent event) {
				//Do nothing.
			}

			@Override
			public void mouseUp(MouseEvent event) {
				populateClassTable(searchBox.getText());
			}
		});

		Button close_button = new Button(buttons_container, SWT.PUSH);
		close_button.setText(CLOSE_BUTTON_TEXT);
		final SearchDialog self = this;
		close_button.addMouseListener(new MouseListener() {
			@Override
			public void mouseDoubleClick(MouseEvent event) {
				//Do nothing.
			}

			@Override
			public void mouseDown(MouseEvent event) {
				//Do nothing.
			}

			@Override
			public void mouseUp(MouseEvent event) {
				self.close();
			}
		});

		//Everything in this should have dynamic size.
		Composite dynamicSize = new Composite(parent, SWT.PUSH);
		dynamicSize.setLayoutData(new GridData(GridData.FILL, GridData.FILL,
				true, true));
		dynamicSize.setLayout(new FillLayout(SWT.VERTICAL));
		
		//Search results.
		createClassFilterListBoxController(dynamicSize);
		populateClassTable();

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

	private void populateClassTable() {
		populateClassTable("");
	}

	private void populateClassTable(String searchTerm) {
		searchTerm = searchTerm.toLowerCase();
		mClassTable.removeAll();
		
		//Get all classes in all packages and add them to the class table using
		//fully qualified names.
		Map<String, UmlPackageElement> packages = mModel.getPackages();
		Iterator<Entry<String, UmlPackageElement>> packagesIter =
				packages.entrySet().iterator();
		while (packagesIter.hasNext())
		{
			UmlPackageElement curPackage = packagesIter.next().getValue();
			Iterator<Entry<String, UmlClassElement>> classesIter =
					curPackage.getClasses().entrySet().iterator();
			while (classesIter.hasNext())
			{
				UmlClassElement curClass = classesIter.next().getValue();
				String fullyQualifiedName = curPackage.getName() + '.' +
					curClass.getName();
				if (!fullyQualifiedName.toLowerCase().contains(searchTerm))
					continue;

				TableItem item = new TableItem(mClassTable, SWT.NONE);
				item.setText(fullyQualifiedName);
				item.setChecked(true);
			}
		}
	}

}
