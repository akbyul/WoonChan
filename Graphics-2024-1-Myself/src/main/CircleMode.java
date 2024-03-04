package main;

import Global.GMode;

public class CircleMode implements GMode {
	
    @Override
    public GShape createShape() {
        return new GCircle();
    }
}
