package UserInterface.DrawingPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

import javax.swing.JPanel;

import UserInterface.ToolBar.ShapeTool.SHShape;

public class SHDrawingPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private SHDrawingItemNode		drawingItemHead;
	private SHDrawingItemNode		selectNode = null;
	private SHDrawingPanelListener	drawPanleListener;
	
	private static boolean	isDrawingMode = false;
	private static boolean	isModifyMode = false;
	private static Point	startPoint, endPoint;
	
	private int				x1, y1, x2, y2;

	public SHDrawingPanel() {
		this.drawPanleListener = new SHDrawingPanelListener(this);
		this.drawingItemHead = new SHDrawingItemNode();
		
		this.addMouseListener(this.drawPanleListener);
		this.addMouseMotionListener(this.drawPanleListener);
	}
	
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		drawItemList(g);
		if (isDrawingMode == true) {
			drawingMode(g);
		}
	}
	
	public void	drawItemList(Graphics g) {
		SHDrawingItemNode curr = this.drawingItemHead.next;
		while (curr != null) {
			drawingShape(g, curr.shapeType, curr.startPoint, curr.endPoint, curr.x1, curr.y1, curr.x2, curr.y2);
			curr = curr.next;
		}
	}
	
	public void drawingMode(Graphics g) {
		x1 = (startPoint.x < endPoint.x ? startPoint.x : endPoint.x);
		y1 = (startPoint.y < endPoint.y ? startPoint.y : endPoint.y);
		x2 = (startPoint.x > endPoint.x ? startPoint.x : endPoint.x);
		y2 = (startPoint.y > endPoint.y ? startPoint.y : endPoint.y);
		drawingShape(g, SHShape.getShapeType(), startPoint, endPoint, x1, y1, x2, y2);
		isDrawingMode = false;
	}
	
	public void	drawingShape(Graphics g, int shapeType, Point startPoint, Point endPoint, int x1, int y1, int x2, int y2) {
		switch (shapeType) {
			case (SHShape.line):
				g.setColor(Color.darkGray);
				g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
				g.drawLine(startPoint.x + 1, startPoint.y, endPoint.x + 1, endPoint.y);
				g.drawLine(startPoint.x - 1, startPoint.y, endPoint.x - 1, endPoint.y);
				g.drawLine(startPoint.x, startPoint.y + 1, endPoint.x, endPoint.y + 1);
				g.drawLine(startPoint.x, startPoint.y - 1, endPoint.x, endPoint.y - 1);
				break ;
			case (SHShape.rectangle):
				g.setColor(Color.darkGray);
				g.fillRect(x1, y1, (x2 - x1), (y2 - y1));
				g.setColor(Color.blue);
				g.drawRect(x1, y1, (x2 - x1), (y2 - y1));
				g.drawRect(x1 + 1, y1 + 1, (x2 - x1) - 1, (y2 - y1) - 1);
				break ;
			case (SHShape.oval):
				g.setColor(Color.darkGray);
				g.fillOval(x1, y1, (x2 - x1), (y2 - y1));
				g.setColor(Color.blue);
				g.drawOval(x1, y1, (x2 - x1), (y2 - y1));
				g.drawOval(x1 + 1, y1 + 1, (x2 - x1) - 1, (y2 - y1) - 1);
				break ;
			case (SHShape.polygon):
				Polygon polygon1 = SHShape.getPolygon(x1, y1, x2, y2);
				Polygon polygon2 = SHShape.getPolygon(x1 + 1, y1 + 1, x2 - 1, y2 - 1);
				g.setColor(Color.darkGray);
				g.fillPolygon(polygon1);
				g.setColor(Color.blue);
				g.drawPolygon(polygon1);
				g.drawPolygon(polygon2);
				break ;
				
			default:
				break ;
		}
	}
	
	public void setModifyMode(Point p) {
		this.selectNode = this.drawingItemHead.search(drawingItemHead, p);
	}
	
	public void modifyPointNode() {
		if (this.selectNode != null) {
			this.selectNode.movePosition(endPoint.x - startPoint.x, endPoint.y - startPoint.y);
			repaint();
		}
	}
	
	public void	saveDrawingItem() {
		SHDrawingItemNode newNode = new SHDrawingItemNode(SHShape.getShapeType(), startPoint, endPoint, x1, y1, x2, y2);
		this.drawingItemHead.add(this.drawingItemHead, newNode);
	}

	public void resetDrawingPanel() {
		this.drawingItemHead.deleteAll(this.drawingItemHead);
		repaint();
	}
	
	public void					setSelectNode(SHDrawingItemNode drawingItemNode) { this.selectNode = drawingItemNode; }
	public SHDrawingItemNode	getSelectNode() { return (this.selectNode); }  
	
	public static void		setIsDrawingMode(boolean isDrawingMode) { SHDrawingPanel.isDrawingMode = isDrawingMode; }
	public static boolean	getIsDrawingMode() { return (SHDrawingPanel.isDrawingMode); }
	public static void		setIsModifygMode(boolean isDraw) { SHDrawingPanel.isModifyMode = isDraw; }
	public static boolean	getIsModifygMode() { return (SHDrawingPanel.isModifyMode); }
	public static void		setStartPoint(Point point) { SHDrawingPanel.startPoint = point; }
	public static void		setEndPoint(Point point) { SHDrawingPanel.endPoint = point; }

	public SHDrawingItemNode	getDrawingItemHead() { return (this.drawingItemHead); }
}
