package com.onionuml.visplugin.ui;

import java.util.HashMap;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import com.onionuml.visplugin.ui.graphics.graphicalmodels.ClassDiagramGraphicalModel;

/**
 * Models a lightweight input to a ModelViewer by maintaining a
 * static collection of diagrams and storing only integer IDs in
 * each instance of this class.
 */
public class ModelViewerInput implements IEditorInput {
	
	private static HashMap<Integer, ClassDiagramGraphicalModel> mModelList = new HashMap<Integer, ClassDiagramGraphicalModel>();
	private static int mNextId = 1;

	private final int mId;

	// PUBLIC METHODS -----------------------------

	/**
	 * Constructs a new ModelViewerInput from the given diagram.
	 */
	public ModelViewerInput(ClassDiagramGraphicalModel model) {

		mId = mNextId++;
		mModelList.put(Integer.valueOf(mId), model);
	}

	/**
	 * Gets a unique integer ID specifying the model this object represents.
	 */
	public int getId() {
		return mId;
	}

	/**
	 * Returns the class model respresented by this ModelViewerInput object.
	 * 
	 * @throws RuntimeException
	 *             if the dispose() method has already been called.
	 */
	public ClassDiagramGraphicalModel getModel() {
		try {
			return mModelList.get(Integer.valueOf(mId));
		} catch (Exception e) {
			throw new RuntimeException("Model has already been disposed.");
		}
	}

	/**
	 * Removes internal references to the model and invalidates its ID.
	 */
	public void dispose() {
		mModelList.remove(Integer.valueOf(mId));
	}

	// OVERRIDE METHODS -----------------------------

	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (getClass() != o.getClass()) {
			return false;
		}
		if (mId != ((ModelViewerInput) o).getId()) {
			return false;
		}
		return (this == o);
	}

	@Override
	public boolean exists() {
		return true;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		return null;
	}

	@Override
	public String getName() {
		return String.valueOf(mId);
	}

	@Override
	public IPersistableElement getPersistable() {
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object getAdapter(Class adapter) {
		return null;
	}

	@Override
	public String getToolTipText() {
		return getModel().getClassModel().getDescription();
	}
}
