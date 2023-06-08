package edu.bayview.model;

/*
 * The underlying model for each grid
 */
public class Grid {
    private boolean revealed;
    private boolean hasBomb;
    private boolean flagged;
    private int numBombsAround;
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
    
}
