package edu.bayview.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;

import edu.bayview.controller.GameController;
import edu.bayview.model.GridModel;

public class BoardView extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final String TITLE = "Minesweeper";
    private JLabel numOfFlaggedLabel;
    private JLabel timerLabel;
    private GameController gameController;
    
    public BoardView() {
        // Create the menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu optionsMenu = new JMenu("Options");
        ButtonGroup gridSizeGroup = new ButtonGroup();
        JRadioButtonMenuItem gridSize9 = new JRadioButtonMenuItem("9 x 9");
        JRadioButtonMenuItem gridSize16 = new JRadioButtonMenuItem("16 x 16");
        JRadioButtonMenuItem gridSize24 = new JRadioButtonMenuItem("24 x 24");
        gridSize9.setSelected(true);

        gridSize9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setGridSize(9);
            }
        });

        gridSize16.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setGridSize(16);
            }
        });

        gridSize24.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setGridSize(24);
            }
        });

        gridSizeGroup.add(gridSize9);
        gridSizeGroup.add(gridSize16);
        gridSizeGroup.add(gridSize24);

        optionsMenu.add(gridSize9);
        optionsMenu.add(gridSize16);
        optionsMenu.add(gridSize24);
        menuBar.add(optionsMenu);
        setJMenuBar(menuBar);
        this.setLayout(new BorderLayout());
        
        GridModel gridModel = new GridModel(9, 10);
        GridView gridView = new GridView(9);
        
        // Create the bottom panel
        JPanel bottomPanel = new JPanel();
        numOfFlaggedLabel = new JLabel("#: " + gridModel.getNumOfFlagged());
        timerLabel = new JLabel("Timer: 0");
        bottomPanel.add(numOfFlaggedLabel);
        bottomPanel.add(timerLabel);
        this.add(bottomPanel, BorderLayout.SOUTH);
        this.add(gridView, BorderLayout.CENTER);
    	initialize();
        this.gameController = new GameController(gridModel, gridView, this);
    }
    private void setGridSize(int size) {
        // Update the grid size and reset the game
        // Replace with your code to handle grid size change
    	System.out.println("SIZE: " + size);
    	this.gameController.resize(size);
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