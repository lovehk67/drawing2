package drawing.action;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import drawing.ui.DrawingBoard;

public abstract class AbstractDrawingAction extends AbstractAction {

	protected DrawingBoard board;

	public AbstractDrawingAction(String actionComm, ImageIcon ico,
			DrawingBoard board) {
		super(actionComm, ico);
		this.board = board;
	}
}
