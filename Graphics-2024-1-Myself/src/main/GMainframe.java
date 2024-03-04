package main;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class GMainframe extends JFrame{
	
	/**
	 * 
	 */
    private GToolbar toolbar;
    private GCanvas canvas;

    public GMainframe() {
        setTitle("Sketchbook");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        
        canvas = new GCanvas();
        toolbar = new GToolbar(canvas);
        

        setLayout(new BorderLayout());
        add(toolbar, BorderLayout.NORTH);
        add(canvas, BorderLayout.CENTER);
	}

}
