package edu.bayview.view;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.GridLayout;

public class GridView extends JPanel {
	private static final long serialVersionUID = 1L;
	private int numOfTile;
	private final int tileLength = 30;
	private TileView[][] tileViews;

	public GridView(int numOfTile) {
		initialize(numOfTile);
	}

	public void initialize(int numOfTile) {
		this.removeAll();
		this.numOfTile = numOfTile;
		this.tileViews = new TileView[numOfTile][numOfTile];
		this.setLayout(new GridLayout(numOfTile, numOfTile));
		set3DBorder();
		setupTileViews();
	}

	private void set3DBorder() {
		int borderWidth = 10; // Set the desired border width
		Border outerBorder = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, Color.DARK_GRAY, Color.GRAY);
		Border innerBorder = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
		Border compoundBorder = BorderFactory.createCompoundBorder(outerBorder, innerBorder);
		Border borderedBorder = BorderFactory.createCompoundBorder(compoundBorder,
				BorderFactory.createEmptyBorder(borderWidth, borderWidth, borderWidth, borderWidth));
		this.setBorder(borderedBorder);
	}

	private void setupTileViews() {
		for (int row = 0; row < numOfTile; row++) {
			for (int col = 0; col < numOfTile; col++) {
				TileView tileView = new TileView(tileLength, 99);
				this.tileViews[row][col] = tileView;
				this.add(tileView);
			}
		}
	}

	public int getNumOfTile() {
		return this.numOfTile;
	}
	
	public TileView getTileView(int row, int col) {
		return this.tileViews[row][col];
	}
	
	public TileView[][] getTileViews() {
		return this.tileViews;
	}
}