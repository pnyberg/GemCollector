/**
 * The gems has positions and colors and are supposed to
 *  be collected by the player.
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
		g.drawImage(gemAttribute.getImage(), x+size/8, y+size/8, (3 * size) / 4, (3 * size) / 4, null);
	}
}
