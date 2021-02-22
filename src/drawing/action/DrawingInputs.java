package drawing.action;

import static drawing.DrawingConstants.ACCTION_EXIT;
import static drawing.DrawingConstants.ACTION_DRAW_CIRCLE;
import static drawing.DrawingConstants.ACTION_DRAW_FILL;
import static drawing.DrawingConstants.ACTION_DRAW_LINE;
import static drawing.DrawingConstants.ACTION_DRAW_PEN;
import static drawing.DrawingConstants.ACTION_DRAW_POLYGON;
import static drawing.DrawingConstants.ACTION_DRAW_RECTANGLE;
import static drawing.DrawingConstants.ACTION_FILE_NEW;
import static drawing.DrawingConstants.ACTION_FILE_OPEN;
import static drawing.DrawingConstants.ACTION_FILE_SAVE;
import static drawing.DrawingConstants.ACTION_FILE_SAVEAS;

import java.awt.event.KeyEvent;

import javax.swing.ComponentInputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

public class DrawingInputs {
	private ComponentInputMap inputMap;

	public DrawingInputs(JComponent comp) {
		inputMap = new ComponentInputMap(comp);

		inputMap
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_L, 0), ACTION_DRAW_LINE);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_P, 0), ACTION_DRAW_PEN);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_C, 0),
				ACTION_DRAW_CIRCLE);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_R, 0),
				ACTION_DRAW_RECTANGLE);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_G, 0),
				ACTION_DRAW_POLYGON);
		inputMap
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_F, 0), ACTION_DRAW_FILL);

		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_MASK),
				ACTION_FILE_NEW);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_MASK),
				ACTION_FILE_OPEN);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK),
				ACTION_FILE_SAVE);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_MASK),
				ACTION_FILE_SAVEAS);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_MASK),
				ACCTION_EXIT);
	}

	public ComponentInputMap getInputMap() {
		return inputMap;
	}
}
