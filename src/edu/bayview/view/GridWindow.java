package edu.bayview.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class GridWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JLabel statusbar;

    public GridWindow() {
    	initialize();
    }

    private void initialize() {
        statusbar = new JLabel("");
        add(statusbar, BorderLayout.SOUTH);

        add(new GridBoard());
        this.setVisible(true);
        setResizable(false);
        pack();

        setTitle("Minesweeper");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
