package View_Frame;

import javax.swing.JMenuBar;

public class GMenubar extends JMenuBar {

	private static final long serialVersionUID = 1L;
	private GFileMenu fileMenu;
	
	public GMenubar() {
		this.fileMenu = new GFileMenu("File");
		this.add(this.fileMenu);		
	}
	

}
