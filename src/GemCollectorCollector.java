import java.awt.Graphics;

public class GemCollectorCollector {
	private GemCollectorGemSlot[] gemSlots;
	
	public GemCollectorCollector(int amountOfGems) {
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
		int y = (GemCollectorConfig.TOP_BAR_HEIGHT - GemCollectorConfig.GEM_SLOT_DIAMETER) / 2;
		for (int i = 0 ; i < gemSlots.length ; i++) {
			int x = 100 + i * (GemCollectorConfig.GEM_SLOT_DIAMETER+10);
			GemCollectorGemSlot slot = new GemCollectorGemSlot(x, y);
			gemSlots[i] = slot;
		}
	}
	
	/*
	 * Getters
	 */
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
	 * Paint-methods
	 */
	public void paint(Graphics g) {
		for (GemCollectorGemSlot gemSlot : gemSlots) {
			gemSlot.paint(g);
		}
	}
}
