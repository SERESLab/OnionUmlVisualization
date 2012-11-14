package com.onionuml.visplugin.ui;

import org.eclipse.gef.ui.actions.ActionBarContributor;
import org.eclipse.gef.ui.actions.ZoomComboContributionItem;
import org.eclipse.jface.action.IToolBarManager;

/**
 * Extends ActionBarContributor to add toolbar contributions.
 */
public class ModelViewerActionBarContributor extends ActionBarContributor {
	
	@Override
	public void contributeToToolBar(IToolBarManager toolBarManager) {
		super.contributeToToolBar(toolBarManager);
		toolBarManager.add(new ZoomComboContributionItem(getPage()));
	}

	@Override
	protected void buildActions() {
	}

	@Override
	protected void declareGlobalActionKeys() {
	}
	
}