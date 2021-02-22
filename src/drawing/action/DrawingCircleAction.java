package drawing.action;

import static drawing.DrawingConstants.ACTION_DRAW_CIRCLE;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import drawing.ui.DrawingBoard;
import drawing.ui.DrawingBoard.Draw_Mode;

public class DrawingCircleAction extends AbstractDrawingAction {

	public DrawingCircleAction(DrawingBoard board) {
		super(ACTION_DRAW_CIRCLE, new ImageIcon(
				"src/drawing/resource/circle.PNG"), board);
	}

	public void actionPerformed(ActionEvent e) {
		board.setDrawMode(Draw_Mode.DRAW_CIRCLE);
	}
}
