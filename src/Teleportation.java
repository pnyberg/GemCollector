import java.awt.Color;
import java.awt.Graphics;

public class Teleportation {
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
		phaseIndex = -1;
	}
	
	/*
	 * Mutators 
	 */
	public void doRound() {
		if (phaseIndex == -1) {
			if (alternatingIndex++%2 == 1) {
				player.shrink();
				
				if (player.getSize() == 0) {
					phaseIndex = 0;
				}
			}
		} else if (phaseIndex == 0) {
			size -= 2;
			x++;
			y++;
			
			if (size == 0) {
				phaseIndex = 1;
				player.setPosition(nextX + Config.TILE_SIZE/2, nextY + Config.TILE_SIZE/2);
				x = nextX + Config.TILE_SIZE/2;
				y = nextY + Config.TILE_SIZE/2;
			}
		} else if (phaseIndex == 1) {
			size += 2;
			x--;
			y--;
			
			if (size == Config.TILE_SIZE * 3) {
				phaseIndex = 2;
			}
		} else if (phaseIndex == 2) {
			player.grow();
			
			if (player.getSize() == Config.TILE_SIZE) {
				phaseIndex = 3;
				alternatingIndex = 0;
			}
		} else if (phaseIndex == 3) {
			if (alternatingIndex++%30 == 29) {
				phaseIndex = 4;
			}
		} else if (phaseIndex == 4) {
			size -= 2;
			x++;
			y++;
			
			if (size == 0) {
				phaseIndex = 5;
				player.setTeleporting(false);
			}
		}
	}
	
	/*
	 * Getters
	 */
	public boolean isDone() {
		return phaseIndex == 5;
	}
	
	/*
	 * Paint-methods
	 */
	public void paint(Graphics g) {
		g.setColor(GemAttribute.BLUE_STONE.getColor().darker());
		g.fillOval(x, y, size, size);
		g.setColor(Color.black);
		g.drawOval(x, y, size, size);
	}
}
