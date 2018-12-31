package com.sandbox.gui;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

import com.sandbox.MapEditor;
import com.sandbox.entity.Entity;
import com.sandbox.entity.Target;
import com.sandbox.entity.TargetFinder;
import com.sandbox.map.Map;

public class Sketch extends AbstractCanvas {
	private static final long serialVersionUID = 1L;

	private Map map;
	private MapEditor editor;

	public Sketch(int width, int height) {
		super(width, height);

		map = new Map(16, 16);
		// 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15
		map.setLine(0, " 2  2  2  2  2  2  2  2  2  2  2  2  2  2  2  2");
		map.setLine(1, " 2  1  1  1  1  1  1  1  2  1  1  1  1  1  1  2");
		map.setLine(2, " 2  1  1  1  1  1  1  1  2  1  1  1  1  1  1  2");
		map.setLine(3, " 2  1  1  1  1  1  1  1  2  1  1  1  1  1  1  2");
		map.setLine(4, " 2  1  1  1  1  1  1  1  2  1  1  1  1  1  1  2");
		map.setLine(5, " 2  1  1  1  1  1  1  1  2  1  1  1  1  1  1  2");
		map.setLine(6, " 2  1  1  1  1  1  1  1  2  1  1  1  1  1  1  2");
		map.setLine(7, " 2  1  1  1  1  1  1  1  2  1  1  1  1  1  1  2");
		map.setLine(8, " 2  1  2  2  2  2  2  2  2  1  1  1  1  1  1  2");
		map.setLine(9, " 2  1  1  1  1  1  1  1  2  1  1  1  1  1  1  2");
		map.setLine(10, "2  1  1  1  1  1  1  1  2  1  1  1  1  1  1  2");
		map.setLine(11, "2  1  1  1  1  1  1  1  2  1  1  1  1  1  1  2");
		map.setLine(12, "2  1  1  1  1  1  1  1  2  1  1  1  1  1  1  2");
		map.setLine(13, "2  1  1  1  1  1  1  2  2  1  1  1  1  1  1  2");
		map.setLine(14, "2  1  1  1  1  1  1  1  1  1  1  1  1  1  1  2");
		map.setLine(15, "2  2  2  2  2  2  2  2  2  2  2  2  2  2  2  2");

		editor = new MapEditor(map, this);
		addMouseListener(editor);
		addMouseMotionListener(editor);
		addKeyListener(editor);

		Entity.entities = new ArrayList<Entity>();
		Target target = new Target(5, 5);
		Entity.entities.add(target);
		Entity.entities.add(new TargetFinder(target, map, 12, 3));

	}

	@Override
	public void update() {
		Entity.entities.forEach(Entity::update);
	}

	@Override
	public void render(Graphics2D g) {
		map.render(g, getWidth(), getHeight());
		editor.render(g);

		Iterator<Entity> i = Entity.entities.iterator();
		while (i.hasNext()) {
			i.next().render(g, getWidth() / map.width, getHeight() / map.height);
		}
	}
}
