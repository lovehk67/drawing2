package drawing.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

import drawing.DrawingConstants;
import drawing.model.Circle;
import drawing.model.Drawing;
import drawing.model.Line;
import drawing.model.Pen;
import drawing.model.Polygon;
import drawing.model.Rect;
import drawing.model.Shape;

public class DrawingBoard extends JPanel implements PropertyChangeListener {

	private static final long serialVersionUID = 5296388756734579179L;

	// 선택되었거나 그려지고 있는 도형
	private Shape shape = null;

	private PalettePanel palette;
	private Drawing model;

	public static enum Draw_Mode {
		DRAW_LINE, DRAW_CIRCLE, DRAW_RECT, DRAW_POLYGON, DRAW_PEN, DRAW_FILL,
	};

	// 그리기 모드(마우스 이벤트 동작 결정)
	private Draw_Mode drawMode = Draw_Mode.DRAW_PEN;

	public DrawingBoard(Drawing model) {
		this.model = model;
		this.setBackground(DrawingConstants.DEFAULT_BACKGROUND);

		Handler handler = new Handler();

		addMouseListener(handler);
		addMouseMotionListener(handler);

		palette = new PalettePanel();
		setLayout(new BorderLayout());
		add(palette, BorderLayout.SOUTH);

		setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
	}

	public Drawing getModel() {
		return model;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		String property = evt.getPropertyName();
		if ("color".equals(property)) {
			// fill color 가 바뀐 도형의 영역을 repaint
			repaint(((Shape) evt.getNewValue()).getRect());
		} else if ("openfile".equals(property)) {
			shape = null;
			// 전체 영역을 repaint
			repaint();
		} else if ("addshape".equals(property)) {
			// 추가된 도형의 영역만 repaint
			repaint(((Shape) evt.getNewValue()).getRect());
		} else if ("newfile".equals(property)) {
			shape = null;
			// 전체 영역 repaint
			repaint();
		}
	}

