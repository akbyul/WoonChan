package main;

import java.awt.Graphics;

public class GCircle extends GShape {

    @Override
    public void draw(Graphics g) {
        int x = Math.min(startPoint.x, endPoint.x);
        int y = Math.min(startPoint.y, endPoint.y);
        int diameter = Math.max(Math.abs(startPoint.x - endPoint.x), Math.abs(startPoint.y - endPoint.y));
        g.drawOval(x, y, diameter, diameter);
    }
}
