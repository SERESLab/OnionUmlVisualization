package edu.ysu.onionuml.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.swt.graphics.RGB;

import edu.ysu.onionuml.Activator;

/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		store.setDefault(PreferenceConstants.P_SHOW_STEREOTYPES, true);
		store.setDefault(PreferenceConstants.P_USE_STEREOTYPE_COLORS, true);
		store.setDefault(PreferenceConstants.P_SHOW_METHODS, true);
		store.setDefault(PreferenceConstants.P_SHOW_FIELDS, true);
		store.setDefault(PreferenceConstants.P_LAYOUT_ALGORITHM, 
				PreferenceConstants.PVAL_HIERARCHICAL_LAYOUT);
		PreferenceConverter.setDefault(store,PreferenceConstants.P_CLASS_COLOR,
				new RGB(255,255,226));
		PreferenceConverter.setDefault(store,PreferenceConstants.P_BOUNDARY_CLASS_COLOR,
				new RGB(204,236,255));
		PreferenceConverter.setDefault(store,PreferenceConstants.P_ENTITY_CLASS_COLOR,
				new RGB(225,255,226));
		PreferenceConverter.setDefault(store,PreferenceConstants.P_CONTROL_CLASS_COLOR,
				new RGB(255,204,204));		
	}

}
