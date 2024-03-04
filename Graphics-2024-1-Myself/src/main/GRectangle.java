package main;

import java.awt.Graphics;

public class GRectangle extends GShape {

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
        int x = Math.min(startPoint.x, endPoint.x);
        int y = Math.min(startPoint.y, endPoint.y);
        int width = Math.abs(startPoint.x - endPoint.x);
        int height = Math.abs(startPoint.y - endPoint.y);
        g.drawRect(x, y, width, height);
	}

}
