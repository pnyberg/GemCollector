/**
 * The topbar of the GemCollecting-game. It shows the gems
 *  currently collected.
 *  
 *  At the moment this is doing nothing more than just that.
 * 
 * @author: Per Nyberg
 */

import java.awt.Color;
import java.awt.Graphics;

public class GemCollectorTopBar {
	private int width;
	public static final int HEIGHT = 30;
	
	private GemCollectorGemSlot[] gemSlots;
	
	public GemCollectorTopBar(int width, int amountOfGems) {
		this.width = width;
		gemSlots = new GemCollectorGemSlot[amountOfGems];
		
		initGemSlots();
	}
	
	/*
	 * Mutators
	 */
	/**
	 * Initialize all the gemslots and line them up in a row
	 */
	private void initGemSlots() {
		int y = (HEIGHT - GemCollectorGemSlot.GEM_SLOT_DIAMETER) / 2;
		for (int i = 0 ; i < gemSlots.length ; i++) {
			int x = 100 + i * (GemCollectorGemSlot.GEM_SLOT_DIAMETER+10);
			GemCollectorGemSlot slot = new GemCollectorGemSlot(x, y);
			gemSlots[i] = slot;
		}
	}
	
	/*
	 * Getters
	 */
	public int getWidth() {
		return width;
	}
	
	public int getAmountOfGems() {
		return gemSlots.length;
	}
	
	public GemCollectorGemSlot getGemSlot(int index) {
		return gemSlots[index];
	}
	
	public int getGemSlotPositionX(int index) {
		return gemSlots[index].getX();
	}
	
	public int getGemSlotPositionY(int index) {
		return gemSlots[index].getY();
	}
	
	/*
	 * Painters
	 */
	public void paint(Graphics g, int x, int y) {
		g.setColor(Color.white);
		g.fillRect(x, y, width, HEIGHT);

		g.setColor(Color.black);
		g.drawRect(x, y, width, HEIGHT);
		
		for (GemCollectorGemSlot gemSlot : gemSlots) {
			gemSlot.paint(g);
		}
	}	
}
