package com.sandbox.entity;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public abstract class Entity {

	public static List<Entity> entities = new ArrayList<>();

	public int x, y;

	public Entity(int x, int y) {
		setXY(x, y);
	}

	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public abstract void update();

	public abstract void render(Graphics2D g, int w, int h);

	public abstract void onMouseClicked(MouseEvent e);

	public abstract void onKeyReleased(KeyEvent e);

	public abstract void onMapModified();

}
