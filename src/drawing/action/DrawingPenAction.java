package drawing.action;

import static drawing.DrawingConstants.ACTION_DRAW_PEN;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import drawing.ui.DrawingBoard;
import drawing.ui.DrawingBoard.Draw_Mode;

public class DrawingPenAction extends AbstractDrawingAction {

	public DrawingPenAction(DrawingBoard board) {
		super(ACTION_DRAW_PEN, new ImageIcon("src/drawing/resource/pen.PNG"),
				board);
	}

	public void actionPerformed(ActionEvent e) {
		board.setDrawMode(Draw_Mode.DRAW_PEN);
	}
}
