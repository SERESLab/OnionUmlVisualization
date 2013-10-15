package edu.ysu.onionuml.preferences;

import org.eclipse.jface.preference.*;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.IWorkbench;
import edu.ysu.onionuml.Activator;

/**
 * This class builds a preference page using the built-in
 * Eclipse FieldEditorPreferencePage class. The page can
 * be accessed from the Eclipse Menu-bar using
 * Windows->Preferences->OnionUmlVisualization.  All preferences
 * are stored in the Eclipse Preferences Store.
 * <p> 
 * This preference page is to be used for setting the
 * visibility of different elements on the onion UML diagram.
 */

public class VisibilityPreferencePage
	extends FieldEditorPreferencePage
	implements IWorkbenchPreferencePage {

	public VisibilityPreferencePage() {
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("");
	}
	
	/**
	 * Creates the field editors. Field editors are abstractions of
	 * the common GUI blocks needed to manipulate various types
	 * of preferences. Each field editor knows how to save and
	 * restore itself.
	 */
	public void createFieldEditors() {
		addField(
			new BooleanFieldEditor(
				PreferenceConstants.P_SHOW_STEREOTYPES,
				"&Show Stereotypes",
				getFieldEditorParent()
			)
		);
		addField(
			new BooleanFieldEditor(
				PreferenceConstants.P_SHOW_PRIVATE_ELEMENTS,
				"&Show Private Fields and Methods",
				getFieldEditorParent()
			)
		);
		addField(
			new BooleanFieldEditor(
				PreferenceConstants.P_SHOW_PRIVATE_METHODS,
				"&Show Private Methods",
				getFieldEditorParent()
			)
		);
		addField(
			new BooleanFieldEditor(
				PreferenceConstants.P_SHOW_UML,
				"&Show UML",
				getFieldEditorParent()
			)
		);
		addField(
			new BooleanFieldEditor(
			PreferenceConstants.P_SHOW_PRIVATE_ELEMENTS,
				"&Show Onion",
				getFieldEditorParent()
			)
		);

	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}
	
}