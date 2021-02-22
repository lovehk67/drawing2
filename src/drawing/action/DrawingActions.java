package drawing.action;

import static drawing.DrawingConstants.ACTION_DRAW_CIRCLE;
import static drawing.DrawingConstants.ACTION_DRAW_FILL;
import static drawing.DrawingConstants.ACTION_DRAW_LINE;
import static drawing.DrawingConstants.ACTION_DRAW_PEN;
import static drawing.DrawingConstants.ACTION_DRAW_POLYGON;
import static drawing.DrawingConstants.ACTION_DRAW_RECTANGLE;

import javax.swing.ActionMap;

import drawing.ui.DrawingBoard;

public class DrawingActions {
	private ActionMap actionMap = new ActionMap();

	public DrawingActions(DrawingBoard board) {
		actionMap.put(ACTION_DRAW_LINE, new DrawingLineAction(board));
		actionMap.put(ACTION_DRAW_PEN, new DrawingPenAction(board));
		actionMap.put(ACTION_DRAW_CIRCLE, new DrawingCircleAction(board));
		actionMap.put(ACTION_DRAW_RECTANGLE, new DrawingRectAction(board));
		actionMap.put(ACTION_DRAW_POLYGON, new DrawingPolygonAction(board));
		actionMap.put(ACTION_DRAW_FILL, new DrawingFillAction(board));
	}

	public ActionMap getActionMap() {
		return actionMap;
	}
}
