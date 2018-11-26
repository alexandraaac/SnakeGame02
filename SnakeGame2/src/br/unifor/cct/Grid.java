package br.unifor.cct;

import java.awt.*;

public class Grid implements Drawable {

	private int width;
	private int height;

	public Grid(int width, int height) {
		this.width = width;
		this.height = height;
	}
 
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	@Override
	public void draw(Graphics2D g) {

		g.setColor(Color.BLACK);

		for (int i = 1; i <= 11; i++) {
			g.drawLine(50 * i, 50, 50 * i, 550);
			g.drawLine(50, 50 * i, 550, 50 * i);
		}

	}

}
