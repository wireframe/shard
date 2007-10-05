package com.codecrate.shard.grid;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class GridApp extends JFrame {

	public GridApp(int x, int y) {
		LayoutManager layout = new GridLayout(x, y);
		
		JPanel squaresPanel = new JPanel();
		squaresPanel.setLayout(layout);
		for (int i = x - 1; i >= 0; i--) {
			for (int j = 0; j <= y; j++) {
				JLabel square = new JLabel("r" + i + ":c" + j);
				square.setForeground(new Color(0, 0, 250));
				

				JPanel squarePanel = new JPanel();
				squarePanel.add(square);
				squarePanel.setBackground(getColor(i, j));
				squarePanel.setBorder(new LineBorder(Color.BLACK));
				squarePanel.setToolTipText("Row " + i + " and Column " + j);
				squaresPanel.add(squarePanel);
			}
		}

		add(squaresPanel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(new Dimension(400, 400));
		setVisible(true);
	}

	private Color getColor(int x, int y) {
		if ((x + y) % 2 == 0) {
			return Color.WHITE;
		} 
		return Color.GRAY;
	}

	public static void main(String[] args) {
		new GridApp(10, 10);
	}
}
