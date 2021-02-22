package drawing.action;

import static drawing.DrawingConstants.MENU_FILE_SAVEAS;

import java.awt.event.ActionEvent;

import drawing.ui.DrawingBoard;

public class FileSaveAs extends AbstractDrawingAction {

	public FileSaveAs(DrawingBoard board) {
		super(MENU_FILE_SAVEAS, null, board);
	}

	public void actionPerformed(ActionEvent e) {
		board.openSaveDialog();
	}
}
