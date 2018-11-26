package br.unifor.cct;

import java.awt.*;

public class Square implements Drawable {

	private static final int GRID_SCALE = 50;
	private int x;
	private int y;
	private int size;
	private Color color;

	public Square(int size, Color color) {
		this.size = size;
		this.color = color;
		this.x = 1;
		this.y = 1; 
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(color);
		g.fillRect((y * GRID_SCALE) + 1, (x * GRID_SCALE) + 1, size - 1, size - 1);
	}

}
