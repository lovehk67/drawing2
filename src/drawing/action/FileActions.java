package drawing.action;

import static drawing.DrawingConstants.ACCTION_EXIT;
import static drawing.DrawingConstants.ACTION_FILE_NEW;
import static drawing.DrawingConstants.ACTION_FILE_OPEN;
import static drawing.DrawingConstants.ACTION_FILE_SAVE;
import static drawing.DrawingConstants.ACTION_FILE_SAVEAS;

import javax.swing.ActionMap;

import drawing.ui.DrawingBoard;

public class FileActions {
	private ActionMap actionMap = new ActionMap();

	public FileActions(DrawingBoard board) {

		actionMap.put(ACTION_FILE_NEW, new FileNewAction(board));
		actionMap.put(ACTION_FILE_OPEN, new FileOpenAction(board));
		actionMap.put(ACTION_FILE_SAVE, new FileSaveAction(board));
		actionMap.put(ACTION_FILE_SAVEAS, new FileSaveAs(board));
		actionMap.put(ACCTION_EXIT, new ExitAction());
	}

	public ActionMap getActionMap() {
		return actionMap;
	}
}
