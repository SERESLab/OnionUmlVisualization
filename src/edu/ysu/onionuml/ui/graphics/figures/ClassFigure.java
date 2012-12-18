package edu.ysu.onionuml.ui.graphics.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;

import edu.ysu.onionuml.core.RelationshipType;

/**
 * Represents a class in a uml class diagram to be displayed with the Eclipse
 * Graphical Editing Framework (GEF).
 */
public class ClassFigure extends Figure {
	
	private ClassSectionFigure mPropertiesFigure;
	private ClassSectionFigure mOperationsFigure;
	private ClassSectionFigure mOnionRelationshipsFigure;
	private Font mContentFont;
	private Label mNameLabel;
	private Label mStereotypeLabel;
	private boolean mIsOnion;
	
	
	/**
	 * Constructs a new ClassFigure with the specified background color
	 * and fonts.
	 * @param bgColor the background color of the class
	 * @param nameFont the font to use for the name label of the class
	 * @param contentFont the font to use for properties and operations
	 * @param stereotypeFont the font to use for the stereotype label
	 */
	public ClassFigure(Color bgColor, Font nameFont, Font contentFont,
			Font stereotypeFont) {
		
		ToolbarLayout layout = new ToolbarLayout();
		setLayoutManager(layout);
		setBorder(new LineBorder(ColorConstants.black, 1));
		setBackgroundColor(bgColor);
		setOpaque(true);
		
		mStereotypeLabel = new Label("");
		mNameLabel = new Label("");
		mStereotypeLabel.setFont(stereotypeFont);
		mNameLabel.setFont(nameFont);
		
		mContentFont = contentFont;
		
		mPropertiesFigure = new ClassSectionFigure(false);
		mOperationsFigure = new ClassSectionFigure(false);
		mOnionRelationshipsFigure = new ClassSectionFigure(true);
		
		reconstruct();
	}
	
	/**
	 * Sets the font of the name label to the specified font.
	 */
	public void setNameFont(Font font){
		mNameLabel.setFont(font);
	}
	
	/**
	 * Sets the text of the name label to the specified string.
	 */
	public void setNameString(String name){
		mNameLabel.setText(name);
	}
	
	/**
	 * Sets the icon of the name label to the specified image.
	 */
	public void setNameIcon(Image icon){
		mNameLabel.setIcon(icon);
	}
	
	/**
	 * Sets the text of the stereotype label to specified string.
	 */
	public void setStereotypeString(String stereotype){
		mStereotypeLabel.setText(stereotype);
		reconstruct();
	}
	
	/**
	 * Adds the specified string and icon to the properties section of the class.
	 * If a font is specified, it will be used instead of the default.
	 */
	public void addProperty(String str, Image icon, Font font) {
		Label l = new Label(str, icon);
		if(font != null){
			l.setFont(font);
		}
		else{
			l.setFont(mContentFont);
		}
		mPropertiesFigure.add(l);
	}
	
	/**
	 * Adds the specified string and icon to the operations section of the class.
	 * If a font is specified, it will be used instead of the default.
	 */
	public void addOperation(String str, Image icon, Font font) {
		Label l = new Label(str, icon);
		if(font != null){
			l.setFont(font);
		}
		else{
			l.setFont(mContentFont);
		}
		mOperationsFigure.add(l);
	}
	
	/**
	 * Clears all properties and operations from the class and resets the name
	 * and stereotype.
	 */
	public void clear(){
		mPropertiesFigure.removeAll();
		mOperationsFigure.removeAll();
		mOnionRelationshipsFigure.removeAll();
		mNameLabel.setText("");
		mNameLabel.setIcon(null);
		mStereotypeLabel.setText("");
		mStereotypeLabel.setIcon(null);
		reconstruct();
	}
	
	/**
	 * Adds a figure to the class figure representing the specified relationship.
	 * Figures are rendered from left to right in the order they are added.
	 */
	public void addOnionRelationship(RelationshipType relationshipType){
		mOnionRelationshipsFigure.add(new OnionRelationshipFigure(relationshipType));
	}
	
	/**
	 * Sets whether the class will be rendered in onion notation.
	 */
	public void setIsOnion(boolean isOnion){
		mIsOnion = isOnion;
	}
	
	// PRIVATE METHODS --------------------------------------------------
	
	private void reconstruct(){
		
		removeAll();
		
		if(mIsOnion){
			add(mOnionRelationshipsFigure);
		}
		else{
			String stereotype = mStereotypeLabel.getText();
			if(stereotype != null && stereotype.length() > 0){
				add(mStereotypeLabel);
			}
			add(mNameLabel);
			add(mPropertiesFigure);
			add(mOperationsFigure);
		}
	}
}
