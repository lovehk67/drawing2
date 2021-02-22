package drawing.action;

import static drawing.DrawingConstants.ACTION_DRAW_FILL;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import drawing.ui.DrawingBoard;
import drawing.ui.DrawingBoard.Draw_Mode;

public class DrawingFillAction extends AbstractDrawingAction {

	public DrawingFillAction(DrawingBoard board) {
		super(ACTION_DRAW_FILL,
				new ImageIcon("src/drawing/resource/bucket.PNG"), board);
	}

	public void actionPerformed(ActionEvent e) {
		board.setDrawMode(Draw_Mode.DRAW_FILL);
	}

}
