package com.sandbox.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.sandbox.map.Map;

public class TargetFinder extends Entity {

	public Color color;
	public boolean renderIntMap = false;

	private Target target;
	private List<Point> path;
	private Map map;
	private int[][] intMap;

	public TargetFinder(Target target, Map map, int x, int y) {
		super(x, y);
		this.map = map;
		this.target = target;
		color = new Color(40, 40, 200);
		path = new ArrayList<>();
		find();
	}

	public void find() {
		intMap = new int[map.width][map.height];
		for (int y = 0; y < map.height; y++) {
			for (int x = 0; x < map.width; x++) {
				if (map.getTile(x, y).solid)
					intMap[x][y] = -1;
				else
					intMap[x][y] = 0;
			}
		}
		List<Point> A = new ArrayList<>();
		Set<Point> B = new HashSet<>();
		A.add(new Point(target.x, target.y));
		intMap[target.x][target.y] = 1;
		for (int i = 0; i < A.size(); i++) {
			B.clear();
			Point p = A.get(i);
			if (intMap[p.x][p.y - 1] == 0) {
				intMap[p.x][p.y - 1] = intMap[p.x][p.y] + 1;
				B.add(new Point(p.x, p.y - 1));
			}
			if (intMap[p.x + 1][p.y] == 0) {
				intMap[p.x + 1][p.y] = intMap[p.x][p.y] + 1;
				B.add(new Point(p.x + 1, p.y));
			}
			if (intMap[p.x][p.y + 1] == 0) {
				intMap[p.x][p.y + 1] = intMap[p.x][p.y] + 1;
				B.add(new Point(p.x, p.y + 1));
			}
			if (intMap[p.x - 1][p.y] == 0) {
				intMap[p.x - 1][p.y] = intMap[p.x][p.y] + 1;
				B.add(new Point(p.x - 1, p.y));
			}
			A.addAll(B);
		}
		fillPath();
	}

	private void fillPath() {
		path.clear();
		if (intMap[x][y] == 0)
			return;
		Point p = new Point(x, y);
		while (p.x != target.x || p.y != target.y) {
			int menor = Integer.MAX_VALUE;
			Point temp = new Point(p.x, p.y);
			if (intMap[p.x][p.y - 1] < menor && intMap[p.x][p.y - 1] != -1) {
				menor = intMap[p.x][p.y - 1];
				temp.x = p.x;
				temp.y = p.y - 1;
			}
			if (intMap[p.x + 1][p.y] < menor && intMap[p.x + 1][p.y] != -1) {
				menor = intMap[p.x + 1][p.y];
				temp.x = p.x + 1;
				temp.y = p.y;
			}
			if (intMap[p.x][p.y + 1] < menor && intMap[p.x][p.y + 1] != -1) {
				menor = intMap[p.x][p.y + 1];
				temp.x = p.x;
				temp.y = p.y + 1;
			}
			if (intMap[p.x - 1][p.y] < menor && intMap[p.x - 1][p.y] != -1) {
				menor = intMap[p.x - 1][p.y];
				temp.x = p.x - 1;
				temp.y = p.y;
			}
			path.add(temp);
			p = temp;
		}
	}

	@Override
	public void update() {
	}

	@Override
	public void render(Graphics2D g, int w, int h) {
		g.setColor(color);
		g.fillOval(x * w, y * h, w, h);
		renderPath(g, w, h);
	}

	private void renderPath(Graphics2D g, int w, int h) {
		g.setColor(Color.yellow);
		int factor = w / 4;
		path.forEach(p -> {
			g.fillOval(p.x * w + factor, p.y * h + factor, w - 2 * factor, h - 2 * factor);
		});

		if (!renderIntMap)
			return;
		g.setColor(Color.black);
		for (int y = 0; y < map.height; y++) {
			for (int x = 0; x < map.width; x++) {
				g.drawString(intMap[x][y] + "", x * w + w / 4, y * h + h / 2);
			}
		}
	}

	@Override
	public void onMouseClicked(MouseEvent e) {
	}

	@Override
	public void onKeyReleased(KeyEvent e) {
	}

	@Override
	public void onMapModified() {
		find();
	}

}
