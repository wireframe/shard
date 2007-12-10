package com.codecrate.shard.grid;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class GridApp extends JFrame {
	public static GridSquare start = null;
	public static GridSquare end = null;
	public static Path path;
	
	public GridApp(Grid grid) {
		setLayout(new BorderLayout());
		GridPanel gridPanel = new GridPanel(grid);
		TokenLabel tokenLabel = new TokenLabel(gridPanel, new Token());
		add(gridPanel, BorderLayout.CENTER);
		add(new ToolboxPanel(tokenLabel), BorderLayout.EAST);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(new Dimension(400, 400));
		setVisible(true);
		
		tokenLabel.place(grid.getSquare(Location.ORIGIN));
	}

	public static void main(String[] args) {
		new GridApp(new Grid(10, 10));
	}

	private static class ToolboxPanel extends JPanel {
		public ToolboxPanel(final TokenLabel tokenLabel) {
			add(new JLabel("Tools"));
		}
	}

	private static class GridPanel extends JPanel {
		private final Grid grid;

		public GridPanel(Grid grid) {
			this.grid = grid;
			setLayout(new GridLayout(grid.getHeight(), grid.getWidth()));
			for (int rowNum = 0; rowNum <  grid.getHeight(); rowNum++) {
				for (GridSquare square : grid.row(rowNum)) {
					add(new GridSquarePanel(this, square));
				}
			}
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

		public Grid getGrid() {
			return grid;
		}

		public void resetPanels() {
			for (GridSquarePanel panel : getGridSquarePanels()) {
				panel.updateSquareColor();
			}
		}
	}
	
	private static class TokenLabel extends JLabel implements MouseListener {
		private final Token token;
		private final GridPanel gridPanel;

		public TokenLabel(GridPanel gridPanel, Token token) {
			super(token.getIcon());
			this.gridPanel = gridPanel;
			this.token = token;
			
			addMouseListener(this);
		}
		
		public boolean canMove(Direction direction) {
			return token.canMove(direction);
		}

		private void place(GridSquare square) {
			token.place(square);
			gridPanel.findGridSquarePanel(square).add(this);
		}
		
		private void move(Direction direction) {
			GridSquarePanel previous = gridPanel.findGridSquarePanel(token.getGridSquare());
			previous.remove(this);
			previous.repaint();
			
			token.move(direction);
			GridSquarePanel newSquare = gridPanel.findGridSquarePanel(token.getGridSquare());
			newSquare.add(this);
			newSquare.repaint();
		}

		private void startPath(GridSquare gridSquare) {
			start = gridSquare;
		}

		private void endPath() {
			start = null;
			for (GridSquare square: GridApp.path.getGridSquares()) {
				Direction step = token.getGridSquare().directionTo(square);
				move(step);
			}
			gridPanel.resetPanels();
		}

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
			startPath(token.getGridSquare());
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			endPath();
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
			Color color = Color.WHITE;
			if (square.isBlocked()) {
				color = Color.BLACK;
			}
			
			setBackground(color);
		}

		private void toggle() {
			square.toggle();
			updateSquareColor();
		}

		private void highlight() {
			setBackground(Color.GREEN);
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println("clicked:" + square);
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			if (GridApp.start != null) {
				PathFinder pathFinder = new DirectPathFinder();
				GridApp.path = pathFinder.findPathBetween(gridPanel.getGrid(), GridApp.start, square);
				for (GridSquare gridSquare : path.getGridSquares()) {
					gridPanel.findGridSquarePanel(gridSquare).highlight();
				}
				GridApp.end = square;
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			gridPanel.resetPanels();
		}
		@Override
		public void mousePressed(MouseEvent e) {
			toggle();
		}
		@Override
		public void mouseReleased(MouseEvent e) {
		}
	}
}
