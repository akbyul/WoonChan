package main;

import java.awt.Graphics;

public class GTriangle extends GShape {

    @Override
    public void draw(Graphics g) {
        int[] xPoints = {startPoint.x, endPoint.x, startPoint.x + (endPoint.x - startPoint.x) / 2};
        int[] yPoints = {endPoint.y, endPoint.y, startPoint.y};
        g.drawPolygon(xPoints, yPoints, 3);
    }

}
