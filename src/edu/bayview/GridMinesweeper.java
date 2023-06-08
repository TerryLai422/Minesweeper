package edu.bayview;

import java.awt.EventQueue;

import edu.bayview.view.GridWindow;
import edu.bayview.view.Window;

public class GridMinesweeper {

	public static void main(String[] args) {

		EventQueue.invokeLater(() -> {
			GridWindow window = new GridWindow();
			window.setVisible(true);
		});
	}
}
