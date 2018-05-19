/**
 * This class handles teleportation for the player.
 *  The teleportation process has "rounds" with
 *  different phases that handles the different 
 *  steps of the teleportation (mostly graphical
 *  stuff). But basically it moves the player 
 *  from it's current position to it's "next"
 *  position.
 * 
 * @author: Per Nyberg
 */
import java.awt.Color;
import java.awt.Graphics;

public class Teleportation {
	// the phases
	private static final int SHRINK_PLAYER_PHASE = 0;
	private static final int SHRINK_CIRCLE_BEFORE_TELEPORTATION_PHASE = 1;
	private static final int GROW_CIRCLE_PHASE = 2;
	private static final int GROW_PLAYER_PHASE = 3;
	private static final int WAIT_PHASE = 4;
	private static final int SHRINK_CIRCLE_AFTER_TELEPORTATION_PHASE = 5;
	private static final int DONE_PHASE = 6;
	
	// other variables
	private Player player;
	private int nextX;
	private int nextY;
	
	private int x;
	private int y;
	private int size;
	
	private int alternatingIndex;
	private int phaseIndex;
	
	public Teleportation(Player player, int nextX, int nextY) {
		this.player = player;
		this.nextX = nextX;
		this.nextY = nextY;
		
		size = Config.TILE_SIZE * 3;
		x = player.getX() - Config.TILE_SIZE;
		y = player.getY() - Config.TILE_SIZE;
		
		alternatingIndex = 0;
		phaseIndex = SHRINK_PLAYER_PHASE;
	}
	
	/*
	 * Mutators 
	 */
	public void doRound() {
		if (phaseIndex == SHRINK_PLAYER_PHASE) {
			// shrink the player every other round, until size = 0
			if (alternatingIndex++%2 == 1) {
				player.shrink();
				
				if (player.getSize() == 0) {
					phaseIndex = SHRINK_CIRCLE_BEFORE_TELEPORTATION_PHASE;
				}
			}
		} else if (phaseIndex == SHRINK_CIRCLE_BEFORE_TELEPORTATION_PHASE) {
			// shrink the portal every round, until size = 0
			//  then move circle and player to "next" position
			size -= 2;
			x++;
			y++;
			
			if (size == 0) {
				phaseIndex = GROW_CIRCLE_PHASE;
				player.setPosition(nextX + Config.TILE_SIZE/2, nextY + Config.TILE_SIZE/2);
				x = nextX + Config.TILE_SIZE/2;
				y = nextY + Config.TILE_SIZE/2;
			}
		} else if (phaseIndex == GROW_CIRCLE_PHASE) {
			// grow the portal every round, until "max-size"
			size += 2;
			x--;
			y--;
			
			if (size == Config.TILE_SIZE * 3) {
				phaseIndex = GROW_PLAYER_PHASE;
			}
		} else if (phaseIndex == GROW_PLAYER_PHASE) {
			// grow player every round, until tile-size
			player.grow();
			
			if (player.getSize() == Config.TILE_SIZE) {
				phaseIndex = WAIT_PHASE;
				alternatingIndex = 0;
			}
		} else if (phaseIndex == WAIT_PHASE) {
			// wait for a certain amount of rounds, at the moment 30
			if (alternatingIndex++ == 30) {
				phaseIndex = SHRINK_CIRCLE_AFTER_TELEPORTATION_PHASE;
			}
		} else if (phaseIndex == SHRINK_CIRCLE_AFTER_TELEPORTATION_PHASE) {
			// shrink portal every round (again), until size = 0
			size -= 2;
			x++;
			y++;
			
			if (size == 0) {
				phaseIndex = DONE_PHASE;
				player.setTeleporting(false);
			}
		}
	}
	
	/*
	 * Getters
	 */
	public boolean isDone() {
		return phaseIndex == DONE_PHASE;
	}
	
	/*
	 * Paint-methods
	 */
	public void paint(Graphics g) {
		// Since the teleportation is connected to the blue
		//  stone the color for the portal comes form the gem
		g.setColor(GemAttribute.BLUE_STONE.getColor().darker());
		g.fillOval(x, y, size, size);
		g.setColor(Color.black);
		g.drawOval(x, y, size, size);
	}
}