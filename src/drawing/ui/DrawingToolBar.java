package drawing.ui;

import static drawing.DrawingConstants.ACTION_DRAW_CIRCLE;
import static drawing.DrawingConstants.ACTION_DRAW_FILL;
import static drawing.DrawingConstants.ACTION_DRAW_LINE;
import static drawing.DrawingConstants.ACTION_DRAW_PEN;
import static drawing.DrawingConstants.ACTION_DRAW_POLYGON;
import static drawing.DrawingConstants.ACTION_DRAW_RECTANGLE;
import static drawing.DrawingConstants.MENU_DRAW_CIRCLE;
import static drawing.DrawingConstants.MENU_DRAW_FILL;
import static drawing.DrawingConstants.MENU_DRAW_LINE;
import static drawing.DrawingConstants.MENU_DRAW_PEN;
import static drawing.DrawingConstants.MENU_DRAW_POLYGON;
import static drawing.DrawingConstants.MENU_DRAW_RECTANGLE;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JToolBar;

public class DrawingToolBar extends JToolBar {

	public void initToolBar() {
		// 도형 아이콘
		add(new IconButton(getActionMap().get(ACTION_DRAW_PEN), MENU_DRAW_PEN));
		add(new IconButton(getActionMap().get(ACTION_DRAW_LINE), MENU_DRAW_LINE));
		add(new IconButton(getActionMap().get(ACTION_DRAW_CIRCLE),
				MENU_DRAW_CIRCLE));
		add(new IconButton(getActionMap().get(ACTION_DRAW_RECTANGLE),
				MENU_DRAW_RECTANGLE));
		add(new IconButton(getActionMap().get(ACTION_DRAW_POLYGON),
				MENU_DRAW_POLYGON));
		add(new IconButton(getActionMap().get(ACTION_DRAW_FILL), MENU_DRAW_FILL));
		// add(new IconButton(actionMap.get(ACTION_DRAW_SELECT),
		// MENU_DRAW_SELECT));
		addSeparator();
	}

	class IconButton extends JButton {
		private static final long serialVersionUID = 4852219230817771994L;

		public IconButton(Action action, String tooltip) {
			super(action);
			setToolTipText(tooltip);
		}
	}
}
