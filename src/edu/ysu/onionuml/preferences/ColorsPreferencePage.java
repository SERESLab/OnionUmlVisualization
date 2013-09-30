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
 * This preference page is to be used for setting the color
 * schema of the onion UML diagram.
 */

public class ColorsPreferencePage
	extends FieldEditorPreferencePage
	implements IWorkbenchPreferencePage {

	public ColorsPreferencePage() {
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("Select Background Colors to Use For Classes");
	}
	
	/**
	 * Creates the field editors. Field editors are abstractions of
	 * the common GUI blocks needed to manipulate various types
	 * of preferences. Each field editor knows how to save and
	 * restore itself.
	 */
	public void createFieldEditors() {
		addField(
			new ColorFieldEditor(
				PreferenceConstants.P_CLASS_COLOR,
				"&General class color",
				getFieldEditorParent()
			)
		);
		addField(
			new BooleanFieldEditor(
				PreferenceConstants.P_USE_STEREOTYPE_COLORS,
				"&Show entity/control/boundary classes in different colors",
				getFieldEditorParent()
			)
		);
		addField(
			new ColorFieldEditor(
				PreferenceConstants.P_ENTITY_CLASS_COLOR,
				"&Entity stereotype class color",
				getFieldEditorParent()
			)
		);
		addField(
			new ColorFieldEditor(
				PreferenceConstants.P_CONTROL_CLASS_COLOR,
				"&Control stereotype class color",
				getFieldEditorParent()
			)
		);
		addField(
				new ColorFieldEditor(
					PreferenceConstants.P_BOUNDARY_CLASS_COLOR,
					"&Boundary stereotype class color",
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