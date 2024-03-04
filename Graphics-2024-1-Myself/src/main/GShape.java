package main;

import java.awt.Graphics;
import java.awt.Point;

public abstract class GShape {
    protected Point startPoint;
    protected Point endPoint;

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    public abstract void draw(Graphics g);
}
