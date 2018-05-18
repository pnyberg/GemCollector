/**
 * This games goal is to collect all the gems
 *  on the board. If the player does this, then
 *  nothing happens, it just looks cool.
 *  
 * Features that can be added:
 * - Adding functionality to the gems
 * - Adding something that happens when you "win"
 */

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;

import jGame.JGameFrame;

public class GemCollecting extends JGameFrame {
	private GemCollectorPanel gemCollectorPanel;
	private GemCollectorPlayer player;
	
	public GemCollecting() {
		int amountOfGems = 5;

		initPlayer(2, 2, amountOfGems, Color.black);
		gemCollectorPanel = new GemCollectorPanel(GemCollectorConfig.BOARD_WIDTH, GemCollectorConfig.BOARD_HEIGHT, player);
		
		addPanel(gemCollectorPanel);
		
		initGems();
	}
	
	/**
	 * Creates the gems and adds them to the panel
	 */
	private void initGems() {
		Color purple = new Color(138,43,226);
		Image purple_image = Toolkit.getDefaultToolkit().getImage("./images/purple_stone.png");
		initGem(5, 5, purple_image, purple);

		Image red_image = Toolkit.getDefaultToolkit().getImage("./images/red_stone.png");
		initGem(1, 3, red_image, Color.red);

		Image blue_image = Toolkit.getDefaultToolkit().getImage("./images/blue_stone.png");
		initGem(4, 1, blue_image, Color.blue);
		
		Image green_image = Toolkit.getDefaultToolkit().getImage("./images/green_stone.png");
		initGem(2, 5, green_image, Color.green);

		Image orange_image = Toolkit.getDefaultToolkit().getImage("./images/orange_stone.png");
		initGem(3, 2, orange_image, Color.orange);
	}
	
	private void initPlayer(int x, int y, int amountOfGems, Color color) {
		GemCollectorCollector collector = new GemCollectorCollector(amountOfGems);
		player = new GemCollectorPlayer(x * GemCollectorConfig.TILE_SIZE, y * GemCollectorConfig.TILE_SIZE, GemCollectorConfig.TILE_SIZE, color, collector);
	}
	
	private void initGem(int x, int y, Image image, Color color) {
		GemCollectorGem gem = new GemCollectorGem(x * GemCollectorConfig.TILE_SIZE, y * GemCollectorConfig.TILE_SIZE, GemCollectorConfig.TILE_SIZE, image, color);
		gemCollectorPanel.addGem(gem);
	}
	
	public static void main(String[] args) {
		new GemCollecting();
	}
}
