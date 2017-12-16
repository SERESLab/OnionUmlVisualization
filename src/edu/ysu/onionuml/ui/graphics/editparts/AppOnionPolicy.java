package edu.ysu.onionuml.ui.graphics.editparts;

import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.AbstractEditPolicy;

/*
 * Edit policy to make a Collapse Function appear on right click context menu
 * To see tutorial I was trying to follow go here: 
 * https://www.inf.ed.ac.uk/teaching/courses/ip/resources/GEF/GEF_Tutorial_2up.pdf
 */

public class AppOnionPolicy extends AbstractEditPolicy {

	public Command getCommand(Request request) {
		if (request.getType().equals("collapse")) {
			return null;//createCollapseCommand(request);
		}
		return null;
}

}
