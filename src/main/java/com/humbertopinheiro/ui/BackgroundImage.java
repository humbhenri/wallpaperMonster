package com.humbertopinheiro.ui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

public class BackgroundImage {

	private final ImageObserver imageObserver;
	private final Rectangle container;
	private final Image image;

	public BackgroundImage(ImageObserver imageObserver, Image image,
			Rectangle container) {
		this.imageObserver = imageObserver;
		this.container = container;
		this.image = image;
	}

	public void draw(Graphics g) {
		if (image != null) {
			int width = image.getWidth(imageObserver);
			int height = image.getHeight(imageObserver);
			g.drawImage(image, (container.width / 2) - (width / 2),
					(container.height / 2) - (height / 2), imageObserver);
		}
	}
}
