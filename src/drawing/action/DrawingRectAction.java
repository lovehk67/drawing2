package drawing.action;

import static drawing.DrawingConstants.ACTION_DRAW_RECTANGLE;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import drawing.ui.DrawingBoard;
import drawing.ui.DrawingBoard.Draw_Mode;

public class DrawingRectAction extends AbstractDrawingAction {

	public DrawingRectAction(DrawingBoard board) {
		super(ACTION_DRAW_RECTANGLE, new ImageIcon(
				"src/drawing/resource/rect.PNG"), board);
	}

	public void actionPerformed(ActionEvent e) {
		board.setDrawMode(Draw_Mode.DRAW_RECT);
	}
}
