package View_ShapeClass;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class GRectangle extends GShape {
	
	private int px, py;

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
        int x = Math.min(startPoint.x, endPoint.x);
        int y = Math.min(startPoint.y, endPoint.y);
        int width = Math.abs(startPoint.x - endPoint.x);
        int height = Math.abs(startPoint.y - endPoint.y);
        g.drawRect(x, y, width, height);
	}

	@Override
	// 동일 구조의 인스턴스 생성. 메모리주소는 다름.
	public GShape clone() {
		return new GRectangle();
	}


	@Override
	public void setShape(int x1, int y1, int x2, int y2) {
		shape = new Rectangle(x1, y1, x2 - x1, y2 - y1);
	}

	@Override
	public void resizePoint(int x2, int y2) {
		Rectangle rectangle = (Rectangle) shape;
		rectangle.setFrame(rectangle.getX(), rectangle.getY(), x2 - rectangle.getX(), y2 - rectangle.getY());
	}

	@Override
	public void setPoint(int x, int y) {
		this.px = x;
		this.py = y;
	}

	@Override
	public void movePoint(int x, int y) {
		Rectangle rectangle = (Rectangle) shape;
		rectangle.setLocation(rectangle.x  + x - px, rectangle.y + y - py);
		this.px = x;
		this.py = y;
	}

}
