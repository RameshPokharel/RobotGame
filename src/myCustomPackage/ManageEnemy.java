package myCustomPackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

public class ManageEnemy {

	ArrayList<Enemy> em;
	HeliBoy h1, h2, h3;

	public ManageEnemy() {
		em = new ArrayList<>();
		h1 = new HeliBoy(540, 350, 200);
		h2 = new HeliBoy(700, 200, 200);
		h3 = new HeliBoy(700, 290, 200);

		em.add(h1);
		em.add(h2);
		em.add(h3);

	}

	public synchronized void update() {
		for (Enemy e : em) {
			e.update();
			ArrayList<enemyCheck> check = e.a;
			for (int i = 0; i < check.size(); i++) {
				enemyCheck ec = (enemyCheck) check.get(i);
				if (ec.isVisible()) {
					ec.update();
				} else {
					if(ec.getRect().getX()<0)
					
					check.remove(i);
				}
			}
		}

	}

	void paintItem(Graphics g, Image img, start s) {
		for (Enemy e : em) {

			g.drawImage(img, e.getCenterX() - 48, e.getCenterY() - 48, s);
			ArrayList<enemyCheck> check = e.a;
			for (int i = 0; i < check.size(); i++) {
				enemyCheck ec = (enemyCheck) check.get(i);
				if (ec.isVisible()) {
					g.setColor(Color.RED);
					g.fillRect(ec.getX(), ec.getY(), 5, 5);
				}
			}
		}
	}

	public void resetEnemy() {
		h1.clear(540, 360);
		h2.clear(700, 200);
		h3.clear(700, 290);

	}

	public ArrayList<Enemy> EnemyArray() {
		return em;
	}
}
