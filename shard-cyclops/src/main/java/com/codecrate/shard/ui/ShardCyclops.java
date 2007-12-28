package com.codecrate.shard.ui;

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

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ToolTipManager;
import javax.swing.border.LineBorder;

import com.codecrate.shard.grid.DirectPathFinder;
import com.codecrate.shard.grid.Grid;
import com.codecrate.shard.grid.GridSquare;
import com.codecrate.shard.grid.Location;
import com.codecrate.shard.grid.Path;
import com.codecrate.shard.grid.PathFinder;
import com.codecrate.shard.grid.Token;

public class ShardCyclops extends JFrame {

	public ShardCyclops(Grid grid) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setSize(new Dimension(600, 600));

		GridPanel gridPanel = new GridPanel(grid);
		gridPanel.setPreferredSize(new Dimension(800, 800));

		JScrollPane pane = new JScrollPane(gridPanel);
	    pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	    pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	  
		add(pane, BorderLayout.CENTER);
		add(new ToolBoxPanel(gridPanel), BorderLayout.EAST);
		
		setVisible(true);
	}

	public static void main(String[] args) {
		new ShardCyclops(new Grid(10, 10));
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

		public void highlightPath(Path path, boolean error) {
			if (this.path != null) {
				unhighlightPath();
			}
			for (GridSquare square : path.getGridSquares()) {
				findGridSquarePanel(square).highlight((error ? Color.red : Color.green));
			}
			this.path = path;
			
			
			GridSquarePanel gridSquare = findGridSquarePanel(path.getDestination());
			gridSquare.setToolTipText(path.getLength() + " Feet");
			//@see http://binkley.blogspot.com/2006/06/programmatically-show-tool-tip-in.html
	        ToolTipManager.sharedInstance().mouseMoved(
	                new MouseEvent(gridSquare, 0, 0, 0,
	                        0, 0, // X-Y of the mouse for the tool tip
	                        0, false));
		}

		private void unhighlightPath() {
			for (GridSquare square : this.path.getGridSquares()) {
				GridSquarePanel gridSquare = findGridSquarePanel(square);
				gridSquare.unhighlight();
				gridSquare.setToolTipText(null);
			}
		}
	}
	
	private static class GridSquarePanel extends JPanel {
		private static GridMovementListener LISTENER = GridMovementListener.NO_OP_LISTENER;

		private final GridSquare square;

		public GridSquarePanel(final GridPanel gridPanel, final GridSquare square) {
			this.square = square;
			setBorder(new LineBorder(Color.BLACK));

			addMouseListener(new MouseListener() {
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
					toggle();
					GridSquarePanel.setListener(new GridMovementListener() {
						@Override
						public void onSquareEnter(GridSquare square) {
							gridPanel.findGridSquarePanel(square).toggle();
						}});
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					GridSquarePanel.setListener(GridMovementListener.NO_OP_LISTENER);
				}
			});

			addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent e) {
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					LISTENER.onSquareEnter(square);
				}
				@Override
				public void mouseExited(MouseEvent e) {
				}
				@Override
				public void mousePressed(MouseEvent e) {
				}
				@Override
				public void mouseReleased(MouseEvent e) {
				}
			});

			updateSquareColor();
		}
		
		public static void setListener(GridMovementListener listener) {
			LISTENER = listener;
		}

		public void unhighlight() {
			updateSquareColor();
			repaint();
		}

		private void updateSquareColor() {
			if (square.isBlocked()) {
				setBackground(Color.BLACK);
			} else {
				setBackground(Color.WHITE);
			}
		}

		public GridSquare getSquare() {
			return square;
		}
		
		private void toggle() {
			square.toggle();
			updateSquareColor();
			repaint();
		}

		private void highlight(Color color) {
			setBackground(color);
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
	}
	
	private static class TokenLabel extends JLabel {
		private static final PathFinder pathFinder = new DirectPathFinder();

		private final Token token;
		private final GridPanel gridPanel;
		private GridSquarePanel gridSquarePanel;

		public TokenLabel(GridPanel gridPanel, final Token token) {
			super(token.getIcon());
			this.gridPanel = gridPanel;
			this.token = token;
			
			addMouseListener(new MouseListener() {
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
			});
		}

		private void place(GridSquarePanel panel) {
			token.place(panel.getSquare()); 
			this.gridSquarePanel = panel;
			panel.addToken(this);
		}

		private void startPath(GridSquare gridSquare) {
			GridSquarePanel.setListener(new GridMovementListener() {
				@Override
				public void onSquareEnter(GridSquare square) {
					Path path = pathFinder.findPathBetween(gridPanel.getGrid(), token.getGridSquare(), square);
					boolean error = !token.canMove(path);
					gridPanel.highlightPath(path, error);
				}
			});
		}

		private void endPath() {
			if (token.canMove(gridPanel.path)) {
				gridSquarePanel.removeToken(this);
				gridSquarePanel = gridPanel.findGridSquarePanel(token.move(gridPanel.path));
				gridSquarePanel.addToken(this);
			}
			gridPanel.unhighlightPath();
			GridSquarePanel.setListener(GridMovementListener.NO_OP_LISTENER);
		}
	}
}
