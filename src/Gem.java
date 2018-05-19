/**
 * The gems are supposed to be collected by the player.
 *  The gem-attribute holds the image and the color for
 *  which the gem shows. The gem is also smaller than
 *  the tile (so it's inside of the tile).
 *  
 *  At this moment they have no more use than that.
 * 
 * @author: Per Nyberg
 */

import java.awt.Color;
import java.awt.Graphics;

public class Gem {
	private int x;
	private int y;
	private int size;
	private GemAttribute gemAttribute;
	
	public Gem(int x, int y, int size, GemAttribute gemAttribute) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.gemAttribute = gemAttribute;
	}

	/*
	 * Mutators
	 */
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/*
	 * Getters
	 */
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getSize() {
		return size;
	}
	
	public GemAttribute getGemAttribute() {
		return gemAttribute;
	}
	
	public Color getColor() {
		return gemAttribute.getColor();
	}
	
	/*
	 * Painters
	 */
	public void paint(Graphics g) {
		// The aligntment and the size is because we want the image to be a 
		//  little bit smaller than the tile
		int alignment = size/8;
		int imageSize = (3 * size) / 4;
		g.drawImage(gemAttribute.getImage(), x+alignment, y+alignment, 
					imageSize, imageSize, null);
	}
}
