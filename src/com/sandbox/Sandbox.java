package com.sandbox;

import java.awt.EventQueue;

import javax.swing.JFrame;

import com.sandbox.gui.Sketch;

public class Sandbox {

	public static void main(String[] args) {
		System.setProperty("sun.java2d.opengl", "true");
		EventQueue.invokeLater(() -> {
			Sketch sketch = new Sketch(512, 512);
			JFrame frame = new JFrame("Sandbox");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().add(sketch);
			frame.pack();
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			sketch.start();
			sketch.requestFocus();
		});
	}
}
