package View_Frame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JPanel;

import View_Frame.GToolbar.EShape;
import View_ShapeClass.GPolygon;
import View_ShapeClass.GShape;

public class GDrawingPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	// Set Drawing State
	private enum EDrawingState {
		eIdle, eSelecting , eDrawing, eMoving, eResizing, eRotating
	}
	private EDrawingState eDrawingState;

	// For Shape Drawing
    private GShape currentShape;
    private Vector<GShape> DrawingShapeData;
    
    // 툴바에서 관리하는 Enum값에 접근하기 위함.
    private GToolbar toolBar;
	private Mover Transformer;
	
    public void setToolbar(GToolbar Toolbar) {
    	this.toolBar = Toolbar;
    }

    public GDrawingPanel() {
		super();
		this.currentShape = null;
    	this.DrawingShapeData = new Vector<GShape>();
    	this.eDrawingState = EDrawingState.eIdle;
    	
        this.setBackground(Color.WHITE);        
        
        new CustomMouseHandler();
        this.addMouseListener(new CustomMouseHandler());
        this.addMouseMotionListener(new CustomMouseHandler());
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (GShape shape : DrawingShapeData) {
            shape.draw(g);
        }
    }
    
	public GShape onShape(Point point) {
		for (GShape shape : DrawingShapeData) {
			if (shape.onShape(point)) {
				return shape;
			}
		}
		return null;
	}
	
	public void prepareTransforming(int x, int y) {
		System.out.println("[GDrawingPanel] : prepareTransforming 호출 ");
		if (eDrawingState == EDrawingState.eDrawing) {
			currentShape = toolBar.GetESelectedShape().getGShape().clone();
			currentShape.setShape(x, y, x, y);
		} else if (eDrawingState == EDrawingState.eSelecting) {
			currentShape = toolBar.GetESelectedShape().getGShape().clone();
			currentShape.setShape(x, y, x, y);
		} else if (eDrawingState == EDrawingState.eMoving) {
			this.Transformer = new Mover(currentShape);
			Transformer.prepare(x, y);
		}
	}
	
	public void keepTransforming(int x, int y) {
		System.out.println("[GDrawingPanel] : KeepTransforming 호출 ");
		Graphics graphics = getGraphics();
		graphics.setXORMode(getBackground());
		if (eDrawingState == EDrawingState.eDrawing) {
			currentShape.draw(graphics);
			currentShape.resizePoint(x, y);
			currentShape.draw(graphics);
		} else if (eDrawingState == EDrawingState.eSelecting) {
			currentShape.draw(graphics);
			currentShape.resizePoint(x, y);
			currentShape.draw(graphics);
		} else if (eDrawingState == EDrawingState.eMoving) {
			Transformer.transform(x, y, graphics);
		}
	}
	
	public void finalizeTransforming(int x, int y) {
		System.out.println("[GDrawingPanel] : finalizeTransforming 호출 ");
		if (eDrawingState == EDrawingState.eDrawing) {
			DrawingShapeData.add(currentShape);
		} else if (eDrawingState == EDrawingState.eSelecting) {
			Graphics graphics = getGraphics();
			graphics.setXORMode(getBackground());
			currentShape.draw(graphics);
		} else if (eDrawingState == EDrawingState.eMoving) {
			Transformer.finalize(x, y);
		}
		currentShape = null;
		toolBar.resetESelectedShape();
	}
	
	public void continueTransforming(int x, int y) {
		System.out.println("[GDrawingPanel] : continueTransforming 호출 ");
		currentShape.addPoint(x, y);
	}
	
	public class Mover extends Transformer {

		public Mover(GShape shape) {
			super(shape);
			System.out.println("[MOVER] : 생성 ");

		}

		public void prepare(int x, int y) {
			System.out.println("[Mover] : prepare 호출 ");
			shape.setPoint(x, y);
		}

		public void transform(int x, int y, Graphics graphics) {
			System.out.println("[Mover] : transform 호출 ");
			shape.draw(graphics);
			shape.movePoint(x, y);
			shape.draw(graphics);
		}

		public void finalize(int x, int y) {
			System.out.println("[Mover] : finalize 호출 ");

		}
	}
	
	public class Transformer {
		protected GShape shape;

		public Transformer(GShape shape) {
			System.out.println("[Transformer] : 생성 ");

			this.shape = shape;
		}

		public void prepare(int x, int y) {
		}

		public void transform(int x, int y, Graphics graphics) {
		}

		public void finalize(int x, int y) {

		}
	}

	
	

    private class CustomMouseHandler extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			System.out.println("[CustomMouseHandler] : mouseClicked 호출 ");
			if (e.getClickCount() == 1) {
				mouse1Clicked(e);
			} else if (e.getClickCount() == 2) {
				mouse2Clicked(e);
			}

		}

		private void mouse2Clicked(MouseEvent e) {
			System.out.println("[CustomMouseHandler] : mouse2Clicked 호출 ");
			if (eDrawingState == EDrawingState.eDrawing) {
				finalizeTransforming(e.getX(), e.getY());
				eDrawingState = EDrawingState.eIdle;
			}
		}

		private void mouse1Clicked(MouseEvent e) {
			System.out.println("[CustomMouseHandler] : mouse1Clicked 호출 ");
			if (eDrawingState == EDrawingState.eIdle) {
				if ((toolBar.GetESelectedShape().getGShape() instanceof GPolygon)) {
					eDrawingState = EDrawingState.eDrawing;
					prepareTransforming(e.getX(), e.getY());
				}
			} else if (eDrawingState == EDrawingState.eDrawing) {
				if (toolBar.GetESelectedShape().getGShape() instanceof GPolygon) {
					continueTransforming(e.getX(), e.getY());
				}
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (eDrawingState == EDrawingState.eIdle) { 
				if (toolBar.GetESelectedShape() == EShape.eSelect) {
					currentShape = onShape(e.getPoint());
					if (currentShape == null) {
						eDrawingState = EDrawingState.eSelecting;
						prepareTransforming(e.getX(), e.getY());
					} else {
						// resize, rotate, move
						eDrawingState = EDrawingState.eMoving;
						prepareTransforming(e.getX(), e.getY());
					}
				} else {
					if (!(toolBar.GetESelectedShape().getGShape() instanceof GPolygon)) {
						eDrawingState = EDrawingState.eDrawing;
						prepareTransforming(e.getX(), e.getY());
					}
				}
			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			if (eDrawingState == EDrawingState.eDrawing) {
				if (!(toolBar.GetESelectedShape().getGShape() instanceof GPolygon)) {
					keepTransforming(e.getX(), e.getY());
				}
			} else if (eDrawingState == EDrawingState.eSelecting) {
				keepTransforming(e.getX(), e.getY());
			} else if (eDrawingState == EDrawingState.eMoving) {
				keepTransforming(e.getX(), e.getY());
				// move
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			if (eDrawingState == EDrawingState.eDrawing) {
				if ((toolBar.GetESelectedShape().getGShape() instanceof GPolygon)) {
					keepTransforming(e.getX(), e.getY());
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (eDrawingState == EDrawingState.eDrawing) {
				if (!(toolBar.GetESelectedShape().getGShape() instanceof GPolygon)) {
					finalizeTransforming(e.getX(), e.getY());
					eDrawingState = EDrawingState.eIdle;
				}
			} else if (eDrawingState == EDrawingState.eSelecting) {
				finalizeTransforming(e.getX(), e.getY());
				eDrawingState = EDrawingState.eIdle;
			} else if (eDrawingState == EDrawingState.eMoving) {
				finalizeTransforming(e.getX(), e.getY());
				eDrawingState = EDrawingState.eIdle;
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}


	}
    private void addShape(GShape shape) {
    	DrawingShapeData.add(shape);
    }
    
    
}