package com.humbertopinheiro.ui;

import java.awt.Cursor;

public class CursorIndicator {

	public Cursor getCursor(boolean hasPrevious, boolean hasNext,
			boolean mouseEnteredLeftSide, boolean mouseEnteredRightSide) {
		if (mouseEnteredLeftSide && hasPrevious) {
			return new Cursor(Cursor.HAND_CURSOR);
		} else if (mouseEnteredRightSide && hasNext) {
			return new Cursor(Cursor.HAND_CURSOR);
		} else {
			return new Cursor(Cursor.DEFAULT_CURSOR);
		}
	}
}
