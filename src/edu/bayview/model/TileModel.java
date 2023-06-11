package edu.bayview.model;

/*
 * The underlying model for each tile
 */
public class TileModel {
	private boolean revealed = false;
	private boolean hasBomb = false;
	private boolean flagged = false;
	private int numBombsAround = 0;

	public boolean isRevealed() {
		return revealed;
	}

	public void setRevealed(boolean revealed) {
		this.revealed = revealed;
	}

	public boolean isHasBomb() {
		return hasBomb;
	}

	public void setHasBomb(boolean hasBomb) {
		this.hasBomb = hasBomb;
	}

	public boolean isFlagged() {
		return flagged;
	}

	public void setFlagged(boolean flagged) {
		this.flagged = flagged;
	}

	public int getNumBombsAround() {
		return numBombsAround;
	}

	public void setNumBombsAround(int numBombsAround) {
		this.numBombsAround = numBombsAround;
	}

	@Override
	public String toString() {
		return "" + (this.hasBomb ? "1" : "0") + "-" + (Integer.valueOf(this.numBombsAround).toString()) + "-"
				+ (this.revealed ? "1" : "0") + "-" + (this.flagged ? "1" : "0");
	}
}