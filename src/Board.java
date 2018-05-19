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
 *   need to be implemented. And some way to win. And more
 *   effects for the gems.
 * 
 * @author: Per Nyberg
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.Timer;

import jGame.JGamePanel;

public class Board extends JGamePanel implements ActionListener, MouseListener {
	private Timer timer;
	
	private TopBar gemCollectorTopBar;
	private ArrayList<Gem> gems;
	private ArrayList<Teleportation> teleportations;
	private Player player;
	private int slotIndex;
	
	public Board(int width, int height, Player player) {
		super(width, height);
		
		addMouseListener(this);
		
		this.player = player;
		
		gems = new ArrayList<Gem>();
		teleportations = new ArrayList<Teleportation>();
		
		player.setPosition(player.getX(), player.getY() + Config.TOP_BAR_HEIGHT);
		
		gemCollectorTopBar = new TopBar(width, player.getCollector());
		slotIndex = 0;
		
		timer = new Timer(Config.TIMER_UPDATE_DELAY, this);
		timer.start();
	}
	
	/*
	 * Mutators
	 */
	public void addGem(Gem gem) {
		gem.setPosition(gem.getX(), gem.getY() + Config.TOP_BAR_HEIGHT);
		
		gems.add(gem);
	}
	
	/*
	 * ActionListener-methods
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		moveGemsTowardsSlots();
		doTeleportationRounds();
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
			GemSlot slot = gemCollectorTopBar.getCollector().getGemSlot(i);
			
			if (slot.hasGem()) {
				Gem gem = slot.getGem();
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
	
	/**
	 * Goes through all active teleportations and makes
	 *  a "round" for them (basically updating them)
	 */
	private void doTeleportationRounds() {
		for (int i = 0 ; i < teleportations.size() ; i++) {
			teleportations.get(i).doRound();
			
			if (teleportations.get(i).isDone()) {
				teleportations.remove(i);
				i--;
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
		for (Gem gem : gems) {
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
	 * MouseLstener-methods
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();
		
		// if the player can teleport, do so
		if (player.getActiveGemAttribute() == GemAttribute.BLUE_STONE && 
			!player.isTeleporting() &&
			Config.TOP_BAR_HEIGHT <= mouseY && 
			mouseY < (Config.TOP_BAR_HEIGHT + Config.BOARD_HEIGHT) &&
			0 <= mouseX && mouseX <= Config.BOARD_WIDTH) {
			int x = mouseX - mouseX % Config.TILE_SIZE;
			int y = mouseY - (mouseY-Config.TOP_BAR_HEIGHT) % Config.TILE_SIZE;
			
			teleportations.add(new Teleportation(player, x, y));
			player.setTeleporting(true);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// do nothing
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// do nothing
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// do nothing
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// do nothing
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

		paintBoardNet(g);
		
		gemCollectorTopBar.paint(g, 0, 0);
		
		for (Teleportation teleportation : teleportations) {
			teleportation.paint(g);
		}
		
		for (Gem gem : gems) {
			gem.paint(g);
		}
		
		player.paint(g);
	}
	
	private void paintBoardNet(Graphics g) {
		g.setColor(new Color(211, 223, 211));
		
		// painting column-lines
		for (int x = 0 ; x <= Config.BOARD_WIDTH ; x += Config.TILE_SIZE) {
			g.drawLine(x, 0, x, Config.BOARD_HEIGHT);
		}
		
		// painting row-lines
		for (int y = Config.TOP_BAR_HEIGHT ; y <= Config.BOARD_HEIGHT ; y += Config.TILE_SIZE) {
			g.drawLine(0, y, getPanelWidth(), y);
		}
	}
}