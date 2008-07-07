package com.codecrate.shard.grid;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.TreeSet;

/**
 * A path finder implementation that uses the AStar heuristic based algorithm
 * to determine a path. 
 * @see http://www.cokeandcode.com/pathfinding
 */
public class AStarPathFinder implements PathFinder {
	/** The set of nodes that have been searched through */
	private Collection<Node> closed = new ArrayList<Node>();
	/** The set of nodes that we do not yet consider fully searched */
	private TreeSet<Node> open = new TreeSet<Node>();
	
	private final Grid grid;
	/** The maximum depth of search we're willing to accept before giving up */
	private final int maxSearchDistance;
	
	/** The complete set of nodes across the map */
	private Node[][] nodes;
	/** The heuristic we're applying to determine which nodes to search first */
	private final ClosestHeuristic heuristic = new ClosestHeuristic();
	
	/**
	 * Create a path finder with the default heuristic - closest to target.
	 * 
	 * @param grid The map to be searched
	 * @param maxSearchDistance The maximum depth we'll search before giving up
	 */
	public AStarPathFinder(Grid grid, int maxSearchDistance) {
		this.grid = grid;
		this.maxSearchDistance = maxSearchDistance;

		nodes = new Node[grid.getWidth()][grid.getHeight()];
		for (int x = 0; x < grid.getWidth(); x++) {
			for (int y = 0; y < grid.getHeight(); y++) {
				nodes[x][y] = new Node(grid.getSquare(new Location(x, y)));
			}
		}
	}
	
	public Path findPathBetween(GridSquare startSquare, GridSquare endSquare) {
	  Location start = startSquare.getLocation();
	  Location end = endSquare.getLocation();
	  
		// initial state for A*. The closed group is empty. Only the starting
		// tile is in the open list and it since we're already there
		nodes[start.getX()][start.getY()].cost = 0;
		nodes[end.getX()][end.getY()].depth = 0;
		closed.clear();
		open.clear();
		open.add(nodes[start.getX()][start.getY()]);
		nodes[end.getX()][end.getY()].parent = null;
		
		// while we haven'n't exceeded our max search depth
		int maxDepth = 0;
		while (maxDepth < maxSearchDistance && !open.isEmpty()) {
			// pull out the first node in our open list, this is determined to 
			// be the most likely to be the next step based on our heuristic
			Node current = open.first();
			if (current == nodes[end.getX()][end.getY()]) {
				break;
			} 
			
			open.remove(current);
			closed.add(current);

			// search through all the neighbours of the current node evaluating
			// them as next steps
			for (GridSquare square : current.square.neighbors()) {
			  if (square.isBlocked()) {
			    continue;
			  }
				// the cost to get to this node is cost the current plus the movement
				// cost to reach this node. Note that the heursitic value is only used
				// in the sorted open list
				float nextStepCost = current.cost + square.getMovementCost();
				Node neighbour = nodes[square.getLocation().getX()][square.getLocation().getY()];
				
				// if the new cost we've determined for this node is lower than 
				// it has been previously makes sure the node hasn'e've
				// determined that there might have been a better path to get to
				// this node so it needs to be re-evaluated
				if (nextStepCost < neighbour.cost) {
					if (open.contains(neighbour)) {
						open.remove(neighbour);
					}
					if (closed.contains(neighbour)) {
						closed.remove(neighbour);
					}
				}
				
				// if the node hasn't already been processed and discarded then
				// reset it's cost to our current cost and add it as a next possible
				// step (i.e. to the open list)
				if (!open.contains(neighbour) && !(closed.contains(neighbour))) {
					neighbour.cost = nextStepCost;
					neighbour.heuristic = heuristic.getCost(square.getLocation().getX(), square.getLocation().getY(), end.getX(), end.getY());
					maxDepth = Math.max(maxDepth, neighbour.setParent(current));
					open.add(neighbour);
				}
			}
		}

		if (nodes[end.getX()][end.getY()].parent == null) {
			throw new PathNotFoundException(startSquare, endSquare);
		}
		
		Node target = nodes[end.getX()][end.getY()];
		Node previous = target;
		Path path = new Path(endSquare);
		do {
		  target = target.parent;
		  path.addStep(previous.square.towards(target.square));
		  previous = target;
		} while (target != nodes[start.getX()][start.getY()]); 

		return path.reverse();
	}
	
	/**
	 * A single node in the search graph
	 */
	private class Node implements Comparable {
	  private final GridSquare square;
	  
		/** The path cost for this node */
		private float cost;
		/** The parent of this node, how we reached it in the search */
		private Node parent;
		/** The heuristic cost of this node */
		private float heuristic;
		/** The search depth of this node */
		private int depth;
		
		/**
		 * Create a new node
		 */
		public Node(GridSquare square) {
		  this.square = square;
		}
		
		/**
		 * Set the parent of this node
		 * 
		 * @param parent The parent node which lead us to this node
		 * @return The depth we have no reached in searching
		 */
		public int setParent(Node parent) {
			depth = parent.depth + 1;
			this.parent = parent;
			
			return depth;
		}
		
		/**
		 * @see Comparable#compareTo(Object)
		 */
		public int compareTo(Object other) {
			Node o = (Node) other;
			
			float f = heuristic + cost;
			float of = o.heuristic + o.cost;
			
			if (f < of) {
				return -1;
			} else if (f > of) {
				return 1;
			} else {
				return 0;
			}
		}
	}
	
	
  public class ClosestHeuristic {
  	public float getCost(int x, int y, int tx, int ty) {		
  		float dx = tx - x;
  		float dy = ty - y;

  		float result = (float) (Math.sqrt((dx*dx)+(dy*dy)));

  		return result;
  	}

  }
}
