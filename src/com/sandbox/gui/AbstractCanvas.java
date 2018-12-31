package com.sandbox.gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

public abstract class AbstractCanvas extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	private boolean running = false;
	private long time;
	private int fps = 60;

	public AbstractCanvas(int width, int height) {
		Dimension d = new Dimension(width, height);
		setMinimumSize(d);
		setPreferredSize(d);
		setMaximumSize(d);
		setSize(d);
		setBackground(Color.black);
	}

	public abstract void update();

	public abstract void render(Graphics2D g);

	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		g.clearRect(0, 0, getWidth(), getHeight());
		render(g);
		g.dispose();
		bs.show();
	}

	@Override
	public void run() {
		while (running) {
			if (time + 1000.0 / fps > System.currentTimeMillis())
				continue;
			time = System.currentTimeMillis();
			update();
			render();
		}
	}

	public void start() {
		if (running)
			return;
		running = true;
		new Thread(this).start();
	}

	public void stop() {
		running = false;
	}
}
