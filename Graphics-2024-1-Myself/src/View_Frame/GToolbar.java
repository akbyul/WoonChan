package View_Frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

import View_ShapeClass.GLine;
import View_ShapeClass.GOval;
import View_ShapeClass.GPolygon;
import View_ShapeClass.GRectangle;
import View_ShapeClass.GSelect;
import View_ShapeClass.GShape;


public class GToolbar extends JToolBar{

	private static final long serialVersionUID = 1L;
	////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Setting Shape Enum class
	public enum EShape {
		
		eSelect("Select", new GSelect()), eRectangle("Rectangle", new GRectangle()), eOval("Oval", new GOval()),
		eLine("Line", new GLine()), eLine1("Line1", new GLine()), ePolygon("Polygon", new GPolygon()); // object		
		
		private String name;
		private GShape gShape;
		
		private EShape(String name, GShape gShape) {
			this.name = name;
			this.gShape = gShape;
		}
		
		public String getName() {
			System.out.println("[GToolbar:EShape] : EShape.getName 호출 ");
			return this.name;
		}

		GShape getGShape() {
			System.out.println("[GToolbar:EShape] : EShape.getGShape 호출 ");
			return this.gShape;
		}
	}
	//
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private EShape eSelectedShape;
	private JRadioButton selectButton;
	private ButtonGroup buttonGroup;
	
	// Enum State Get
	public EShape GetESelectedShape() {
		return this.eSelectedShape;
	}
	
	// Enum State Reset
	public void resetESelectedShape() {
		this.buttonGroup.clearSelection();
		this.eSelectedShape = EShape.eSelect;
	}	
	
	// Constructor
	public GToolbar() {
		this.buttonGroup = new ButtonGroup();
		ActionHandler actionHandler = new ActionHandler();
		
		for (EShape eButtonShape : EShape.values()) {
			JRadioButton buttonShape = new JRadioButton(eButtonShape.getName());
			this.add(buttonShape);
			buttonShape.setActionCommand(eButtonShape.toString());
			buttonShape.addActionListener(actionHandler);
			System.out.println("[GToolbar] : Initialize Button = " + eButtonShape.toString());
			this.buttonGroup.add(buttonShape);
		}
		resetESelectedShape();
	}
	
	
	private class ActionHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			eSelectedShape = EShape.valueOf(event.getActionCommand());
		}
	}
}