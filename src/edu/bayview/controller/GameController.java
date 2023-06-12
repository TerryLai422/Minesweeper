package edu.bayview.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import edu.bayview.model.GridModel;
import edu.bayview.model.TileModel;
import edu.bayview.view.BoardView;
import edu.bayview.view.GridView;
import edu.bayview.view.TileView;

public class GameController {
	private GridModel gridModel;
	private GridView gridView;
	private BoardView boardView;

	public GameController(GridModel gridModel, GridView gridView, BoardView boardView) {
		this.gridModel = gridModel;
		this.gridView = gridView;
		this.boardView = boardView;
		setupListeners();
	}

	private void setupListeners() {
		for (int row = 0; row < this.gridView.getNumOfTile(); row++) {
			for (int col = 0; col < this.gridView.getNumOfTile(); col++) {
				TileView tileView = this.gridView.getTileView(row, col);
				tileView.addMouseListener(new TileClickListener(row, col));
			}
		}
	}

	private class TileClickListener extends MouseAdapter {
		private int row;
		private int col;

		public TileClickListener(int row, int col) {
			this.row = row;
			this.col = col;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (!gridModel.isGameOver()) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					// Left button clicked
					if (!gridModel.isTileFlagged(row, col)) {
						revealTile(row, col);
					}
				} else if (e.getButton() == MouseEvent.BUTTON3) {
					// Right button clicked
					flagTile(row, col);
				}
				updateTileViews();
			} else {
				System.out.println("GAME OVER");
				if (JOptionPane.showConfirmDialog(boardView, "Do you want to restart the game?", "Restart Game?",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					System.out.println("Restart Game");
					gridModel.restart();
					updateTileViews();
				} else {
					boardView.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				}
			}
		}

		private void revealTile(int row, int col) {
			System.out.println("revealTile");
			if (!gridModel.isTileRevealed(row, col)) {
				gridModel.setTileRevealed(row, col, true);
				gridModel.decrementNumOfCovered();
				if (gridModel.hasBomb(row, col)) {
					// Handle bomb revealed
					gridModel.setGameOver(true);
				} else {
					// Update neighboring tiles
					revealNeighborTiles(row, col);
				}
			}
		}

		private void revealNeighborTiles(int row, int col) {
			int minRow, minCol, maxRow, maxCol;

			minRow = (row <= 0 ? 0 : row - 1);
			minCol = (col <= 0 ? 0 : col - 1);
			maxRow = (row >= gridView.getNumOfTile() - 1 ? gridView.getNumOfTile() : row + 2);
			maxCol = (col >= gridView.getNumOfTile() - 1 ? gridView.getNumOfTile() : col + 2);

			// Loop over all surrounding cells
			for (int i = minRow; i < maxRow; i++) {
				for (int j = minCol; j < maxCol; j++) {
					if (!gridModel.hasBomb(i, j) && !gridModel.isTileRevealed(i, j)) {
						if (!gridModel.isTileFlagged(i, j)) {
							gridModel.setTileRevealed(i, j, true);
							gridModel.decrementNumOfCovered();
							if (gridModel.getNumBombsAround(i, j) == 0) {
								// Call ourself recursively
								revealNeighborTiles(i, j);
							}
						}
					}
				}
			}
		}

		private void flagTile(int row, int col) {
			if (!gridModel.isTileRevealed(row, col)) {
				boolean currentFlagStatus = gridModel.isTileFlagged(row, col);
				gridModel.setNumOfFlagged(gridModel.getNumOfFlagged() + (currentFlagStatus ? 1 : -1));
				gridModel.setTileFlagged(row, col, !currentFlagStatus);
			}
		}

		private void updateTileViews() {
			TileView[][] tileViews = gridView.getTileViews();
			int numOfTile = gridView.getNumOfTile();

			for (int row = 0; row < numOfTile; row++) {
				for (int col = 0; col < numOfTile; col++) {
					TileModel tileModel = gridModel.getTileModel(row, col);
					TileView tileView = tileViews[row][col];

					if (gridModel.isGameOver()) {
						if (tileModel.isRevealed()) {
							if (tileModel.isHasBomb()) {
								tileView.setType(55);
							} else {
								tileView.setType(tileModel.getNumBombsAround());
							}
						} else if (tileModel.isHasBomb()) {
							tileView.setType(77);
						}
					} else {
						if (tileModel.isRevealed()) {
							tileView.setType(tileModel.getNumBombsAround());
						} else if (tileModel.isFlagged()) {
							// Set flagged tile image
							tileView.setType(88);
						} else {
							// Set default tile image
							tileView.setType(99);
						}
					}
				}
			}
			System.out.println("#covered" + gridModel.getNumOfCovered());
			System.out.println("#flagged" + gridModel.getNumOfFlagged());
			boardView.updateFlagged(gridModel.getNumOfFlagged());
			boardView.repaint();
			gridView.repaint();
			gridView.revalidate();
		}
	}
}