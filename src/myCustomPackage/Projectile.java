package myCustomPackage;

import java.awt.Rectangle;
import java.util.ArrayList;

public class Projectile {

	private int x, y, speedX;
	private Boolean visible;

	public static Rectangle r;

	public Projectile(int startX, int startY) {
		x = startX;
		y = startY;
		speedX = 7;
		visible = true;
		r = new Rectangle(0, 0, 0, 0);

	}

	public void update() {
		x += speedX;
		if (x > 800) {
			visible = false;
		}
		r.setBounds(x, y, 15, 15);
		if (x < 800) {
			checkCollision();
		}

	}

	private void checkCollision() {

		ArrayList<Enemy> a = start.me.em;
		for (Enemy e : a) {
			if (r.intersects(e.r)) {
				visible = false;
				if (e.health > 0) {
					e.health -= 10;
				}
				if (e.health == 0) {
					start.score += 5;
				}

			}
		}
		}
	

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getSpeedX() {
		return speedX;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public static Rectangle getRect()
	{
		return r;
	}

	
}
