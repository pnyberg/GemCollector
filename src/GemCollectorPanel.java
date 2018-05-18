/**
 * The panel does most of the actions for the GemCollecting
 *  and also paints up the whole thing. The players is moved
 *  throughout key-input and the panel is updated depending
 *  on a specified delay-time.
 *  
 *  The width and the height as well as the amounts of gems
 *   are set by the constructor which the player then can 
 *   collect.
 *   
 *  At the moment the player can go wherever it want, but
 *   if this would be a more serious game then bounds would
 *   need to be implemented.
 * 
 * @author: Per Nyberg
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.Timer;

import jGame.JGamePanel;

public class GemCollectorPanel extends JGamePanel implements ActionListener {
	private Timer timer;
	
	private GemCollectorTopBar gemCollectorTopBar;
	private int amountOfGems;
	private ArrayList<GemCollectorGem> gems;
	private GemCollectorPlayer player;
	private int slotIndex;
	
	public GemCollectorPanel(int width, int height, GemCollectorPlayer player) {
		super(width, height);
		
		this.amountOfGems = amountOfGems;
		this.player = player;
		
		gems = new ArrayList<GemCollectorGem>();
		
		player.setPosition(player.getX(), player.getY() + GemCollectorConfig.TOP_BAR_HEIGHT);
		
		gemCollectorTopBar = new GemCollectorTopBar(width, player.getCollector());
		slotIndex = 0;
		
		timer = new Timer(GemCollectorConfig.TIMER_UPDATE_DELAY, this);
		timer.start();
	}
	
	/*
	 * Mutators
	 */
	public void addGem(GemCollectorGem gem) {
		gem.setPosition(gem.getX(), gem.getY() + GemCollectorConfig.TOP_BAR_HEIGHT);
		
		gems.add(gem);
	}
	

	/*
	 * ActionListener-methods
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		moveGemsTowardsSlots();
		repaint();
	}
	
	/**
	 * If a gem has been picked up it will move to its
	 *  allocated slot. When the gem has reached its 
	 *  slot it will be removed from the "public" gems
	 *  and it will become "slotted" for that slot.
	 */
	private void moveGemsTowardsSlots() {
		for (int i = 0 ; i < gemCollectorTopBar.getCollector().getAmountOfGems() ; i++) {
			GemCollectorGemSlot slot = gemCollectorTopBar.getCollector().getGemSlot(i);
			
			if (slot.hasGem()) {
				GemCollectorGem gem = slot.getGem();
				int yDiff = slot.getY() - gem.getY();
				
				// if there is an diff in y-values
				if (yDiff < 0) {
					gem.setPosition(gem.getX(), gem.getY()-1);
					continue;
				}
				
				int xDiff = slot.getX() - slot.getGem().getX();
				if (xDiff > 0) {
					gem.setPosition(gem.getX()+1, gem.getY());
				} else if (xDiff < 0) {
					gem.setPosition(gem.getX()-1, gem.getY());
				} else {
					slot.setGemSlotted(true);
					gems.remove(gem);
				}
			}
		}
	}
	
	/*
	 * KeyListener-methods
	 */
	/**
	 * Check if a key was entered to move the player. Then
	 *  check if the player "stepped" on a gem.
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == 'w') {
			player.goUp();
		} else if (e.getKeyChar() == 'd') {
			player.goRight();
		} else if (e.getKeyChar() == 's') {
			player.goDown();
		} else if (e.getKeyChar() == 'a') {
			player.goLeft();
		} else if (e.getKeyChar() == 'q') {
			player.changeToNextGemSlot();
		}
		
		checkPlayerGemInteraction();
	}
	
	/**
	 * Check if a player "collects" a gem
	 */
	private void checkPlayerGemInteraction() {
		for (GemCollectorGem gem : gems) {
			if (gem.getX() == player.getX() && gem.getY() == player.getY()) {
				gemCollectorTopBar.getCollector().getGemSlot(slotIndex).addGem(gem);
				slotIndex++;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// do nothing
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// do nothing
	}
	
	/*
	 * Getters
	 */
	public int getAmountOfGems() {
		return amountOfGems;
	}

	/*
	 * Painters
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Color lightLightGray = new Color(211, 211, 223);
		
		g.setColor(lightLightGray);
		g.fillRect(0, 0, getPanelWidth(), getPanelHeight());
		
		gemCollectorTopBar.paint(g, 0, 0);
		
		for (GemCollectorGem gem : gems) {
			gem.paint(g);
		}
		
		player.paint(g);
	}
}
