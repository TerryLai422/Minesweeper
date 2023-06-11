package edu.bayview.view;

import java.awt.Dimension;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class TileView extends JLabel {

	private static final long serialVersionUID = 1L;
	private static final String IMAGE_PATH = "src/resources/tile/";
	private static Map<Integer, Image> imageMap = new HashMap<>();
	private final int length;
	private int type;

	static {
		for (int i = 0; i < 9; i++) {
			ImageIcon imageIcon = new ImageIcon(IMAGE_PATH + i + ".png");
			Image image = imageIcon.getImage();
			imageMap.put(i, image);
		}
		ImageIcon imageIcon55 = new ImageIcon(IMAGE_PATH + 55 + ".png");
		Image image55 = imageIcon55.getImage();
		imageMap.put(55, image55);
		ImageIcon imageIcon66 = new ImageIcon(IMAGE_PATH + 66 + ".png");
		Image image66 = imageIcon66.getImage();
		imageMap.put(66, image66);
		ImageIcon imageIcon77 = new ImageIcon(IMAGE_PATH + 77 + ".png");
		Image image77 = imageIcon77.getImage();
		imageMap.put(77, image77);
		ImageIcon imageIcon88 = new ImageIcon(IMAGE_PATH + 88 + ".png");
		Image image88 = imageIcon88.getImage();
		imageMap.put(88, image88);
		ImageIcon imageIcon99 = new ImageIcon(IMAGE_PATH + 99 + ".png");
		Image image99 = imageIcon99.getImage();
		imageMap.put(99, image99);
	}

	public TileView(int length, int type, int x, int y) {
		this.length = length;
		this.type = type;
		initialize();
	}

	public void setType(int type) {
		this.type = type;
		setImage();
	}

	private void initialize() {
		// Set the preferred size for the tile
		Dimension tileSize = new Dimension(length, length);
		this.setPreferredSize(tileSize);
		setImage();
	}
	
	private void setImage() {
		Image image = this.imageMap.get(type);
		// Scale the image to fit the label size
		Image scaledImage = image.getScaledInstance(length, length, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		// Set the scaled image icon
		this.setIcon(scaledIcon);
	}
}