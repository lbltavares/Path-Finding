package com.sandbox.map;

import java.awt.Color;
import java.awt.Graphics2D;

public class Tile {

	public static final Tile TILE_GROUND = new Tile(new Color(90, 240, 90), false);
	public static final Tile TILE_WALL = new Tile(new Color(90, 90, 90), true);

	public boolean solid;
	public Color color;

	public Tile(Color color, boolean solid) {
		this.color = color;
		this.solid = solid;
	}

	public void render(Graphics2D g, int x, int y, int width, int height) {
		g.setColor(color);
		g.fillRect(x, y, width, height);
	}

}
