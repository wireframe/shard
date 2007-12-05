package com.codecrate.shard.grid;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class GridApp extends JFrame {

	public GridApp(Grid grid) {
		add(new GridPanel(grid));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(new Dimension(400, 400));
		setVisible(true);
	}

	public static void main(String[] args) {
		new GridApp(new Grid(10, 10));
	}

	private static class GridPanel extends JPanel {
		public GridPanel(Grid grid) {
			setLayout(new GridLayout(grid.getHeight(), grid.getWidth()));
			for (int rowNum = 0; rowNum <  grid.getHeight(); rowNum++) {
				for (GridSquare square : grid.row(rowNum)) {
					add(new GridSquarePanel(square));
				}
			}
		}
	}
	
	private static class GridSquarePanel extends JPanel implements MouseListener {
		private final GridSquare square;

		public GridSquarePanel(GridSquare square) {
			this.square = square;
			setBorder(new LineBorder(Color.BLACK));

			updateSquareColor(square);

			JLabel label = new JLabel(square.toString());
			label.setForeground(new Color(0, 0, 250));
			add(label);
			
			addMouseListener(this);
		}
		private void updateSquareColor(GridSquare square) {
			Color color = Color.GRAY;
			
			if (square.getSequentialId() % 2 == 0) {
				color = Color.WHITE;
			}

			if (square.isBlocked()) {
				color = Color.BLACK;
			}

			setBackground(color);
		}

		private void toggle() {
			square.toggle();
			updateSquareColor(square);
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println("clicked:" + square);
		}
		@Override
		public void mouseEntered(MouseEvent e) {
		}
		@Override
		public void mouseExited(MouseEvent e) {
		}
		@Override
		public void mousePressed(MouseEvent e) {
			System.out.println("pressed:" + square);
			toggle();
		}
		@Override
		public void mouseReleased(MouseEvent e) {
		}
	}
}
