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
	public static TokenLabel currentToken = null;
	public static Path path;
	public static boolean error;
	
	public GridApp(Grid grid) {
		setLayout(new BorderLayout());
		GridPanel gridPanel = new GridPanel(grid);
		TokenLabel tokenLabel = new TokenLabel(gridPanel, new Token());
		add(gridPanel, BorderLayout.CENTER);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(new Dimension(400, 400));
		setVisible(true);
		
		tokenLabel.place(grid.getSquare(Location.ORIGIN));
	}

	public static void main(String[] args) {
		new GridApp(new Grid(10, 10));
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
			
			if (GridApp.path != null) {
				for (GridSquare square : GridApp.path.getGridSquares()) {
					findGridSquarePanel(square).highlight();
				}
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

		private void place(GridSquare square) {
			token.place(square);
			gridPanel.findGridSquarePanel(square).add(this);
		}

		private GridSquare getGridSquare() {
			return token.getGridSquare();
		}

		private boolean canMove(Path path) {
			return token.canMove(path);
		}

		private void startPath(GridSquare gridSquare) {
			GridApp.currentToken = this;
		}

		private void endPath() {
			if (GridApp.path != null && token.canMove(GridApp.path)) {
				gridPanel.findGridSquarePanel(token.getGridSquare()).remove(this);
				token.move(GridApp.path);
				gridPanel.findGridSquarePanel(token.getGridSquare()).add(this);
			}

			GridApp.currentToken = null;
			GridApp.path = null;
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
		private PathFinder pathFinder = new DirectPathFinder();
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
			repaint();
		}

		private void toggle() {
			square.toggle();
			updateSquareColor();
		}

		private void highlight() {
			Color color = Color.GREEN;
			if (GridApp.error) {
				color = Color.RED;
			}
			setBackground(color);
		}

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			if (GridApp.currentToken != null) {
				GridApp.path = pathFinder.findPathBetween(gridPanel.getGrid(), GridApp.currentToken.getGridSquare(), square);
				GridApp.error = !GridApp.currentToken.canMove(path);
				gridPanel.resetPanels();
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
