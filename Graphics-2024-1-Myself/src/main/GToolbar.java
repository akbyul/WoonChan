package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import Global.GMode;

public class GToolbar extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Constructor
    private GCanvas canvas;

    public GToolbar(GCanvas canvas) {
        this.canvas = canvas;
        addButton("네모", new RectangleMode());
        addButton("동그라미", new CircleMode());
        addButton("세모", new TriangleMode());
    }

    private void addButton(String name, GMode mode) {
        JButton button = new JButton(name);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (name) {
                    case "네모":
                        if (mode != null) {
                            canvas.setCurrentShape(mode.createShape());
                        }
                        break;
                    case "동그라미":
                        if (mode != null) {
                            canvas.setCurrentShape(mode.createShape());
                        }
                        break;
                    case "세모":
                        if (mode != null) {
                            canvas.setCurrentShape(mode.createShape());
                        }
                        break;
                    default:
                        break;
                }
            }
        });
        add(button);
    }
}
