package com.codecrate.shard.grid;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class GridApp extends JFrame {
	public GridApp(Grid grid) {
		setLayout(new BorderLayout());
		GridPanel gridPanel = new GridPanel(grid);
		add(gridPanel, BorderLayout.CENTER);
		add(new ToolboxPanel(gridPanel), BorderLayout.EAST);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(new Dimension(400, 400));
		setVisible(true);
	}

	public static void main(String[] args) {
		new GridApp(new Grid(10, 10));
	}

	private static class ToolboxPanel extends JPanel {
		public ToolboxPanel(final GridPanel gridPanel) {
			JButton down = new JButton("Down");
			down.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					gridPanel.moveToken(Direction.DOWN);
				}});
			add(down);
		}
	}

	private static class GridPanel extends JPanel {
		private JLabel token;
		private GridSquare tokenSquare;

		public GridPanel(Grid grid) {
			setLayout(new GridLayout(grid.getHeight(), grid.getWidth()));
			for (int rowNum = 0; rowNum <  grid.getHeight(); rowNum++) {
				for (GridSquare square : grid.row(rowNum)) {
					add(new GridSquarePanel(this, square));
				}
			}
			
			token = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("images/token.png")));
			tokenSquare = grid.getSquare(new Location(0,0));
			findGridSquarePanel(tokenSquare).add(token);
		}

		public void moveToken(Direction direction) {
			findGridSquarePanel(tokenSquare).remove(token);
			
			tokenSquare = tokenSquare.move(direction);
			findGridSquarePanel(tokenSquare).add(token);
			repaint();
		}

		public GridSquarePanel findGridSquarePanel(GridSquare gridSquare) {
			for (GridSquarePanel panel : getGridSquarePanels()) {
				if (panel.getSquare().equals(gridSquare)) {
					return panel;
				}
			}
			throw new IllegalArgumentException("Unalbe to find grid square panel for: " + gridSquare);
		}

		public Collection<GridSquarePanel> getGridSquarePanels() {
			Collection<GridSquarePanel> results = new ArrayList<GridSquarePanel>();
			for (Component sibling : getComponents()) {
				GridSquarePanel panel = (GridSquarePanel) sibling;
				results.add(panel);
			}
			return results;
		}
	}
	
	private static class GridSquarePanel extends JPanel implements MouseListener {
		private final GridSquare square;
		private final GridPanel gridPanel;

		public GridSquarePanel(GridPanel gridPanel, GridSquare square) {
			this.gridPanel = gridPanel;
			this.square = square;
			setBorder(new LineBorder(Color.BLACK));

			updateSquareColor();
			addMouseListener(this);
		}

		public GridSquare getSquare() {
			return square;
		}

		private void updateSquareColor() {
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
			updateSquareColor();
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println("clicked:" + square);
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			for (GridSquare gridSquare : square.neighbors()) {
				gridPanel.findGridSquarePanel(gridSquare).highlight();
			}
		}
		private void highlight() {
			setBackground(Color.GREEN);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			for (GridSquarePanel panel : gridPanel.getGridSquarePanels()) {
				panel.updateSquareColor();
			}
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
