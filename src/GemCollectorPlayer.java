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
	private int activeSlotIndex;
	private GemCollectorCollector collector;
	
	public GemCollectorPlayer(int x, int y, int size, Color color, GemCollectorCollector collector) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.color = color;
		this.collector = collector;
		
		activeSlotIndex = -1;
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
	
	public void changeToNextGemSlot() {
		for (int i = activeSlotIndex+1 ; i < collector.getAmountOfGems() ; i++) {
			if (collector.getGemSlot(i).isGemSlotted()) {
				activeSlotIndex = i;
				return;
			}
		}
		
		activeSlotIndex = -1;
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
	
	public GemCollectorCollector getCollector() {
		return collector;
	}
	
	private int getNumberOfSlottedGems() {
		int numberOfSlottedGems = 0;
		
		for (int i = 0 ; i < collector.getAmountOfGems() ; i++) {
			if (collector.getGemSlot(i).isGemSlotted()) {
				numberOfSlottedGems++;
			}
		}
		
		return numberOfSlottedGems;
	}
	
	/*
	 * Painters
	 */
	public void paint(Graphics g) {
		if (activeSlotIndex != -1) {
			g.setColor(collector.getGemSlot(activeSlotIndex).getGem().getColor());
			g.fillRect(x-2, y-2, size+5, size+5);			
		}
		g.setColor(color);
		g.fillRect(x, y, size, size);
		g.setColor(Color.black);
		g.drawRect(x, y, size, size);
	}
}
