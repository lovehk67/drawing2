package drawing.action;

import static drawing.DrawingConstants.MENU_FILE_SAVE;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import drawing.ui.DrawingBoard;

public class FileSaveAction extends AbstractDrawingAction {

	public FileSaveAction(DrawingBoard board) {
		super(MENU_FILE_SAVE, new ImageIcon("src/drawing/resource/save.PNG"),
				board);
	}

	public void actionPerformed(ActionEvent e) {
		board.saveFile();
	}
}
