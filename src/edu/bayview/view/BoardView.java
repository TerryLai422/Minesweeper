package edu.bayview.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import edu.bayview.controller.GameController;
import edu.bayview.model.GridModel;

public class BoardView extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final String TITLE = "Minesweeper";
	private JLabel statusbar;

    public BoardView() {
    	initialize();
        GridModel gridModel = new GridModel(9, 9, 10);
        GridView gridView = new GridView(9, 40);
        this.add(gridView);
        GameController gameController = new GameController(gridModel, gridView, this);
    }

    private void initialize() {
        statusbar = new JLabel("Hello");
        this.add(statusbar, BorderLayout.SOUTH);

        this.add(new GridView(9, 40));
        this.setVisible(true);
        this.setResizable(false);
        this.pack();
        this.setTitle(TITLE);
        this.setLocationRelativeTo(null);

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(BoardView.this,
                        "Are you sure you want to quit?", "Stop playing?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                    System.exit(0);
                }
                else{
                    BoardView.this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });
    }
}