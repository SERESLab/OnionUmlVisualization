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
 * This preference page is to be used for setting the layout
 * of the onion UML diagram.
 */

public class LayoutPreferencePage
	extends FieldEditorPreferencePage
	implements IWorkbenchPreferencePage {

	/**
	 * This builds the layout preference page.
	 */
	public LayoutPreferencePage() {
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("Select the layout to use when loading new diagrams");
	}
	
	/**
	 * Creates the field editors. Field editors are abstractions of
	 * the common GUI blocks needed to manipulate various types
	 * of preferences. Each field editor knows how to save and
	 * restore itself.
	 */
	public void createFieldEditors() {
		addField(new RadioGroupFieldEditor(
				PreferenceConstants.P_LAYOUT_ALGORITHM,
		        "(note: layout changes will not be applied to diagrams that are already open)", 1,
		        new String[][] { { "Hierarchical", PreferenceConstants.PVAL_HIERARCHICAL_LAYOUT },
		            { "Multicluster", PreferenceConstants.PVAL_MULTICLUSTER_LAYOUT } }, getFieldEditorParent()));
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}
	
}
