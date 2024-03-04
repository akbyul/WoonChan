package main;

import Global.GMode;

public class TriangleMode implements GMode {

	@Override
    public GShape createShape() {
        return new GTriangle();
    }

}
