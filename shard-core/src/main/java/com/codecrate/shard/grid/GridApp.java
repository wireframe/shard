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

	public GridApp(Grid grid) {
		LayoutManager layout = new GridLayout(grid.getHeight(), grid.getWidth());
		
		JPanel squaresPanel = new JPanel();
		squaresPanel.setLayout(layout);
		for (int y = 0; y <  grid.getHeight(); y++) {
			for (int x = 0; x < grid.getWidth(); x++) {
				GridSquare square = grid.getSquare(x, y);
				JLabel label = new JLabel(square.toString());
				label.setForeground(new Color(0, 0, 250));

				JPanel squarePanel = new JPanel();
				squarePanel.add(label);
				squarePanel.setBackground(getColor(y, x));
				squarePanel.setBorder(new LineBorder(Color.BLACK));
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
		new GridApp(new Grid(10, 10));
	}
}
