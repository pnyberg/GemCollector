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
	private Panel gemCollectorPanel;
	private Player player;
	
	public GemCollecting() {
		int amountOfGems = 5;

		initPlayer(2, 2, amountOfGems, Color.black);
		gemCollectorPanel = new Panel(Config.BOARD_WIDTH, Config.BOARD_HEIGHT, player);
		
		addPanel(gemCollectorPanel);
		
		initGems();
	}
	
	/**
	 * Creates the gems and adds them to the panel
	 */
	private void initGems() {
		initGem(5, 5, GemAttribute.PURPLE_STONE);
		initGem(1, 3, GemAttribute.RED_STONE);
		initGem(4, 1, GemAttribute.BLUE_STONE);
		initGem(2, 5, GemAttribute.GREEN_STONE);
		initGem(3, 2, GemAttribute.ORANGE_STONE);
	}
	
	private void initPlayer(int x, int y, int amountOfGems, Color color) {
		GemCollector collector = new GemCollector(amountOfGems);
		player = new Player(x * Config.TILE_SIZE, y * Config.TILE_SIZE, Config.TILE_SIZE, color, collector);
	}
	
	private void initGem(int x, int y, GemAttribute gemAttribute) {
		Gem gem = new Gem(x * Config.TILE_SIZE, y * Config.TILE_SIZE, Config.TILE_SIZE, gemAttribute);
		gemCollectorPanel.addGem(gem);
	}
	
	public static void main(String[] args) {
		new GemCollecting();
	}
}
