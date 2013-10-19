package com.humbertopinheiro.ui;

import static java.awt.event.MouseEvent.BUTTON3;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class RightClickMenu {

	private final Component component;

	public RightClickMenu(Component component) {
		this.component = component;
	}

	public void show(MouseEvent e, ActionListener action) {
		if (e.getButton() == BUTTON3) {
			JPopupMenu menu = new JPopupMenu();
			JMenuItem menuItem = new JMenuItem("Save as wallpaper");
			menuItem.addActionListener(action);
			menu.add(menuItem);
			menu.show(component, e.getX(), e.getY());
		}
	}
}
