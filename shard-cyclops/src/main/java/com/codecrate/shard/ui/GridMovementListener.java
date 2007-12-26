package com.codecrate.shard.ui;

import com.codecrate.shard.grid.GridSquare;

public interface GridMovementListener {
	public static final GridMovementListener NO_OP_LISTENER = new GridMovementListener() {
		@Override
		public void onSquareEnter(GridSquare square) {
		}
	};

	void onSquareEnter(GridSquare square);
}