	// 다른 이름으로 저장
	public void openSaveDialog() {
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showSaveDialog(null);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			model.setFileName(fc.getSelectedFile().getAbsolutePath());

			saveFile();
		}
	}

	// 저장
	public void saveFile() {
		if (model.getFileName().length() <= 0) {
			openSaveDialog();
			return;
		}
		try {
			FileOutputStream fos = new FileOutputStream(model.getFileName());
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(model.getShapeVector());
			oos.flush();
			oos.close();
			fos.close();

			model.setModified(false);
		} catch (IOException ex) {
			ex.printStackTrace();

		}
	}

	public void setDrawMode(Draw_Mode mode) {
		// drawMode 를 바꾸기 전 다각형을 그리던 중이었다면 그리던 선을 지운다.
		if (shape != null && drawMode == Draw_Mode.DRAW_POLYGON
				&& ((Polygon) shape).getPolygon() == null) {
			Rectangle rect = shape.getRect();
			shape = null;
			repaint(rect);
		}
		drawMode = mode;
	}

	private void draw(Shape shape, Graphics g) {
		Vector<Point> pointVector = shape.getPointVector();
		Color lineColor = shape.getLineColor();
		Color fillColor = shape.getFillColor();
		int thickness = shape.getThickness();

		if (shape instanceof Pen) {
			Iterator<Point> iter = pointVector.iterator();
			Point p1 = iter.next();

			while (iter.hasNext()) {
				Point p2 = iter.next();
				g.setColor(lineColor);
				g.drawLine(p1.x, p1.y, p2.x, p2.y);
				p1 = p2;
			}
		} else if (shape instanceof Line) {
			Point p1 = pointVector.firstElement();
			Point p2 = pointVector.lastElement();
			g.setColor(lineColor);
			g.drawLine(p1.x, p1.y, p2.x, p2.y);
		} else if (shape instanceof Circle) {
			Point p1 = pointVector.firstElement();
			Point p2 = pointVector.lastElement();
			Rectangle rect = shape.getRect(p1, p2);
			g.setColor(fillColor);
			g.fillOval(rect.x, rect.y, rect.width, rect.height);
			g.setColor(lineColor);
			// 채우기 없는 선
			g.drawOval(rect.x, rect.y, rect.width-thickness, rect.height - thickness);
		} else if (shape instanceof Rect) {
			Point p1 = pointVector.firstElement();
			Point p2 = pointVector.lastElement();
			Rectangle rect = shape.getRect(p1, p2);
			g.setColor(fillColor);
			g.fillRect(rect.x, rect.y, rect.width, rect.height);
			g.setColor(lineColor);
			g.drawRect(rect.x, rect.y, rect.width, rect.height);
		} else if (shape instanceof Polygon) {
			g.setColor(fillColor);
			g.fillPolygon(((Polygon) shape).getPolygon());
			g.setColor(lineColor);
			g.drawPolygon(((Polygon) shape).getPolygon());
		}
	}

	// 도형이 완성되어 모델에 들어가기 전 그려지는 과정을 XOR 모드로 보여준다.
	// 이미 그려져 있는 p1-p2 선을 다시 XOR 로 그려 지우고
	// 새로 그려져야 하는 p1-p3 선을 XOR 로 그린다.
	private void drawOutline(Shape shape, Point p1, Point p2, Point p3) {

		Graphics g = getGraphics();
		Color oldColor = g.getColor();
		g.setXORMode(getBackground());
		g.setColor(shape.getLineColor());

		if (shape instanceof Polygon && p3 == null) {
			g.drawLine(p1.x, p1.y, p2.x, p2.y);
		} else if (shape instanceof Line || shape instanceof Polygon) {
			// 이전에 그린 도형을 지우고 새로운 도형을 그린다.
			g.drawLine(p1.x, p1.y, p2.x, p2.y); // old line
			g.drawLine(p1.x, p1.y, p3.x, p3.y); // new line
		} else if (shape instanceof Circle) {
			// 이전에 그린 도형을 지우고 새로운 도형을 그린다.
			Rectangle rect = shape.getRect(p1, p2);
			g.drawOval(rect.x, rect.y, rect.width, rect.height); // old line
			rect = shape.getRect(p1, p3);
			g.drawOval(rect.x, rect.y, rect.width, rect.height); // new line
		} else if (shape instanceof Rect) {
			// 이전에 그린 도형을 지우고 새로운 도형을 그린다.
			Rectangle rect = shape.getRect(p1, p2);
			g.drawRect(rect.x, rect.y, rect.width, rect.height); // old line
			rect = shape.getRect(p1, p3);
			g.drawRect(rect.x, rect.y, rect.width, rect.height); // new line
		}
		g.setColor(oldColor);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Iterator<Shape> iter = model.getShapeVector().iterator();

		while (iter.hasNext())
			draw(iter.next(), g);

		if (shape != null && !(shape instanceof Polygon))
			draw(shape, g);
	}

	public Shape createShape() {
		Shape shape = null;

		switch (drawMode) {
		case DRAW_LINE:
			shape = new Line(model);
			break;
		case DRAW_PEN:
			shape = new Pen(model);
			break;
		case DRAW_CIRCLE:
			shape = new Circle(model);
			break;
		case DRAW_RECT:
			shape = new Rect(model);
			break;
		case DRAW_POLYGON:
			shape = new Polygon(model);
			break;
		}
		shape.setFillColor(palette.getFillColor());
		shape.setLineColor(palette.getLineColor());

		return shape;
	}

	class Handler extends MouseAdapter {

		@Override
		public void mousePressed(MouseEvent e) {

			// 채우기
			switch (drawMode) {
			case DRAW_FILL:
				Vector<Shape> vec = model.getShapeVector();
				int i = 0;
				// 뒤에서 부터 비교
				for (i = vec.size() - 1; i >= 0; i--) {
					Shape s = vec.get(i);
					// 채우기할 도형이 다각형일 때
					if (s instanceof Polygon) {
						if (((Polygon) s).polygon.contains(e.getPoint())) {
							s.setFillColor(palette.getFillColor());
							break;
						}
					}
					// 채우기 할 도형이 원, 사각형 일
					else if (s instanceof Circle || s instanceof Rect) {
						Rectangle r = s.getRect();
						// 채우기 색을 바꿀 도형을 찾았다
						if (e.getX() >= r.x && e.getX() <= r.x + r.width
								&& e.getY() >= r.y
								&& e.getY() <= r.y + r.height) {
							s.setFillColor(palette.getFillColor());
							break;
						}
					}
				}
				break;

			// 그리기
			case DRAW_LINE:
			case DRAW_PEN:
			case DRAW_CIRCLE:
			case DRAW_RECT:
			case DRAW_POLYGON:
				if (shape == null)
					shape = createShape();

				shape.addPoint(e.getPoint());
				// 다각형일 경우 선을 계속 이어준다.
				if (drawMode == Draw_Mode.DRAW_POLYGON) {
					Point p1 = shape.getPointVector().size() > 1 ? shape
							.getPointVector().get(
									shape.getPointVector().size() - 2) : shape
							.getPointVector().firstElement();
					Point p2 = shape.getPointVector().lastElement();
					drawOutline(shape, p1, p2, null);
				}
				break;
			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {

			if (shape != null) {
				Vector<Point> p = shape.getPointVector();
				Point p1 = shape.getPointVector().firstElement();
				Point p2 = shape.getPointVector().lastElement();

				switch (drawMode) {
				case DRAW_LINE:
				case DRAW_CIRCLE:
				case DRAW_RECT:
				case DRAW_POLYGON:
					if (drawMode == Draw_Mode.DRAW_POLYGON && p.size() > 2)
						return;

					// 중간단계에서 보여지는 도형의 끝 포인트는 지운다.
					if (p.size() >= 2)
						shape.getPointVector().remove(p.lastElement());
					// 현재 포인트 저장
					shape.addPoint(e.getPoint());
					drawOutline(shape, p1, p2, p.lastElement());
					break;
				case DRAW_PEN:
					// 현재 포인트 저장
					shape.addPoint(e.getPoint());

					p1 = shape.getPointVector().size() > 1 ? shape
							.getPointVector().get(
									shape.getPointVector().size() - 2) : shape
							.getPointVector().firstElement();
					p2 = shape.getPointVector().lastElement();
					getGraphics().drawLine(p1.x, p1.y, p2.x, p2.y);
					break;
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {

			if (shape != null) {

				// polygon 은 release 시에도 그리기가 끝나지 않는다.(더블클릭)
				if (drawMode != Draw_Mode.DRAW_POLYGON)
					drawingCompleted();
			}
		}

		// 그리기가 끝나면
		private void drawingCompleted() {
			// 도형을 모델의 벡터에 넣는다.
			model.addShape(shape);
			shape = null;
		}

		@Override
		public void mouseClicked(MouseEvent e) {

			// 마우스 더블클릭, 다각형일경우, 더블클릭 됐을 때 도형 그리기를 끝낸다.
			if (e.getClickCount() == 2 && drawMode == Draw_Mode.DRAW_POLYGON
					&& shape != null) {
				Iterator<Point> iter = shape.getPointVector().iterator();
				java.awt.Polygon polygon = new java.awt.Polygon();
				while (iter.hasNext()) {
					Point p = iter.next();
					polygon.addPoint(p.x, p.y);
				}
				((Polygon) shape).setPolygon(polygon);
				drawingCompleted();
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// drawMode 에 따라 커서모양을 바꿔준다.
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mouseMoved(MouseEvent e) {

			if (shape != null && drawMode == Draw_Mode.DRAW_POLYGON) {

				Vector<Point> p = shape.getPointVector();

				Point p1 = p.size() > 1 ? p.get(p.size() - 2) : p
						.firstElement();
				Point p2 = p.lastElement();

				// 중간단계에서 보여지는 도형의 끝 포인트는 지운다.
				if (p.size() >= 2)
					shape.getPointVector().remove(p.lastElement());

				// 현재 포인트 저장
				shape.addPoint(e.getPoint());
				drawOutline(shape, p1, p2, p.lastElement());
			}
		}

		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			super.mouseWheelMoved(e);
		}
	}
}
