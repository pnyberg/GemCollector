/**
 * This slot can hold a Gem. It lights up with the color
 *  of the gem when it gets "slotted".
 * 
 * At the moment this has no purpose other than painting
 *  the slot for the gem.
 * 
 * @author: Per Nyberg
 */
import java.awt.Color;
import java.awt.Graphics;

public class GemSlot {
	private int x;
	private int y;
	private Gem gem;
	private boolean gemSlotted;
	
	public GemSlot(int x, int y) {
		this.x = x;
		this.y = y;
		
		gem = null;
		gemSlotted = false;
	}
	
	/*
	 * Mutator
	 */
	public void addGem(Gem gem) {
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
	
	public Gem getGem() {
		return gem;
	}
	
	/*
	 * Painters
	 */
	public void paint(Graphics g) {
		int diameter = Config.GEM_SLOT_DIAMETER;

		// Paint the outer circe (white/gem-colored)
		if (gemSlotted) {
			g.setColor(gem.getColor());
		} else {
			g.setColor(Color.white);
		}
		g.fillOval(x, y, diameter, diameter);

		g.setColor(Color.black);
		g.drawOval(x, y, diameter, diameter);

		// Paint the inner circle
		g.setColor(Color.gray);
		g.fillOval(x+2, y+2, diameter-4, diameter-4);
		
		// if a gem is slotted it gets painted as well
		if (gemSlotted) {
			gem.paint(g);
		}
	}
}
