package com.sandbox;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.sandbox.entity.Entity;
import com.sandbox.map.Map;
import com.sandbox.map.Tile;

public class MapEditor implements MouseListener, MouseMotionListener, KeyListener {

	private Map map;
	private Canvas canvas;
	private Point mouse;

	private Tile[] tiles = { Tile.TILE_GROUND, Tile.TILE_WALL };
	private int selectedTile;

	public MapEditor(Map map, Canvas canvas) {
		this.map = map;
		this.canvas = canvas;
		mouse = new Point(0, 0);
		selectedTile = 0;
	}

	public void render(Graphics2D g) {
		Point m = mouseToMap();
		int tileWidth = canvas.getWidth() / map.width;
		int tileHeight = canvas.getHeight() / map.height;
		tiles[selectedTile].render(g, tileWidth * m.x, tileHeight * m.y, tileWidth, tileHeight);
		g.setColor(Color.WHITE);
		g.drawRect(tileWidth * m.x, tileHeight * m.y, tileWidth, tileHeight);
	}

	private Point mouseToMap() {
		int tileWidth = canvas.getWidth() / map.width;
		int tileHeight = canvas.getHeight() / map.height;
		return new Point(mouse.x / tileWidth, mouse.y / tileHeight);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
			selectedTile++;
			selectedTile %= tiles.length;
		}
		Entity.entities.forEach(en -> en.onKeyReleased(e));
	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouse.x = e.getX();
		mouse.y = e.getY();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			Point m = mouseToMap();
			map.setTile(m.x, m.y, tiles[selectedTile]);
		}
		Entity.entities.forEach(en -> en.onMouseClicked(e));
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
