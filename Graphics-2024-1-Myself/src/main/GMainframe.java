package main;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import View_Frame.GDrawingPanel;
import View_Frame.GMenubar;
import View_Frame.GToolbar;

public class GMainframe extends JFrame{
	private static final long serialVersionUID = 1L;
	private GMenubar menubar;
    private GToolbar toolbar;
    private GDrawingPanel canvas;

    public GMainframe() {
        this.setTitle("Sketchbook");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        
        this.menubar = new GMenubar();
        this.canvas = new GDrawingPanel();
        this.toolbar = new GToolbar(); 
        
        this.setLayout(new BorderLayout());
        this.setJMenuBar(this.menubar);
        
        this.add(toolbar, BorderLayout.NORTH);
        this.add(canvas, BorderLayout.CENTER);
        
        // 툴바 바인딩.
        this.canvas.setToolbar(toolbar);
	}

}
