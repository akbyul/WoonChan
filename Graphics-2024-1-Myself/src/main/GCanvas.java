package main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class GCanvas extends JPanel {
    private GShape currentShape;
    private List<GShape> shapes;
    private Point startPoint;
    private Point endPoint;

    public GCanvas() {
        setBackground(Color.WHITE);
        shapes = new ArrayList<>();
        addMouseListener(new CustomMouseListener());
        addMouseMotionListener(new CustomMouseListener());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (GShape shape : shapes) {
            shape.draw(g);
        }
    }

    private class CustomMouseListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            startPoint = e.getPoint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            endPoint = e.getPoint();
            if (currentShape != null) {
                currentShape.setStartPoint(startPoint);
                currentShape.setEndPoint(endPoint);
                addShape(currentShape);
                repaint();
            }
        }
    }

    public void setCurrentShape(GShape shape) {
        currentShape = shape;
    }

    private void addShape(GShape shape) {
        shapes.add(shape);
    }
}