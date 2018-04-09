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
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;



/**
 * Model: Contains all the state and logic
 * Does not contain anything about images or graphics, must ask view for that
 *
 * has methods to
 *  detect collision with boundaries
 * decide next direction
 * provide direction
 * provide location
 **/
public class Model{
	
	private static Direction direction  = Direction.SOUTHEAST;
	private int xLoc = 0;
	private int yLoc = 0;
	private int frameHeight;
	private int frameWidth;
	private int imageWidth;
	private int imageHeight;
	final int xIncr = 8;
    final int yIncr = 2;
	
	public Model(int imgWidth, int imgHeight, int width, int height) {
		imageWidth = imgWidth;
		imageHeight = imgHeight;
		frameHeight = height;
		frameWidth = width;
	}
	
	public int getX() {
		return xLoc;
	}
	
	public int getY() {
		return yLoc;
	}
	
	public Direction getDirect() {
		return direction;
	}
	
	public void setDirect(int a) {
		switch(a) {
			case 0:// Southeast
				if(direction == Direction.SOUTHEAST)
					direction = Direction.SOUTH;
				else
					direction = Direction.SOUTHEAST;
				break;
			case 1:// South
				if(direction == Direction.SOUTH)
					direction = Direction.SOUTHWEST;
				else
					direction = Direction.SOUTH;
				break;
			case 2:// Southwest
				if(direction == Direction.SOUTHWEST)
					direction = Direction.WEST;
				else
					direction = Direction.SOUTHWEST;
				break;
			case 3:// West
				if(direction == Direction.WEST)
					direction = Direction.NORTHWEST;
				else 
					direction = Direction.WEST;
				break;
			case 4: // Northwest
				if(direction == Direction.NORTHWEST)
					direction = Direction.NORTH;
				else
					direction = Direction.NORTHWEST;
				break;
			case 5:// North
				if(direction == Direction.NORTH)
					direction = Direction.NORTHEAST;
				else
					direction = Direction.NORTH;
				break;
			case 6:// Northeast
				if(direction == Direction.NORTHEAST)
					direction = Direction.EAST;
				else
					direction = Direction.NORTHEAST;
				break;
			case 7:// East
				if(direction == Direction.EAST)
					direction = Direction.SOUTHEAST;
				else
					direction = Direction.EAST;
		}
	}
	
	public void updateLocationAndDirection() {
		switch(direction) {
			case NORTH:
				if((yLoc) <= 0)
					direction = Direction.SOUTH;
				break;
			case NORTHEAST:
				if((yLoc) <= 0) {
					if((xLoc + imageWidth)>= frameWidth)
						direction = Direction.SOUTHWEST;
					else
						direction = Direction.SOUTHEAST;
				}else {
					if((xLoc + imageWidth) >= frameWidth)
						direction = Direction.NORTHWEST;
				}
				break;
			case EAST:
				if((xLoc + imageWidth) >= frameWidth)
					direction = Direction.WEST;
				break;
			case SOUTHEAST:
				if((yLoc + imageHeight) >= frameHeight - 60) {
					
					// The frameHeight is greater than that of the window that pops up
					// on the computer.
					// REASON: frameStartSize is 800, frameHeight is always 92 units
					// greater than the frameStartSize.
					if((xLoc + imageWidth) >= frameWidth)
						direction = Direction.NORTHWEST;
					else
						direction = Direction.NORTHEAST;
				}else {
					if((xLoc + imageWidth) >= frameWidth)
						direction = Direction.SOUTHWEST;
				}
				break;
			case SOUTH:
				if((yLoc + imageHeight) >= frameHeight - 60)
					direction = Direction.NORTH;
				break;
			case SOUTHWEST:
				if((yLoc + imageHeight) >= frameHeight - 60) {
					if((xLoc + imageWidth) <= 0)
						direction = Direction.NORTHEAST;
					else
						direction = Direction.NORTHWEST;
				}else {
					if((xLoc) <= 0)
						direction = Direction.SOUTHEAST;
				}
				break;
			case WEST:
				if((xLoc) <= 0)
					direction = Direction.EAST;
				break;
			case NORTHWEST:
				if((yLoc) <= 0) {
					if((xLoc) <= 0)
						direction = Direction.SOUTHEAST;
					else
						direction = Direction.SOUTHWEST;
				}else {
					if((xLoc ) <= 0)
						direction = Direction.NORTHEAST;
				}
				break;
		}
		
		switch(direction) {
			case NORTH:
				yLoc -= yIncr;
				break;
			case NORTHEAST:
				yLoc -= yIncr;
				xLoc += xIncr;
				break;
			case EAST:
				xLoc += xIncr;
				break;
			case SOUTHEAST:
				yLoc += yIncr;
				xLoc += xIncr;
				break;
			case SOUTH:
				yLoc += yIncr;
				break;
			case SOUTHWEST:
				yLoc += yIncr;
				xLoc -= xIncr;
				break;
			case WEST:
				xLoc -= xIncr;
				break;
			case NORTHWEST:
				yLoc -= yIncr;
				xLoc -= xIncr;
				break;
		}
	}
}