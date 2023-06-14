package edu.bayview.view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;

import edu.bayview.controller.GameController;

public class BoardView extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final String TITLE = "Minesweeper";
	private static final String NUMBER_IMAGE_PATH = "src/resources/number/";
	private static Map<Integer, Image> numberImageMap = new HashMap<>();

	private JLabel numOfFlaggedLabel;
	private JLabel timerLabel;
	private JPanel timerPanel;
	private JPanel minePanel;
	private GameController gameController;
	private GridView gridView;

	static {
		for (int i = 0; i < 9; i++) {
			ImageIcon imageIcon = new ImageIcon(NUMBER_IMAGE_PATH + i + ".png");
			Image image = imageIcon.getImage();
			numberImageMap.put(i, image);
		}
	}

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

		this.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				if (JOptionPane.showConfirmDialog(BoardView.this, "Are you sure you want to quit?", "Stop playing?",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					System.exit(0);
				} else {
					BoardView.this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				}
			}
		});

		this.setLayout(new BorderLayout());
		initialize(9, 10);

	}

	private void setGridSize(int size) {
		// Update the grid size and reset the game
		// Replace with your code to handle grid size change
		System.out.println("SIZE: " + size);
		this.gameController.resize(size);
	}

	public JLabel getTimerLabel() {
		return this.timerLabel;
	}

	public void setNumOfFlagged(String numOfFlagged) {
		this.numOfFlaggedLabel.setText(numOfFlagged);
	}

	public void initialize(int size, int numOfMines) {
		gridView = new GridView(size);

		// Create the bottom panel
		JPanel bottomPanel = new JPanel();
		timerPanel = new JPanel();
		timerPanel.setLayout(new GridBagLayout());
		minePanel = new JPanel();
		minePanel.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		// Load the images
		Image image0 = numberImageMap.get(0);
		Image scaledImage0 = image0.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon0 = new ImageIcon(scaledImage0);
		// Create JLabels for each image and set the images as their icons
		JLabel label01 = new JLabel();
		label01.setIcon(scaledIcon0);

		JLabel label02 = new JLabel();
		label02.setIcon(scaledIcon0);

		JLabel label03 = new JLabel();
		label03.setIcon(scaledIcon0);

		Image image1 = numberImageMap.get(1);
		Image scaledImage1 = image1.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon1 = new ImageIcon(scaledImage1);
		// Create JLabels for each image and set the images as their icons
		JLabel label11 = new JLabel();
		label11.setIcon(scaledIcon1);

		JLabel label12 = new JLabel();
		label12.setIcon(scaledIcon1);

		JLabel label13 = new JLabel();
		label13.setIcon(scaledIcon1);
		// Add the labels to the panel
		timerPanel.add(label01);
		timerPanel.add(label02);
		timerPanel.add(label03);
		
		minePanel.add(label11);
		minePanel.add(label12);
		
		numOfFlaggedLabel = new JLabel();
		timerLabel = new JLabel("Timer: 0");

		// Align the left label to the left
		constraints.gridx = 0;
		constraints.anchor = GridBagConstraints.WEST;
		bottomPanel.add(minePanel, constraints);

		// Align the right label to the right
		constraints.gridx = 2;
		constraints.anchor = GridBagConstraints.EAST;
		bottomPanel.add(timerPanel, constraints);

		// Align the center label to the center
//		constraints.gridx = 1;
//		constraints.anchor = GridBagConstraints.CENTER;
//		bottomPanel.add(timerPanel, constraints);

//        bottomPanel.add(numOfFlaggedLabel);
//        bottomPanel.add(timerPanel);
		this.add(bottomPanel, BorderLayout.SOUTH);
		this.add(gridView, BorderLayout.CENTER);
		this.gameController = new GameController(size, numOfMines, gridView, this);

		this.setTitle(TITLE);
		this.setVisible(true);
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);

	}
}