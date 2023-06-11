package edu.bayview.old;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import edu.bayview.view.TileView;

import javax.swing.border.BevelBorder;


import java.awt.Color;
import java.awt.GridLayout;
import java.util.Random;

public class GridView2 extends JPanel {

	private static final long serialVersionUID = 1L;
	private int size;
	private final int tileLength;

	public GridView2(int size, int tileLength) {
		this.size = size;
		this.tileLength = tileLength;
		initialize();
	}

	public void setSize(int size) {
		this.size = size;
	}

	private void initialize() {
		this.setLayout(new GridLayout(size, size));

		set3DBorder();
		
		addNormal();
//		addRandom();
	}
	
	private void set3DBorder() {
		 int borderWidth = 10; // Set the desired border width
	        
	        Border outerBorder = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, Color.DARK_GRAY, Color.GRAY);
	        Border innerBorder = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
	        
	        Border compoundBorder = BorderFactory.createCompoundBorder(outerBorder, innerBorder);
	        Border borderedBorder = BorderFactory.createCompoundBorder(compoundBorder, BorderFactory.createEmptyBorder(borderWidth, borderWidth, borderWidth, borderWidth));
	        this.setBorder(borderedBorder);

	}

	private void addNormal() {
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				this.add(new TileView(tileLength, 99, x, y));
			}
		}
	}

	private void addRandom() {
		Random random = new Random();
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				int type = random.nextInt(14);
				if (type == 9)
					type = 99;
				if (type == 10)
					type = 88;
				if (type == 11)
					type = 77;
				if (type == 12)
					type = 66;
				if (type == 13)
					type = 55;
				this.add(new TileView(tileLength, type, x, y));
			}
		}
	}
}