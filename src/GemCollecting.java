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

import jGame.JGameFrame;

public class GemCollecting extends JGameFrame {
	public static final int WIDTH = 500;
	public static final int HEIGHT = 200;
	
	private GemCollectorPanel gemCollectorPanel;
	private GemCollectorPlayer player;
	
	public GemCollecting() {
		int amountOfGems = 5;

		player = new GemCollectorPlayer(40, 40, 20, Color.black);
		gemCollectorPanel = new GemCollectorPanel(WIDTH, HEIGHT, amountOfGems, player);
		
		addPanel(gemCollectorPanel);
		
		initGems();
	}
	
	/**
	 * Creates the gems and adds them to the panel
	 */
	private void initGems() {
		Color purple = new Color(138,43,226);

		GemCollectorGem purpleGem = new GemCollectorGem(100, 100, 20, purple);
		GemCollectorGem redGem = new GemCollectorGem(20, 60, 20, Color.red);
		GemCollectorGem blueGem = new GemCollectorGem(80, 20, 20, Color.blue);
		GemCollectorGem greenGem = new GemCollectorGem(40, 100, 20, Color.green);
		GemCollectorGem orangeGem = new GemCollectorGem(60, 40, 20, Color.orange);
		
		gemCollectorPanel.addGem(purpleGem);
		gemCollectorPanel.addGem(redGem);
		gemCollectorPanel.addGem(blueGem);
		gemCollectorPanel.addGem(greenGem);
		gemCollectorPanel.addGem(orangeGem);
	}
	
	public static void main(String[] args) {
		new GemCollecting();
	}
}
