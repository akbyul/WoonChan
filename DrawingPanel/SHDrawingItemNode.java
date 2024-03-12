package UserInterface.DrawingPanel;

import java.awt.Point;

import UserInterface.ToolBar.ShapeTool.SHShape;

public class SHDrawingItemNode {
	int		shapeType;
	Point	startPoint;
	Point	endPoint;
	int		x1, y1, x2, y2;

	SHDrawingItemNode	next;
	SHDrawingItemNode	prev;
	SHDrawingItemNode	tail;

	public SHDrawingItemNode() {
		this.shapeType = SHShape.none;
		this.startPoint = null;
		this.endPoint = null;
		this.x1 = 0;
		this.y1 = 0;
		this.x2 = 0;
		this.y2 = 0;
		this.next = null;
		this.prev = null;
		this.tail = this;
	}
	
	public SHDrawingItemNode(int shapeType, Point startPoint, Point endPoint, int x1, int y1, int x2, int y2) {
		this.shapeType = shapeType;
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	public void movePosition(int moveX, int moveY) {
		this.startPoint.x += moveX;
		this.startPoint.y += moveY;
		this.endPoint.x += moveX;
		this.endPoint.y += moveY;
		this.x1 += moveX;
		this.y1 += moveY;
		this.x2 += moveX;
		this.y2 += moveY;
	}
	
	public void add(SHDrawingItemNode head, SHDrawingItemNode newNode) {
		SHDrawingItemNode curr = head;
		while (curr.next != null) {
			curr = curr.next;
		}
		curr.next = newNode;
		newNode.prev = curr;
		head.tail = newNode;
	}
	
	public void delete(SHDrawingItemNode head, SHDrawingItemNode dest) {
		if (dest.next != null) {
			dest.prev.next = dest.next;
			dest.next.prev = dest.prev;
		} else {
			head.tail = dest.prev;
			dest.prev.next = null;
		}
		dest = null;
	}
	
	public SHDrawingItemNode search(SHDrawingItemNode head, Point p) {
		SHDrawingItemNode curr = head.tail;
		
		while (curr.prev != null) {
			if ((curr.x1 <= p.x && p.x <= curr.x2) && (curr.y1 <= p.y && p.y <= curr.y2))
				return (curr);
			curr = curr.prev;
		}
		return (null);
	}
	
	public void deleteAll(SHDrawingItemNode head) {
		SHDrawingItemNode curr = head.next;
		
		if (head.prev == null) {
			while (curr != null) {
				curr.delete(head, curr);
				curr = curr.next;
			}
		}
	}
}
