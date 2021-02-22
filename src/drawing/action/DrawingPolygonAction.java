package drawing.action;

import static drawing.DrawingConstants.ACTION_DRAW_POLYGON;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import drawing.ui.DrawingBoard;
import drawing.ui.DrawingBoard.Draw_Mode;

public class DrawingPolygonAction extends AbstractDrawingAction {

	public DrawingPolygonAction(DrawingBoard board) {
		super(ACTION_DRAW_POLYGON, new ImageIcon(
				"src/drawing/resource/polygon.PNG"), board);
	}

	public void actionPerformed(ActionEvent e) {
		board.setDrawMode(Draw_Mode.DRAW_POLYGON);
	}

}
