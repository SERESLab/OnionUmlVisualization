package com.onionuml.visplugin.ui.graphics;

/**
 * Defines a class that can register and unregister event listeners.
 */
public interface IEventRegistrar {
	
	/**
	 * Begins listening for model events using the specified listener.
	 */
	public void registerEventListener(IEventListener listener);
	
	/**
	 * Stops using the specified listener.
	 */
	public void unregisterEventListener();
}
