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
import java.awt.Image;
import java.awt.Toolkit;

public class Gem {
	private int x;
	private int y;
	private int size;
	private Image image;
	private Color color;
	
	public Gem(int x, int y, int size, Image image, Color color) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.image = image;
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
		g.drawImage(image, x+size/8, y+size/8, (3 * size) / 4, (3 * size) / 4, null);
	}
}
