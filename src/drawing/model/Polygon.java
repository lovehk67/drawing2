package drawing.model;

public class Polygon extends Shape {

	private static final long serialVersionUID = 8813669403576855931L;

	public java.awt.Polygon polygon;

	public Polygon(Drawing model) {
		super(model);
	}

	public void setPolygon(java.awt.Polygon polygon) {
		this.polygon = polygon;
	}

	public java.awt.Polygon getPolygon() {
		return polygon;
	}
}
