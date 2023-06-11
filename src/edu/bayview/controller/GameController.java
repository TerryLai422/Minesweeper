package edu.bayview.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import edu.bayview.model.GridModel;
import edu.bayview.view.GridView;
import edu.bayview.view.TileView;

public class GameController {
    private GridModel gridModel;
    private GridView gridView;

    public GameController(GridModel gridModel, GridView gridView) {
        this.gridModel = gridModel;
        this.gridView = gridView;

        setupListeners();
    }

    private void setupListeners() {
        for (int row = 0; row < gridView.getNumOfTile(); row++) {
            for (int col = 0; col < gridView.getNumOfTile(); col++) {
                TileView tileView = gridView.getTileView(row, col);
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
        	System.out.println("Click - row:" + row + ",col:" + col);
        	System.out.println("hasBomb:" + gridModel.hasBomb(row, col));
        	System.out.println("Revealed:" + gridModel.isTileRevealed(row, col));
        	System.out.println("Flagged:" + gridModel.isTileFlagged(row, col));
        	System.out.println("numBombsAround:" + gridModel.getNumBombsAround(row, col));
            if (e.getButton() == MouseEvent.BUTTON1) {
                // Left button clicked
                revealTile(row, col);
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                // Right button clicked
                flagTile(row, col);
            }
        }

        private void revealTile(int row, int col) {
            if (!gridModel.isTileRevealed(row, col)) {
                gridModel.setTileRevealed(row, col, true);
                if (gridModel.hasBomb(row, col)) {
                    // Handle bomb revealed
                    // ...
                	gridModel.revealTile(row, col);
                	System.out.println("REVEALED");
                } else {
                    // Update neighboring tiles
                    // ...
                	System.out.println("ELSE REVEALED");
                }

                // Update the view
                gridView.updateTileView(row, col);
            }
        }

        private void flagTile(int row, int col) {
            if (!gridModel.isTileRevealed(row, col)) {
                boolean currentFlagStatus = gridModel.isTileFlagged(row, col);
                gridModel.setTileFlagged(row, col, !currentFlagStatus);

                // Update the view
                gridView.updateTileView(row, col);
            }
        }
    }
}