package UserInterface.DrawingPanel;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import UserInterface.ToolBar.ShapeTool.SHShape;

public class SHDrawingPanelListener implements MouseListener, MouseMotionListener {
	private SHDrawingPanel	drawingPanel;
	
	private int	modifierEX;
	
	public SHDrawingPanelListener(SHDrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		this.modifierEX = e.getModifiersEx();
		if (this.modifierEX == InputEvent.BUTTON1_DOWN_MASK) {
			if (SHShape.getShapeType() != SHShape.none) {
				SHDrawingPanel.setStartPoint(e.getPoint());
			} else if (SHDrawingPanel.getIsModifygMode() == true) {
				SHDrawingPanel.setStartPoint(e.getPoint());
				this.drawingPanel.setModifyMode(e.getPoint());
			}
		} else {
			this.modifierEX = 0;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (this.modifierEX == InputEvent.BUTTON1_DOWN_MASK) {
			if (SHShape.getShapeType() != SHShape.none) {
				SHDrawingPanel.setEndPoint(e.getPoint());
				this.drawingPanel.saveDrawingItem();
				SHDrawingPanel.setIsDrawingMode(false);
				this.drawingPanel.repaint();
			} else if (this.drawingPanel.getSelectNode() != null) {
				SHDrawingPanel.setEndPoint(e.getPoint());
				this.drawingPanel.setSelectNode(null);
			}
		}
		this.modifierEX = 0;
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		this.modifierEX = e.getModifiersEx();
		if (this.modifierEX == InputEvent.BUTTON1_DOWN_MASK) {
			if (SHShape.getShapeType() != SHShape.none) {
				SHDrawingPanel.setEndPoint(e.getPoint());
				SHDrawingPanel.setIsDrawingMode(true);
				this.drawingPanel.repaint();
			} else if (this.drawingPanel.getSelectNode() != null) {
				SHDrawingPanel.setEndPoint(e.getPoint());
				this.drawingPanel.modifyPointNode();
				SHDrawingPanel.setStartPoint(e.getPoint());
			}
		} else {
			this.modifierEX = 0;
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
