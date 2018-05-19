/**
 * The enum holds attributes (image and color) 
 *  for predefined stones. Used to define the
 *  gems for effects etc.
 * 
 * @author: Per Nyberg
 */
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;

public enum GemAttribute {
	PURPLE_STONE("./images/purple_stone.png", new Color(138,43,226)),
	RED_STONE("./images/red_stone.png", Color.red),
	BLUE_STONE("./images/blue_stone.png", Color.blue),
	GREEN_STONE("./images/green_stone.png", Color.green),
	ORANGE_STONE("./images/orange_stone.png", Color.orange);
	
	private Image image;
	private Color color;

	GemAttribute(String imagePath, Color color) {
		this.image = Toolkit.getDefaultToolkit().getImage(imagePath);
		this.color = color;
	}
	
	public Image getImage() {
		return image;
	}
	
	public Color getColor() {
		return color;
	}
}
