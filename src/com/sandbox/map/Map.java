package com.sandbox.map;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Scanner;

import com.sandbox.entity.Entity;

public class Map {

	public int width, height;

	private Tile[][] tiles;

	public Map(int width, int height) {
		this.width = width;
		this.height = height;
		tiles = new Tile[width][height];
	}

	public void setLine(int y, String line) {
		Scanner sc = new Scanner(line);
		for (int x = 0; sc.hasNextInt(); x++) {
			if (x >= width) {
				sc.close();
				return;
			}
			int n = sc.nextInt();
			if (n == 1)
				setTile(x, y, Tile.TILE_GROUND);
			else if (n == 2)
				setTile(x, y, Tile.TILE_WALL);
		}
		sc.close();
	}

	public void setTile(int x, int y, Tile tile) {
		if (tiles[x][y] == tile)
			return;
		tiles[x][y] = tile;
		Entity.entities.forEach(en -> en.onMapModified());
	}

	public Tile getTile(int x, int y) {
		if (x >= 0 && x < width && y >= 0 && y < height)
			return tiles[x][y];
		return null;
	}

	public void render(Graphics2D g, int canvasWidth, int canvasHeight) {
		int tileWidth = canvasWidth / width;
		int tileHeight = canvasHeight / height;
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (tiles[x][y] != null)
					tiles[x][y].render(g, x * tileWidth, y * tileHeight, tileWidth, tileHeight);
				g.setColor(Color.black);
				g.drawLine(x * tileWidth, 0, x * tileWidth, height * tileHeight);
			}
			g.drawLine(0, y * tileHeight, width * tileWidth, y * tileHeight);
		}
	}

}
