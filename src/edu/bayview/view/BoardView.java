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
    private Timer timer;
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

    public void updateFlagged(int flagged) {
    	numOfFlaggedLabel.setText("#:" + flagged);
    }
    
    private void initialize() {
        this.setVisible(true);
        this.setResizable(false);
        this.pack();
        this.setTitle(TITLE);
        this.setLocationRelativeTo(null);
        // Start the timer if it hasn't started yet
        if (timer == null) {
            timer = new Timer();
            timer.schedule(new TimerTask() {
                int timeElapsed = 0;

                @Override
                public void run() {
                    timeElapsed++;
                    timerLabel.setText("Timer: " + timeElapsed);
                }
            }, 0, 1000);
        }
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