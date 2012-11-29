package edu.ysu.onionuml.ui.graphics;

/**
 * Defines a class that can listen for events.
 */
public interface IEventListener {
	
	/**
	 * Responds to the event specified by evt.
	 */
	public void eventOccured(String evt);
}
