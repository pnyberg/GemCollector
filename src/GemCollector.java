/**
 * A collector that holds the slots for the gems.
 *  Should be connected with the player collecting
 *  the gems.
 * 
 * @author Per Nyberg
 */
import java.awt.Graphics;

public class GemCollector {
	private GemSlot[] gemSlots;
	
	public GemCollector(int amountOfGems) {
		gemSlots = new GemSlot[amountOfGems];
		
		initGemSlots();
	}
	
	/*
	 * Mutators
	 */
	/**
	 * Initialize all the gemslots and give them coordinates
	 *  such that they line up in a row
	 */
	private void initGemSlots() {
		int xAlignment = 100;
		int xSpaceBetweenSlots = 10;
		int y = (Config.TOP_BAR_HEIGHT - Config.GEM_SLOT_DIAMETER) / 2;
		
		for (int i = 0 ; i < gemSlots.length ; i++) {
			int x = xAlignment + i * (Config.GEM_SLOT_DIAMETER + xSpaceBetweenSlots);
			gemSlots[i] = new GemSlot(x, y);
		}
	}
	
	/*
	 * Getters
	 */
	public int getAmountOfGems() {
		return gemSlots.length;
	}
	
	public GemSlot getGemSlot(int index) {
		return gemSlots[index];
	}
	
	public int getGemSlotPositionX(int index) {
		return gemSlots[index].getX();
	}
	
	public int getGemSlotPositionY(int index) {
		return gemSlots[index].getY();
	}
	
	/*
	 * Paint-methods
	 */
	public void paint(Graphics g) {
		for (GemSlot gemSlot : gemSlots) {
			gemSlot.paint(g);
		}
	}
}
