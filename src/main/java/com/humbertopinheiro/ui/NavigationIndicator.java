package com.humbertopinheiro.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

public class NavigationIndicator {
	
	private static final double SIDE_WIDTH_PERC = 0.1;
	
	private Rectangle container;

	public NavigationIndicator(Rectangle container) {
		this.container = container;
	}

	public void draw(Graphics2D g, boolean isLoading, boolean shouldPaindLeft, boolean shouldPaintRight) {
		if (isLoading) {
			return;
		}
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		if (shouldPaindLeft) {
			paintSide(g, getLeftSide(), "<");
		}
		if (shouldPaintRight) {
			paintSide(g, getRightSide(), ">");
		}
	}
	
	private Rectangle getLeftSide() {
		return new Rectangle(0, 0, (int) (container.width * SIDE_WIDTH_PERC),
				container.height);
	}

	private Rectangle getRightSide() {
		return new Rectangle((int) (container.width * (1.0 - SIDE_WIDTH_PERC)), 0,
				(int) (container.width * SIDE_WIDTH_PERC), container.height);
	}
	
	private void paintSide(Graphics2D g, Rectangle rectangle, String text) {
		g.setColor(new Color(0, 0, 0, 0.5f));
		g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Dialog", Font.BOLD, 48));
		FontMetrics fm = g.getFontMetrics();
		int totalWidth = (fm.stringWidth(text) * 2) + 4;
		int x = rectangle.x + (rectangle.width - totalWidth) / 2;
		int y = (rectangle.height - fm.getHeight()) / 2;
		g.drawString(text, x, y + ((fm.getDescent() + fm.getAscent()) / 2));
	}
	
}
