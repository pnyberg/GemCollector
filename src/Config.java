/**
 * A file with config-data (so it's centralised).
 *  Could possibly format this in a nicer fashion
 *  but it's good enough for now.
 * 
 * @author: Per Nyberg
 */
public class Config {
	// timer
	public static final int TIMER_UPDATE_DELAY = 10;
	
	// board
	public static final int TILE_SIZE = 40;

	// gem slot
	public static final int GEM_SLOT_DIAMETER = TILE_SIZE;
	
	// top bar
	public static final int TOP_BAR_HEIGHT = TILE_SIZE + 10; // should be a little bit bigger than the tile
	
	// frame
	public static final int NUMBER_OF_TILES_WIDTH = 20;
	public static final int NUMBER_OF_TILES_HEIGHT = 10;
	public static final int BOARD_WIDTH = NUMBER_OF_TILES_WIDTH * TILE_SIZE;
	public static final int BOARD_HEIGHT = TOP_BAR_HEIGHT + NUMBER_OF_TILES_HEIGHT * TILE_SIZE;
}