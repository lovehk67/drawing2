package drawing.action;

import static drawing.DrawingConstants.ACTION_DRAW_LINE;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import drawing.ui.DrawingBoard;
import drawing.ui.DrawingBoard.Draw_Mode;

public class DrawingLineAction extends AbstractDrawingAction {

	public DrawingLineAction(DrawingBoard board) {
		super(ACTION_DRAW_LINE, new ImageIcon("src/drawing/resource/line.PNG"),
				board);
	}

	public void actionPerformed(ActionEvent e) {
		board.setDrawMode(Draw_Mode.DRAW_LINE);
	}
}
