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
	private GemCollectorCollector collector;
	
	public GemCollectorTopBar(int width, GemCollectorCollector collector) {
		this.width = width;
		this.collector = collector;
	}
	
	/*
	 * Getters
	 */
	public int getWidth() {
		return width;
	}
	
	public GemCollectorCollector getCollector() {
		return collector;
	}
	
	/*
	 * Painters
	 */
	public void paint(Graphics g, int x, int y) {
		g.setColor(Color.white);
		g.fillRect(x, y, width, GemCollectorConfig.TOP_BAR_HEIGHT);

		g.setColor(Color.black);
		g.drawRect(x, y, width, GemCollectorConfig.TOP_BAR_HEIGHT);
		
		collector.paint(g);
	}	
}
