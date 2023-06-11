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
				System.out.println("Click - row:" + row + ",col:" + col);
				System.out.println("Before Tile:" + gridModel.getTileModel(row, col).toString());
				if (e.getButton() == MouseEvent.BUTTON1) {
					// Left button clicked
					if (!gridModel.isTileFlagged(row, col)) {
						revealTile(row, col);
					} else {
						System.out.println("row[" + row + "],col[" + col + "] is flagged");
					}
				} else if (e.getButton() == MouseEvent.BUTTON3) {
					// Right button clicked
					flagTile(row, col);
				}
				updateTileViews();

				System.out.println("After Tile:" + gridModel.getTileModel(row, col).toString());
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
		        if (gridModel.hasBomb(row, col)) {
		            // Handle bomb revealed
		            gridModel.setGameOver(true);
		            System.out.println("GAME OVER!");
		        } else {
		            // Update neighboring tiles
//		            revealNeighborTiles(row, col);
		        }
		    }
		}

		private void revealNeighborTiles(int row, int col) {
		    for (int i = Math.max(row - 1, 0); i <= Math.min(row + 1, gridView.getNumOfTile() - 1); i++) {
		        for (int j = Math.max(col - 1, 0); j <= Math.min(col + 1, gridView.getNumOfTile() - 1); j++) {
		            if (i != row || j != col) {
		                if (!gridModel.hasBomb(i, j) && !gridModel.isTileRevealed(i, j)) {
		                    revealTile(i, j);
		                }
		            }
		        }
		    }
		}
		
		private boolean isValidTile(int row, int col) {
		    int numOfTile = gridView.getNumOfTile();
		    return row >= 0 && row < numOfTile && col >= 0 && col < numOfTile;
		}


		private void flagTile(int row, int col) {
			System.out.println("flatTile");
			if (!gridModel.isTileRevealed(row, col)) {
				boolean currentFlagStatus = gridModel.isTileFlagged(row, col);
				gridModel.setTileFlagged(row, col, !currentFlagStatus);
			}
		}

		private void updateTileViews() {
			System.out.println("update tile views");

			TileView[][] tileViews = gridView.getTileViews();
			int numOfTile = gridView.getNumOfTile();
			for (int row = 0; row < numOfTile; row++) {
				for (int col = 0; col < numOfTile; col++) {
					System.out.print(gridModel.getTileModels()[row][col].toString() + "|");
				}
				System.out.println("");
			}
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
			gridView.repaint();
			gridView.revalidate();
		}
	}
}