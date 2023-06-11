package edu.bayview;

import java.awt.EventQueue;

import edu.bayview.view.BoardView;

public class Minesweeper {

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			BoardView window = new BoardView();
			window.setVisible(true);
		});
	}
}
