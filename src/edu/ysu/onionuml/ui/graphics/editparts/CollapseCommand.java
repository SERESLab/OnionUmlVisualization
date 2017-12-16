package edu.ysu.onionuml.ui.graphics.editparts;

import org.eclipse.gef.commands.Command;

import edu.ysu.onionuml.ui.DiagramControlView;

/*
 * Command to make a Collapse Function appear on right click context menu
 * To see tutorial I was trying to follow go here: 
 * https://www.inf.ed.ac.uk/teaching/courses/ip/resources/GEF/GEF_Tutorial_2up.pdf
 */
public class CollapseCommand extends Command {
	public void execute() {
		 DiagramControlView.onCompactSelectedPressed();
		 }
}
