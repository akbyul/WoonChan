package View_ShapeClass;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;

public abstract class GShape {
	protected Shape shape;	
    protected Point startPoint;
    protected Point endPoint;
	
	public GShape() {
		
	}
	
	abstract public GShape clone();	
	
	public boolean onShape(Point p) {
		return shape.contains(p);		
	}
	
	public void draw(Graphics graphics) {
		Graphics2D graphics2D = (Graphics2D) graphics;
		graphics2D.draw(shape);
		System.out.println("[GShape] : Draw 호출" );

	}
//    public void setStartPoint(Point startPoint) {
//        this.startPoint = startPoint;
//    }
//
//    public void setEndPoint(Point endPoint) {
//        this.endPoint = endPoint;
//    }
//    
//    public Point getStartPoint() {
//		return this.startPoint;    	
//    }
//    public Point getEndPoint() {
//		return this.endPoint;    	
//    }
    
	public abstract void setShape(int x1, int y1, int x2, int y2);

	public abstract void setPoint(int x, int y);

	public abstract void resizePoint(int x, int y);

	public abstract void movePoint(int x, int y);

	public void addPoint(int x, int y) {
		
	}
}
