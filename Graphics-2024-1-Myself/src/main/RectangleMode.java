package main;

import Global.GMode;

public class RectangleMode implements GMode {
    @Override
    public GShape createShape() {
        return new GRectangle();
    }
}
