package drawing.model;

import java.awt.Component;
import java.util.Vector;

public class Drawing extends Component {
	private static final long serialVersionUID = 6251258176751113774L;

	private Vector<Shape> shapeVector = new Vector<Shape>();
	private boolean isModified = false;
	private String fileName = "";

	public void changeFillColor(Shape shape) {
		firePropertyChange("color", null, shape);
		setModified(true);
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
		firePropertyChange("filename", null, fileName);
	}

	public boolean isModified() {
		return isModified;
	}

	public void setModified(boolean isModified) {
		boolean oldValue = this.isModified;
		this.isModified = isModified;
		firePropertyChange("modified", oldValue, isModified);
	}

	public Vector<Shape> getShapeVector() {
		return shapeVector;
	}

	public void setShapeVector(Vector<Shape> shapeVector) {
		Vector<Shape> oldValue = this.shapeVector;
		this.shapeVector = shapeVector;
		firePropertyChange("openfile", oldValue, shapeVector);
		setModified(false);
	}

	public void addShape(Shape shape) {
		shapeVector.add(shape);
		firePropertyChange("addshape", null, shape);
		setModified(true);
	}

	public void clear() {
		shapeVector.removeAllElements();
		setFileName("");
		setModified(false);
		firePropertyChange("newfile", true, false);
	}
}