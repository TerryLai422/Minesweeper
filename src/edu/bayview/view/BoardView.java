package edu.bayview.view;

import java.awt.BorderLayout;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import edu.bayview.controller.GameController;
import edu.bayview.model.GridModel;

public class BoardView extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final String TITLE = "Minesweeper";
    private JLabel numOfFlaggedLabel;
    private JLabel timerLabel;

    public BoardView() {
        GridModel gridModel = new GridModel(9, 10);
        GridView gridView = new GridView(9);
        this.setLayout(new BorderLayout());
        this.add(gridView, BorderLayout.CENTER);

        // Create the bottom panel
        JPanel bottomPanel = new JPanel();
        numOfFlaggedLabel = new JLabel("#: " + gridModel.getNumOfFlagged());
        timerLabel = new JLabel("Timer: 0");
        bottomPanel.add(numOfFlaggedLabel);
        bottomPanel.add(timerLabel);
        this.add(bottomPanel, BorderLayout.SOUTH);
    	initialize();
        new GameController(gridModel, gridView, this);
    }

    public JLabel getFlaggedLabel() {
    	return this.numOfFlaggedLabel;
    }
    
    public JLabel getTimerLabel() {
    	return this.timerLabel;
    }
    private void initialize() {
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