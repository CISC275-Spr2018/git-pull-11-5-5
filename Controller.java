import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.*;

public class Controller {

	private Model model;
	private View view;
	
	public Controller(){
		view = new View();
		model = new Model(view.picSize, view.picSize, view.getWidth(), view.getHeight());
	}
	
	
	public static void main(String[] args) { //this was the only thing we changed, it's basically just the main of Animation4Thread
		Controller c = new Controller();
		EventQueue.invokeLater(new Runnable(){
			// Create a new Action here- gameAction
			Action gameAction = new AbstractAction(){
				public void actionPerformed(ActionEvent event){
					if(!c.view.isStopped()){
						if(c.view.getChangeDir()) {
							Random r = new Random();
							c.model.setDirect(r.nextInt(7));
							c.view.setChangeDir(false);
						}
						c.model.updateLocationAndDirection();
						c.view.update(c.model.getX(),c.model.getY(),c.model.getDirect());
					}
				}
			};
			public void run(){
				// Have an update method
				
				Timer t = new Timer(c.view.drawDelay, gameAction);
				t.start();

			}
		});
	}
	

}
