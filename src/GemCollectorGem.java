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

public class GemCollectorGem {
	private int x;
	private int y;
	private int size;
	private Color color;
	
	public GemCollectorGem(int x, int y, int size, Color color) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.color = color;
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
	
	public Color getColor() {
		return color;
	}
	
	/*
	 * Painters
	 */
	public void paint(Graphics g) {
		g.setColor(color);
		g.fillOval(x + size / 4, y + size / 4, size / 2, size / 2);
		
		g.setColor(Color.black);
		g.drawOval(x + size / 4, y + size / 4, size / 2, size / 2);
	}
}
