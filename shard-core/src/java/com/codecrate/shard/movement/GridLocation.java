package com.codecrate.shard.movement;

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
