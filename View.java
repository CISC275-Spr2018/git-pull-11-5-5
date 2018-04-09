import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

/**
 * View: Contains everything about graphics and images
 * Know size of world, which images to load etc
 *
 * has methods to
 * provide boundaries
 * use proper images for direction
 * load images for all direction (an image should only be loaded once!!! why?)
 **/
public class View extends JFrame implements ActionListener{
	
	protected JButton b1;
	protected JButton b2;
	final int frameCount = 10;
    int picNum = 0;
    BufferedImage[][] pics;
	BufferedImage[] imgs = new BufferedImage[8];
	Direction direction2 = Direction.SOUTHEAST;
    final int frameWidth = 500;
    final int frameHeight = 300;
    final int picSize = 165;
    JFrame frame = new JFrame();
    public int xloc = 100;
    public int yloc = 100;
    final int frameStartSize = 800;
    final int drawDelay = 50; //msec
    Direction direction = Direction.SOUTHEAST;
    private boolean stopped = false;
	DrawPanel drawPanel = new DrawPanel();
    Action drawAction;
    BorderLayout ourLayout = new BorderLayout();
    JPanel buttonPanel = new JPanel();
    boolean dirChange = false;
	
    
    public void setUpPanel() {
    	buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    	b1 = new JButton("Stop/Start");
		b1.setVerticalTextPosition(AbstractButton.CENTER);
	    b1.setHorizontalTextPosition(AbstractButton.LEADING);
	    b1.setActionCommand("disable");
	    b1.addActionListener(this);
	    b1.setToolTipText("Click this button to stop/start the orc");
	    b2 = new JButton("Change Direction");
	    b2.setVerticalTextPosition(AbstractButton.CENTER);
	    b2.setHorizontalTextPosition(AbstractButton.LEADING);
	    b2.setActionCommand("change");
	    b2.addActionListener(this);
	    b2.setToolTipText("Click this button to randomize orc direction");
    	buttonPanel.add(b1);
    	buttonPanel.add(b2);
    	getContentPane().setBackground(Color.GRAY);
    	buttonPanel.setBackground(Color.gray);
    	drawPanel.setBackground(Color.gray);
    	buttonPanel.setForeground(Color.gray);
    	
    }
	
    public boolean getChangeDir() {
    	return dirChange;
    }
    
    public void setChangeDir(boolean a) {
    	dirChange = a;
    }
	
    public View() {
	    
	    
	    setLayout(ourLayout);
	    setUpPanel();
	    
    	drawAction = new AbstractAction(){
    		public void actionPerformed(ActionEvent e){
    			drawPanel.repaint();
    		}
    	};
    
    	
    	add(drawPanel,BorderLayout.SOUTH);
    	add(buttonPanel,BorderLayout.NORTH);
    	
    	
    	setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    	
    	imgs[0] = createImage(0, Direction.SOUTHEAST);
		imgs[1] = createImage(1, Direction.SOUTHWEST);
		imgs[2] = createImage(2, Direction.NORTHEAST);
		imgs[3] = createImage(3, Direction.NORTHWEST);
		imgs[4] = createImage(4, Direction.EAST);
		imgs[5] = createImage(5, Direction.WEST);
		imgs[6] = createImage(6, Direction.SOUTH);
		imgs[7] = createImage(7, Direction.NORTH);
		
		pics = new BufferedImage[8][frameCount];//get this dynamically
		for(int c = 0; c < imgs.length; c++){    	
			for(int i = 0; i < frameCount; i++){
				pics[c][i] = imgs[c].getSubimage(picSize*i, 0, picSize, picSize);
			}
		}
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setSize(frameStartSize, frameStartSize);
    	setVisible(true);
    	pack();
	}
	
	public void actionPerformed(ActionEvent e) {
        if ("disable".equals(e.getActionCommand())) {
            if(stopped == true)
            	stopped = false;
            else
            	stopped = true;
        }
        
        if("change".equals(e.getActionCommand())) {
        	dirChange = true;
        }
    }
	
	public boolean isStopped() {
		return stopped;
	}
	
	@SuppressWarnings("serial")
	public class DrawPanel extends JPanel {
    	int picNum = 0;

		protected void paintComponent(Graphics g) {
			g.setColor(Color.gray);
			super.paintComponent(g);
	    	picNum = (picNum + 1) % frameCount;
	    	g.drawImage(pics[getDirNum()][picNum], xloc, yloc, Color.gray, this);
		}

		public Dimension getPreferredSize() {
			return new Dimension(frameStartSize, frameStartSize);
		}
	}
	
	public int getDirNum() {
		int result = 0;
		switch(direction) {
			case SOUTHEAST:
				result = 0;
				break;
			case SOUTHWEST:
				result = 1;
				break;
			case NORTHEAST:
				result = 2;
				break;
			case NORTHWEST:
				result = 3;
				break;
			case EAST:
				result = 4;
				break;
			case WEST:
				result = 5;
				break;
			case SOUTH:
				result = 6;
				break;
			case NORTH:
				result = 7;
				break;
		}
		return result;
	}
	
	public void update(int x, int y, Direction d) {
		this.xloc = x;
		this.yloc = y;
		this.direction = d;
		drawPanel.repaint();
	}

	private BufferedImage createImage(int num, Direction direction){ //right now this just loads a single image, since we're just trying to make drawing work
    	BufferedImage bufferedImage;
    	try {
    		switch (num) {
			case 0:
				bufferedImage = ImageIO.read(new File("images/orc/orc_forward_southeast.png"));
				return bufferedImage;
			case 1:
				bufferedImage = ImageIO.read(new File("images/orc/orc_forward_southwest.png"));
				return bufferedImage;
			case 2:
				bufferedImage = ImageIO.read(new File("images/orc/orc_forward_northeast.png"));
				return bufferedImage;
			case 3:
				bufferedImage = ImageIO.read(new File("images/orc/orc_forward_northwest.png"));
				return bufferedImage;
			case 4:
				bufferedImage = ImageIO.read(new File("images/orc/orc_forward_east.png"));
				return bufferedImage;
			case 5:
				bufferedImage = ImageIO.read(new File("images/orc/orc_forward_west.png"));
				return bufferedImage;
			case 6:
				bufferedImage = ImageIO.read(new File("images/orc/orc_forward_south.png"));
				return bufferedImage;
			case 7:
				bufferedImage = ImageIO.read(new File("images/orc/orc_forward_north.png"));
				return bufferedImage;
			default:
				return null;
    		}
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return null;
    }
}