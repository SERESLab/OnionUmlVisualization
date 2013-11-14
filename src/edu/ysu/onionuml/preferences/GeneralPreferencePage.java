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
 * This is the top-level preference page as defined in the 
 * plugin.xml file and is intended for general settings.  
 * Currently, it doesn't have any FieldEditors and is only 
 * used as a placeholder container for the more specific 
 * settings pages.
 */

public class GeneralPreferencePage
	extends FieldEditorPreferencePage
	implements IWorkbenchPreferencePage {

	/**
	 * This builds the main page for the preference page.
	 */
	public GeneralPreferencePage() {
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("See subpages for settings");
	}
	
	/**
	 * Creates the field editors. Field editors are abstractions of
	 * the common GUI blocks needed to manipulate various types
	 * of preferences. Each field editor knows how to save and
	 * restore itself.
	 */
	public void createFieldEditors() {
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}
	
}