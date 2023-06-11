package edu.bayview.model;

import java.util.Random;

public class GridModel {
	private TileModel[][] tileModels;
	private int rows;
	private int columns;
	private int numMines;
	private boolean gameOver = false;

	public GridModel(int rows, int columns, int numMines) {
		this.rows = rows;
		this.columns = columns;
		this.numMines = numMines;
		tileModels = new TileModel[rows][columns];
		initialize();
	}

	private void initialize() {
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < columns; col++) {
				tileModels[row][col] = new TileModel();
			}
		}
		setupMines();
	}

	public void restart() {
		this.gameOver = false;
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < columns; col++) {
				tileModels[row][col].setRevealed(false);
				tileModels[row][col].setFlagged(false);
				tileModels[row][col].setHasBomb(false);
				tileModels[row][col].setNumBombsAround(0);
			}
		}
		setupMines();
	}
	private void setupMines() {
		Random random = new Random();
		int count = 0;
		while (count < numMines) {
			int row = random.nextInt(rows);
			int col = random.nextInt(columns);
			if (!tileModels[row][col].isHasBomb()) {
				tileModels[row][col].setHasBomb(true);
				count++;
			}
		}
		calculateNumBombsAround();
	}

	private void calculateNumBombsAround() {
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < columns; col++) {
				tileModels[row][col].setNumBombsAround(countBombsAround(row, col));
			}
		}
	}

	private int countBombsAround(int row, int col) {
		int count = 0;
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				int newRow = row + i;
				int newCol = col + j;
				if (isValidPosition(newRow, newCol) && tileModels[newRow][newCol].isHasBomb()) {
					count++;
				}
			}
		}
		return count;
	}

	public void revealTile(int row, int col) {
    	System.out.println("REVEAL TILES- row:" + row + "-col:" + col);
		if (isValidPosition(row, col)) {
			TileModel tile = tileModels[row][col];
			if (!tile.isRevealed()) {
				tile.setRevealed(true);
				if (tile.getNumBombsAround() == 0 && !tile.isHasBomb()) {
					// If the tile has no bombs around it, recursively reveal neighboring tiles
					revealNeighboringTiles(row, col);
				}
			}
		}
	}

	private void revealNeighboringTiles(int row, int col) {
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				int newRow = row + i;
				int newCol = col + j;
				System.out.println("newRow:" + newRow + "-newCol:" + newCol);
				if (isValidPosition(newRow, newCol)) {
					revealTile(newRow, newCol);
				}
			}
		}
	}

	private boolean isValidPosition(int row, int col) {
		return row >= 0 && row < rows && col >= 0 && col < columns;
	}

	public boolean hasBomb(int row, int col) {
		return this.tileModels[row][col].isHasBomb();
	}

	public boolean isTileRevealed(int row, int col) {
		return this.tileModels[row][col].isRevealed();
	}

	public void setTileRevealed(int row, int col, boolean value) {
		this.tileModels[row][col].setRevealed(value);
	}

	public boolean isTileFlagged(int row, int col) {
		return this.tileModels[row][col].isFlagged();
	}

	public void setTileFlagged(int row, int col, boolean value) {
		this.tileModels[row][col].setFlagged(value);
	}

	public int getNumBombsAround(int row, int col) {
		return this.tileModels[row][col].getNumBombsAround();
	}

	public TileModel getTileModel(int row, int col) {
		return this.tileModels[row][col];
	}

	public TileModel[][] getTileModels() {
		return tileModels;
	}
	public boolean isGameOver() {
		return this.gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
}