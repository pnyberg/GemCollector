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
	 * Initialize all the gemslots and line them up in a row
	 */
	private void initGemSlots() {
		int y = (Config.TOP_BAR_HEIGHT - Config.GEM_SLOT_DIAMETER) / 2;
		for (int i = 0 ; i < gemSlots.length ; i++) {
			int x = 100 + i * (Config.GEM_SLOT_DIAMETER+10);
			GemSlot slot = new GemSlot(x, y);
			gemSlots[i] = slot;
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
