package edu.ysu.onionuml.ui.handler;

import edu.ysu.onionuml.ui.SearchDialog;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * Command that opens the search dialog box.
 * 
 * @see org.eclipse.core.commands.IHandler
 */
public class OpenSearchCommandHandler implements IHandler {
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Shell shell = new Shell(Display.getDefault());
		(new SearchDialog(shell)).open();

		return null;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean isHandled() {
		return true;
	}

	@Override
	public void addHandlerListener(IHandlerListener handlerListener) {
	}

	@Override
	public void removeHandlerListener(IHandlerListener handlerListener) {
	}

	@Override
	public void dispose() {
	}
}
