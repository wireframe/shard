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

/**
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class GridLocation {
	private static final TerrainCondition CONDITION_IMPASSIBLE = new TerrainCondition(false);
	private static final TerrainCondition CONDITION_DIFFICULT = new TerrainCondition(true);

	private GridKey key;
	private TerrainCondition terrainCondition;

	public GridKey getKey() {
		return key;
	}
	
	public boolean isAdjacent(GridLocation target) {
		key.getX();
		target.getKey().getX();
		
		return false;
	}
	
	public boolean isDiagonal(GridLocation target) {
		return false;
	}
	
	
	private static final class TerrainCondition {
		private boolean passable;
		
		public TerrainCondition(boolean passable) {
			this.passable = passable;
		}
		
		public boolean isPassable() {
			return passable;
		}
		
		public int getModifier() {
			return 0;
		}
	}
}
