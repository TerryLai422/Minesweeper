package edu.bayview.old;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

/*
 * The class to create the window to display the game
 */
public class MWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JLabel statusbar;

    public MWindow() {
        initUI();
    }

    private void initUI() {
        statusbar = new JLabel("");
        add(statusbar, BorderLayout.SOUTH);

        add(new MBoard(statusbar));
        this.setVisible(true);
        setResizable(false);
        pack();

        setTitle("Minesweeper");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}