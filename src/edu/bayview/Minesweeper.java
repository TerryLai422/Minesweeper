package edu.bayview;

import java.awt.EventQueue;

import edu.bayview.view.Window;

public class Minesweeper {

	public static void main(String[] args) {

		EventQueue.invokeLater(() -> {
			Window window = new Window();
			window.setVisible(true);
		});
	}
}
