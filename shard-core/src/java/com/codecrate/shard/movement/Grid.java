package com.codecrate.shard.movement;
import java.util.ArrayList;
import java.util.List;

public class Grid {

	private GridLocation[][] locations;
	
	/**
	 * calculates distance from one point to another.
	 * ignores paths and uses most direct route.
	 * @param from
	 * @param to
	 * @return
	 */
	private int getDistance(GridLocation from, GridLocation to) {
		return 0;
	}
	
	private boolean isAdjacentTo(GridLocation from, GridLocation to) {
		return false;
	}
	
	private List getAdjacentLocations(GridLocation location) {
		return new ArrayList();
	}
}
