package edu.bayview.model;

import java.util.Random;

public class GridModel {
	private TileModel[][] tileModels;
	private int numOfTile;
	private int numOfMines;
	private int numOfCovered;
	private int numOfFlagged;
	private boolean gameOver = false;

	public GridModel(int numOfTile, int numOfMines) {
		initialize(numOfTile, numOfMines);
	}

	private void initialize(int numOfTile, int numOfMines) {
		this.numOfTile = numOfTile;
		this.numOfMines = numOfMines;
		this.numOfFlagged = numOfMines;
		this.numOfCovered = numOfTile * numOfTile;
		tileModels = new TileModel[numOfTile][numOfTile];
		for (int row = 0; row < numOfTile; row++) {
			for (int col = 0; col < numOfTile; col++) {
				tileModels[row][col] = new TileModel();
			}
		}
		setupMines();
	}

	public void restart() {
		this.gameOver = false;
		this.numOfFlagged = numOfMines;
		for (int row = 0; row < numOfTile; row++) {
			for (int col = 0; col < numOfTile; col++) {
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
		while (count < numOfMines) {
			int row = random.nextInt(numOfTile);
			int col = random.nextInt(numOfTile);
			if (!tileModels[row][col].isHasBomb()) {
				tileModels[row][col].setHasBomb(true);
				count++;
			}
		}
		calculateNumBombsAround();
	}

	private void calculateNumBombsAround() {
		for (int row = 0; row < numOfTile; row++) {
			for (int col = 0; col < numOfTile; col++) {
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

	private boolean isValidPosition(int row, int col) {
		return row >= 0 && row < numOfTile && col >= 0 && col < numOfTile;
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

	public int getNumOfCovered() {
		return this.numOfCovered;
	}

	public void decrementNumOfCovered() {
		this.numOfCovered--;
	}

	public int getNumOfFlagged() {
		return this.numOfFlagged;
	}

	public void setNumOfFlagged(int numOfFlagged) {
	 this.numOfFlagged = numOfFlagged;
	}
}