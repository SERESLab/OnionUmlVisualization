package edu.ysu.onionuml.ui;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class SearchDialog extends Window {
	private static final String SEARCH_BUTTON_TEXT = "Search";
	private static final String CLOSE_BUTTON_TEXT = "Close";

	public SearchDialog(Shell parentShell) {
		super(parentShell);
	}

	public Control createContents(Composite parent) {
		parent.setLayout(new RowLayout());

		//TODO: Search input box.

		//Search button and close button.
		Composite buttons_container = new Composite(parent, 0);
		buttons_container.setLayout(new FillLayout(SWT.HORIZONTAL));

		Button search_button = new Button(parent, SWT.PUSH);
		search_button.setText(SEARCH_BUTTON_TEXT);

		Button close_button = new Button(parent, SWT.PUSH);
		close_button.setText(CLOSE_BUTTON_TEXT);

		//TODO: Search results.

		return parent;
	}
}
