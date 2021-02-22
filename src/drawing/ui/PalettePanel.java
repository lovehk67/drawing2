package drawing.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JColorChooser;
import javax.swing.JPanel;

import drawing.DrawingConstants;

public class PalettePanel extends JPanel {

	private ColorPanel lineColorPanel;
	private ColorPanel fillColorPanel;
	private boolean isNull = false;

	public Color getLineColor() {
		return lineColorPanel.getBackground();
	}

	public Color getFillColor() {
		if (isNull) {
			System.out.println("null");
			return null;
		}
		return fillColorPanel.getBackground();
	}

	public void setLineColor(Color c) {
		lineColorPanel.setBackground(c);
	}

	public void setFillColor(Color c) {
		fillColorPanel.setBackground(c);
	}

	public PalettePanel() {
		JPanel totalPanel = new JPanel();

		JPanel colorPanel = new JPanel();
		colorPanel.setLayout(new GridLayout(2, 1));

		lineColorPanel = new ColorPanel(Color.black, ColorPanel.LINE);
		fillColorPanel = new ColorPanel(Color.white, ColorPanel.FILL);

		colorPanel.add(lineColorPanel);
		colorPanel.add(fillColorPanel);

		JPanel palettePanel = new JPanel();
		palettePanel.setLayout(new GridLayout(2, 14));

		// 28가지 팔래트
		Color[] paletteColor = { Color.black, Color.gray,
				new Color(128, 0, 64), new Color(128, 128, 64),
				new Color(0, 128, 64), new Color(0, 128, 128),
				new Color(0, 0, 128), new Color(128, 0, 128),
				new Color(128, 128, 0), new Color(0, 64, 0),
				new Color(0, 128, 255), new Color(0, 64, 128),
				new Color(128, 0, 255), new Color(128, 64, 0), Color.white,
				Color.lightGray, Color.red, Color.yellow, Color.cyan,
				Color.green, Color.blue, Color.magenta,
				new Color(255, 255, 128), new Color(128, 255, 128),
				new Color(128, 255, 255), new Color(128, 128, 255),
				new Color(255, 0, 128), new Color(255, 128, 0) };

		for (int i = 0; i < paletteColor.length; i++)
			palettePanel.add(new PaletteColor(paletteColor[i]));

		totalPanel.add(colorPanel);
		totalPanel.add(palettePanel);

		setLayout(new FlowLayout(FlowLayout.LEFT));
		add(totalPanel);
	}

	class ColorPanel extends JPanel implements MouseListener {

		private static final long serialVersionUID = -4113218423393440402L;

		public static final int LINE = 0;
		public static final int FILL = 1;
		private int type;

		public ColorPanel(Color c, int type) {
			this.type = type;
			setBackground(c);
			setPreferredSize(new Dimension(30, 20));
			setBorder(BorderFactory.createCompoundBorder());

			addMouseListener(this);
		}

		public void mouseClicked(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}

		public void mousePressed(MouseEvent e) {
			Color c = JColorChooser.showDialog(null, "색상을 고르세요", Color.black);

			if (c == null)
				return;

			setBackground(c);

			switch (type) {
			case LINE:
				lineColorPanel.setBackground(c);
				break;
			case FILL:
				fillColorPanel.setBackground(c);
				break;
			}
		}

		public void mouseReleased(MouseEvent e) {
		}

	}

	class PaletteColor extends JPanel implements MouseListener {

		private static final long serialVersionUID = -6049638739738736097L;

		Color c = DrawingConstants.DEFAULT_BACKGROUND;

		public void mouseClicked(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}

		public void mousePressed(MouseEvent e) {
			// 왼쪽 버튼
			if (e.getButton() == MouseEvent.BUTTON1) {
				lineColorPanel.setBackground(c);
			}
			// 오른쪽 버튼
			else if (e.getButton() == MouseEvent.BUTTON3) {
				fillColorPanel.setBackground(c);
			}
		}

		public void mouseReleased(MouseEvent e) {
		}

		public PaletteColor(Color c) {
			this.setBackground(c);
			setPreferredSize(new Dimension(30, 20));
			setBorder(BorderFactory.createEtchedBorder());
			this.c = c;

			addMouseListener(this);
		}
	}
}