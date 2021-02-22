package drawing;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ActionMap;
import javax.swing.JComponent;
import javax.swing.JFrame;

import drawing.action.DrawingActions;
import drawing.action.DrawingInputs;
import drawing.action.FileActions;
import drawing.model.Drawing;
import drawing.ui.DrawingBoard;
import drawing.ui.DrawingMenuBar;
import drawing.ui.DrawingToolBar;

public class DrawingMainFrame extends JFrame {

	public DrawingMainFrame(String title) {
		super(title);
		Dimension screenSize = getToolkit().getScreenSize();
		setSize(screenSize.width / 2, screenSize.height / 2);
		setLocation(screenSize.width / 4, screenSize.height / 4);

		Drawing model = new Drawing();
		DrawingBoard board = new DrawingBoard(model);
		ActionMap am = new DrawingActions(board).getActionMap();
		am.setParent(new FileActions(board).getActionMap());
		
		model.addPropertyChangeListener(board);
		model.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if ("modified".equals(evt.getPropertyName())) {
					if ((Boolean) evt.getNewValue())
						setTitle(getTitle() + "*");
					else
						setTitle(getTitle().indexOf('*') == -1 ? getTitle()
								: getTitle().substring(0,
										getTitle().indexOf('*')));
				} else if ("filename".equals(evt.getPropertyName())) {
					setTitle(DrawingConstants.TITLE_PREFIX + ":"
							+ evt.getNewValue().toString());
				} else if ("newfile".equals(evt.getPropertyName())) {
					setTitle(DrawingConstants.TITLE_PREFIX);
				}
			}
		});

		DrawingMenuBar drawingMenuBar = new DrawingMenuBar();
		DrawingToolBar drawingToolBar = new DrawingToolBar();

		setJMenuBar(drawingMenuBar);

		Container cpane = getContentPane();

		cpane.add(board);

		cpane.add(drawingToolBar, BorderLayout.NORTH);

		drawingToolBar.setActionMap(am);
		drawingMenuBar.setActionMap(am);

		drawingMenuBar.initMenuBar();
		drawingToolBar.initToolBar();

		JComponent layeredPane = getLayeredPane();
		layeredPane.setActionMap(am);	

		layeredPane.setInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW,
				new DrawingInputs(layeredPane).getInputMap());

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public static void main(String args[]) {
		new DrawingMainFrame(DrawingConstants.TITLE_PREFIX).setVisible(true);

	}
}
