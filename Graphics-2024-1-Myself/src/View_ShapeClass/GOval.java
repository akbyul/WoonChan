package View_ShapeClass;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Ellipse2D;

public class GOval extends GShape {


	private int px;
	private int py;

	public GOval() {
	}
	
	@Override
	public GShape clone() {
		// TODO Auto-generated method stub
		return new GOval();
	}

	@Override
	public void setShape(int x1, int y1, int x2, int y2) {
		this.shape = new Ellipse2D.Double(x1, y1, x2 - x1, y2 - y1);
	}

	@Override
	public void resizePoint(int x2, int y2) {
		Ellipse2D ellipse2D = (Ellipse2D) shape;
		ellipse2D.setFrame(ellipse2D.getX(), ellipse2D.getY(), x2 - ellipse2D.getX(), y2 - ellipse2D.getY());
	}

	@Override
	public void setPoint(int x, int y) {
		this.px = x;
		this.py = y;
	}

	@Override
	public void movePoint(int x, int y) {
		Ellipse2D ellipse2D = (Ellipse2D) shape;
		ellipse2D.setFrame(ellipse2D.getX() + x - px, ellipse2D.getY() + y - py, ellipse2D.getWidth(),
				ellipse2D.getHeight());
		this.px = x;
		this.py = y;
	}

	
	

}
