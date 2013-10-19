package com.humbertopinheiro.ui;

import java.awt.Component;
import java.awt.Rectangle;

import com.humbertopinheiro.base.Side;

public class Bounds {

	private static final double SIDE_WIDTH_PERC = 0.1;

	private final Component container;

	private int mouseX;

	private int mouseY;

	public Bounds(Component container) {
		this.container = container;
	}

	public void setMouse(int x, int y) {
		mouseX = x;
		mouseY = y;
	}

	public boolean insideLeftSide() {
		return getLeftSide().contains(mouseX, mouseY);
	}

	public boolean insideRightSide() {
		return getRightSide().contains(mouseX, mouseY);
	}

	private Rectangle getLeftSide() {
		return new Rectangle(0, 0,
				(int) (container.getWidth() * SIDE_WIDTH_PERC),
				container.getHeight());
	}

	private Rectangle getRightSide() {
		return new Rectangle(
				(int) (container.getWidth() * (1.0 - SIDE_WIDTH_PERC)), 0,
				(int) (container.getWidth() * SIDE_WIDTH_PERC),
				container.getHeight());
	}

	public Side getSideClicked() {
		if (insideLeftSide()) {
			return Side.LEFT;
		} else if (insideRightSide()) {
			return Side.RIGHT;
		}
		return Side.NONE;
	}
}
