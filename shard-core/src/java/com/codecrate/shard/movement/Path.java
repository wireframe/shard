package com.codecrate.shard.movement;

import java.util.ArrayList;
import java.util.List;

public class Path {
	private List locations = new ArrayList();

	public boolean isContinuous() {
		if (!hasMoreThanOneLocation()) {
			return true;
		}
		
		GridLocation previousLocation = getFirstLocation();
		for (int x = 1; x < locations.size(); x++) {
			GridLocation nextLocation = (GridLocation) locations.get(x);
			
			if (!previousLocation.isAdjacent(nextLocation)) {
				return false;
			}

			previousLocation = nextLocation;
		}
		
		return true;
	}
	
	public GridLocation getFirstLocation() {
		if (!hasLocations()) {
			return null;
		}
		return (GridLocation) locations.get(0);
	}
	
	public GridLocation getLastLocation() {
		if (!hasLocations()) {
			return null;
		}
		return (GridLocation) locations.get(locations.size() - 1);
	}
	
	public int getDistance() {
		if (!hasMoreThanOneLocation()) {
			return 0;
		}
		
		int distance = 0;
		int diagonalMovements = 0;
		GridLocation previousLocation = getFirstLocation();
		for (int x = 1; x < locations.size(); x++) {
			GridLocation nextLocation = (GridLocation) locations.get(x);
			previousLocation.isAdjacent(nextLocation);
			if (!previousLocation.isDiagonal(nextLocation)) {
				distance += 5;
			} else {
				distance += 5 * (2 % diagonalMovements); 
				diagonalMovements++;
			}
			
			previousLocation = nextLocation;
		}
		return distance;
	}
	
	private boolean hasLocations() {
		return 0 < locations.size();
	}
	
	private boolean hasMoreThanOneLocation() {
		return 1 <  locations.size();
	}
}
