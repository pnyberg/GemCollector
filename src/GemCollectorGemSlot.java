/**
 * This slot can hold a GemCollectorGem.
 * 
 * At the moment this has no purpose other than painting
 *  the slot for the gem.
 * 
 * @author: Per Nyberg
 */
import java.awt.Color;
import java.awt.Graphics;

public class GemCollectorGemSlot {
	private int x;
	private int y;
	private GemCollectorGem gem;
	private boolean gemSlotted;
	
	public GemCollectorGemSlot(int x, int y) {
		this.x = x;
		this.y = y;
		
		gem = null;
		gemSlotted = false;
	}
	
	/*
	 * Mutator
	 */
	public void addGem(GemCollectorGem gem) {
		this.gem = gem;
	}
	
	public void setGemSlotted(boolean gemSlotted) {
		this.gemSlotted = gemSlotted;
	}
	
	/*
	 * Checkers
	 */
	public boolean hasGem() {
		return gem != null;
	}
	
	public boolean isGemSlotted() {
		return gemSlotted;
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
	
	public GemCollectorGem getGem() {
		return gem;
	}
	
	/*
	 * Painters
	 */
	public void paint(Graphics g) {
		int diameter = GemCollectorConfig.GEM_SLOT_DIAMETER;

		if (gemSlotted) {
			g.setColor(gem.getColor());
		} else {
			g.setColor(Color.white);
		}
		g.fillOval(x, y, diameter, diameter);

		g.setColor(Color.black);
		g.drawOval(x, y, diameter, diameter);

		g.setColor(Color.gray);
		g.fillOval(x+2, y+2, diameter-4, diameter-4);
		
		if (gemSlotted) {
			gem.paint(g);
		}
	}
}
