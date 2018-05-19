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

public class TopBar {
	private int width;
	private GemCollector collector;
	
	public TopBar(int width, GemCollector collector) {
		this.width = width;
		this.collector = collector;
	}
	
	/*
	 * Getters
	 */
	public int getWidth() {
		return width;
	}
	
	public GemCollector getCollector() {
		return collector;
	}
	
	/*
	 * Painters
	 */
	public void paint(Graphics g, int x, int y) {
		Color color = Color.darkGray;
		g.setColor(Color.darkGray);
		g.fillRect(x, y, width, Config.TOP_BAR_HEIGHT);

		g.setColor(Color.black);
		g.drawRect(x, y, width, Config.TOP_BAR_HEIGHT);
		
		collector.paint(g);
	}	
}
