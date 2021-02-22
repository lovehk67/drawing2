package drawing.model;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Vector;

import drawing.DrawingConstants;

public abstract class Shape implements Serializable {

	protected Vector<Point> pointVector = new Vector<Point>();
	// �� ��
	protected Color lineColor = DrawingConstants.DEFAULT_LINE_COLOR;
	// ä��� ��
	protected Color fillColor = DrawingConstants.DEFAULT_FILL_COLOR;
	// �� �β�
	protected int thickness = 1;

	protected Drawing model;

	public Shape(Drawing model) {
		this.model = model;
	}

	// shape �� ����� ������ ȣ��, Drawing model �� �����Ű�� �ʴ´�.
	public void addPoint(Point p) {
		pointVector.add(p);
	}

	public Vector<Point> getPointVector() {
		return pointVector;
	}

	public Color getLineColor() {
		return lineColor;
	}

	public Color getFillColor() {
		return fillColor;
	}

	public int getThickness() {
		return thickness;
	}

	// �� �� ����
	public void setLineColor(Color c) {
		lineColor = c;
	}

	// ä��� �� ����
	public void setFillColor(Color c) {
		fillColor = c;
		// View ������ �ٲ� color �� ȭ�鿡 ��Ÿ����.
		model.changeFillColor(this);
	}

	// �β� ����
	public void setThickness(int th) {
		thickness = th;
	}

	// p1, p2 ������ ����
	public Rectangle getRect(Point p1, Point p2) {
		Rectangle rect = null;

		int minX = Math.min(p1.x, p2.x);
		int maxX = Math.max(p1.x, p2.x);
		int minY = Math.min(p1.y, p2.y);
		int maxY = Math.max(p1.y, p2.y);

		rect = new Rectangle(minX, minY, maxX - minX, maxY - minY);

		return rect;
	}

	// ������ �� �� ������ ����
	public Rectangle getLastRect() {
		Rectangle rect = null;
		Point p1 = pointVector.size() > 1 ? pointVector
				.get(pointVector.size() - 2) : pointVector.firstElement();
		Point p2 = pointVector.lastElement();

		int minX = Math.min(p1.x, p2.x);
		int maxX = Math.max(p1.x, p2.x);
		int minY = Math.min(p1.y, p2.y);
		int maxY = Math.max(p1.y, p2.y);

		rect = new Rectangle(minX, minY, maxX - minX, maxY - minY);

		return rect;
	}

	// ������ �׷��� ��ü ����
	public Rectangle getRect() {
		Rectangle rect = null;

		Iterator<Point> iter = pointVector.iterator();
		int minX = Integer.MAX_VALUE;
		int maxX = 0;
		int minY = Integer.MAX_VALUE;
		int maxY = 0;

		while (iter.hasNext()) {
			Point p = iter.next();

			minX = Math.min(minX, p.x);
			minY = Math.min(minY, p.y);
			maxX = Math.max(maxX, p.x);
			maxY = Math.max(maxY, p.y);
		}

		minX -= thickness;
		maxX += thickness;
		minY -= thickness;
		maxY += thickness;

		rect = new Rectangle(minX, minY, maxX - minX, maxY - minY);

		return rect;
	}
}
