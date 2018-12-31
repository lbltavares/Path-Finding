package com.sandbox.entity;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Target extends Entity {

	public Color color;

	public Target(int x, int y) {
		super(x, y);
		color = new Color(200, 40, 40);
	}

	@Override
	public void update() {

	}

	@Override
	public void render(Graphics2D g, int w, int h) {
		g.setColor(color);
		g.setStroke(new BasicStroke(4));
		g.drawOval(x * w + 4, y * h + 4, w - 8, h - 8);
	}

	@Override
	public void onMouseClicked(MouseEvent e) {
	}

	@Override
	public void onKeyReleased(KeyEvent e) {
	}

	@Override
	public void onMapModified() {
	}

}
