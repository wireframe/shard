/*
 * Copyright 2004 codecrate consulting
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.codecrate.shard.movement;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
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
