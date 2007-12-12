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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class GridApp extends JFrame {
	public static TokenLabel currentToken = null;

	public GridApp(Grid grid) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setSize(new Dimension(400, 400));

		GridPanel gridPanel = new GridPanel(grid);
		add(gridPanel, BorderLayout.CENTER);
		add(new ToolBoxPanel(gridPanel), BorderLayout.EAST);
		
		setVisible(true);
	}

	public static void main(String[] args) {
		new GridApp(new Grid(10, 10));
	}

	private static class ToolBoxPanel extends JPanel {
		public ToolBoxPanel(final GridPanel gridPanel) {
			final GridSquarePanel start = gridPanel.findGridSquarePanel(gridPanel.getGrid().getSquare(Location.ORIGIN));
			JButton addToken = new JButton("New Token");
			addToken.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					TokenLabel tokenLabel = new TokenLabel(gridPanel, new Token());
					tokenLabel.place(start);
				}});
			add(addToken);
		}
	}
	
	private static class GridPanel extends JPanel {
		private final Grid grid;
		private Path path;

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
			throw new IllegalArgumentException("Unable to find grid square panel for: " + gridSquare);
		}

		private Collection<GridSquarePanel> getGridSquarePanels() {
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

		public void highlightPath(Path path) {
			if (this.path != null) {
				unhighlightPath();
			}

			boolean error = !GridApp.currentToken.canMove(path);
			for (GridSquare square : path.getGridSquares()) {
				findGridSquarePanel(square).highlight((error ? Color.red : Color.green));
			}
			this.path = path;
		}

		private void unhighlightPath() {
			for (GridSquare square : this.path.getGridSquares()) {
				findGridSquarePanel(square).unhighlight();
			}
		}
	}
	
	private static class GridSquarePanel extends JPanel implements MouseListener {
		private PathFinder pathFinder = new DirectPathFinder();
		private final GridSquare square;
		private final GridPanel gridPanel;
		private Color highlight;

		public GridSquarePanel(GridPanel gridPanel, GridSquare square) {
			this.gridPanel = gridPanel;
			this.square = square;
			setBorder(new LineBorder(Color.BLACK));
			setSize(50, 50);

			addMouseListener(this);
		}

		public void unhighlight() {
			this.highlight = null;
			repaint();
		}

		public GridSquare getSquare() {
			return square;
		}
		
		@Override
		public void repaint() {
			Color color = Color.WHITE;
			if (highlight != null) {
				color = highlight;
			} else if (square != null && square.isBlocked()) {
				color = Color.BLACK;
			}
			
			setBackground(color);
			super.repaint();
		}

		private void toggle() {
			square.toggle();
			repaint();
		}

		private void highlight(Color color) {
			this.highlight = color;
			repaint();
		}

		public void addToken(TokenLabel tokenLabel) {
			add(tokenLabel);
			validate();
			repaint();
		}
		
		public void removeToken(TokenLabel tokenLabel) {
			remove(tokenLabel);
			repaint();
		}

		@Override
		public void mouseClicked(MouseEvent e) {
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			if (GridApp.currentToken != null) {
				Path path = pathFinder.findPathBetween(gridPanel.getGrid(), GridApp.currentToken.getGridSquare(), square);
				gridPanel.highlightPath(path);
			}
		}
		@Override
		public void mouseExited(MouseEvent e) {
		}
		@Override
		public void mousePressed(MouseEvent e) {
			toggle();
		}
		@Override
		public void mouseReleased(MouseEvent e) {
		}
	}
	
	private static class TokenLabel extends JLabel implements MouseListener {
		private final Token token;
		private final GridPanel gridPanel;
		private GridSquarePanel gridSquarePanel;

		public TokenLabel(GridPanel gridPanel, Token token) {
			super(token.getIcon());
			this.gridPanel = gridPanel;
			this.token = token;
			
			addMouseListener(this);
		}

		private void place(GridSquarePanel panel) {
			token.place(panel.getSquare()); 
			this.gridSquarePanel = panel;
			panel.addToken(this);
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
			if (GridApp.currentToken != null && token.canMove(gridPanel.path)) {
				gridSquarePanel.removeToken(this);
				gridSquarePanel = gridPanel.findGridSquarePanel(token.move(gridPanel.path));
				gridSquarePanel.addToken(this);
			}
			gridPanel.unhighlightPath();
			GridApp.currentToken = null;
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
}
