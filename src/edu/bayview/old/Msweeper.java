package edu.bayview.old;

import java.awt.EventQueue;

public class Msweeper {

	public static void main(String[] args) {

		EventQueue.invokeLater(() -> {
			MWindow window = new MWindow();
			window.setVisible(true);
		});
	}
}
