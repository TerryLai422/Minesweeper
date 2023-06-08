package edu.bayview.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.swing.BorderFactory;
import javax.swing.ImageIcon;


public class GridBoard extends JPanel {
	
	public GridBoard() {
		initialize();
	}
    public void initialize() {
        this.setLayout(new GridLayout(9,9));
        for (int i = 0; i < 81; i++) {
            final int tileNumber = i; // Capture the tile number for each label
            JLabel label = new JLabel();
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int x = tileNumber % 9; // Calculate the x position
                    int y = tileNumber / 9; // Calculate the y position

                    if (e.getButton() == MouseEvent.BUTTON1) {
                        System.out.println("Left button clicked on tile (" + x + ", " + y + ")");
                    } else if (e.getButton() == MouseEvent.BUTTON3) {
                        System.out.println("Right button clicked on tile (" + x + ", " + y + ")");
                    }
                }
            });

            // Set the preferred size for the tile
            Dimension tileSize = new Dimension(50, 50);
            label.setPreferredSize(tileSize);
			ImageIcon imageIcon = new ImageIcon("src/resources/0.png");

			Image image = imageIcon.getImage();

			// Scale the image to fit the label size
			Image scaledImage = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			ImageIcon scaledIcon = new ImageIcon(scaledImage);

			// Set the scaled image icon
			label.setIcon(scaledIcon);
            // Create and set the border for the tile
            Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
            label.setBorder(border);

            add(label);
        }
    }
}


