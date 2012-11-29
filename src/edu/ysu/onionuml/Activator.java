package edu.ysu.onionuml;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * Controls the life cycle of the plugin and contains plugin-wide constants.
 */
public class Activator extends AbstractUIPlugin{
	
	
	// PUBLIC MEMBER VARIABLES -----------------------------
	
	/**
	 * ID by which this plugin is referenced.
	 */
	public static final String PLUGIN_ID = "edu.ysu.onionuml";

	
	
	
	// PRIVATE MEMBER VARIABLES -----------------------------
	
	private static Activator mPlugin;
	
	
	
	
	// PUBLIC METHODS ---------------------------------
	
	/**
	 * Constructs a new Activator for the plugin.
	 */
	public Activator() {
	}
	
	
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		mPlugin = this;
	}
	
	
	@Override
	public void stop(BundleContext context) throws Exception {
		mPlugin = null;
		super.stop(context);
	}
	
	
	/**
	 * Gets a shared, static instance of the plugin.
	 */
	public static Activator getDefault() {
		return mPlugin;
	}
	
	
	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path.
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
}
