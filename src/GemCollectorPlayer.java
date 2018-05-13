/**
 * The player is given a start-position and a color and can then
 *  be moved through-out the map.
 *  
 *  At the moment the player does nothing special with the collected
 *   gems.
 * 
 * @author: Per Nyberg
 */

import java.awt.Color;
import java.awt.Graphics;

public class GemCollectorPlayer {
	private int x;
	private int y;
	private int size;
	private Color color;
	
	public GemCollectorPlayer(int x, int y, int size, Color color) {
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
	
	public void goUp() {
		y -= size;
	}
	
	public void goDown() {
		y += size;
	}
	
	public void goLeft() {
		x -= size;
	}
	
	public void goRight() {
		x += size;
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
		g.fillRect(x, y, size, size);
		g.setColor(Color.black);
		g.drawRect(x, y, size, size);
	}
}
