package edu.ysu.onionuml.ui.graphics.figures;

import org.eclipse.draw2d.AbstractBorder;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Insets;

/**
 * Represents a section of a uml class to be displayed with the Eclipse
 * Graphical Editing Framework (GEF).
 */
public class ClassSectionFigure extends Figure {
	
	private static final int SPACING = 3;
	
	/**
	 * Constructs a new ClassSectionFigure with a top border.
	 * @param isHorizontal set true if the layout should be horizontal,
	 * false otherwise
	 */
	public ClassSectionFigure(boolean isHorizontal) {
		ToolbarLayout layout = new ToolbarLayout();
		layout.setMinorAlignment(ToolbarLayout.ALIGN_TOPLEFT);
		layout.setStretchMinorAxis(false);
		layout.setSpacing(SPACING);
		layout.setHorizontal(isHorizontal);
		setLayoutManager(layout);
		
		setBorder(new AbstractBorder(){

			@Override
			public Insets getInsets(IFigure figure) {
				return new Insets(0, 0, 0, 0);
			}

			@Override
			public void paint(IFigure figure, Graphics graphics, Insets insets) {
				graphics.drawLine(getPaintRectangle(figure, insets).getTopLeft(),
						tempRect.getTopRight());
			}
		});
	}
}
