package myCustomPackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import superEnemy.BulletManage;
import superEnemy.SuperEnemyClass;

public class ManageEnemy {

	public ArrayList<Enemy> em;
	HeliBoy h1, h2, h3;
	public static SuperEnemyClass s;
	ArrayList<BulletManage> ab = new ArrayList<>();

	public ManageEnemy() {
		em = new ArrayList<>();
		h1 = new HeliBoy(540, 350, 200);
		h2 = new HeliBoy(700, 200, 200);
		h3 = new HeliBoy(700, 290, 200);

		em.add(h1);
		em.add(h2);
		em.add(h3);
		s = new SuperEnemyClass(700, 20);

	}

	public synchronized void update() {
		s.Update();
		for (Enemy e : em) {
			if (!e.setCrash) {
				e.update();

				ArrayList<enemyCheck> check = e.a;
				for (int i = 0; i < check.size(); i++) {
					enemyCheck ec = (enemyCheck) check.get(i);
					if (ec.isVisible()) {
						ec.update();
					} else {
						if (ec.getRect().getX() < 0)

							check.remove(i);
					}
				}

			}
		}

		ab = s.getBulletArray();
		for (BulletManage b : ab) {
			b.update();
		}

	}

	void paintItem(Graphics g, Image img, start s) {

		for (Enemy e : em) {
			if (!e.setCrash) {
			/*
			 * if(s.getRobot().getCentery()>e.getCenterY()) { AffineTransform
			 * identity = new AffineTransform(); AffineTransform trans = new
			 * AffineTransform(); trans.setTransform(identity); trans.rotate(
			 * Math.toRadians(-10) ); Graphics2D g2d = (Graphics2D) g.create();
			 * g2d.drawImage(img, trans, s); } else
			 */
			g.drawImage(img, e.getCenterX() - 20, e.getCenterY() - 20,myStaticClass.heliBoyWidth,
					myStaticClass.heliBoyHeight,s);
			int ch = e.getCurrentHealth();
			//int x;
			Color c = Color.GREEN;
			/*if (ch > 40)
				x = 20;

			else if (ch <= 40 && ch > 30)
				x = 16;
			else if (ch <= 30 && ch > 20)
				x = 12;
			else if (ch <= 20 && ch > 10) {
				x = 8;
				c = Color.RED;
			} else if (ch <= 10 & ch > 5) {
				x = 4;
				c = Color.RED;
			} else {
				x = 0;
				c = Color.RED;
			}*/
			myStaticClass.enemyHealth(ch,c);
			g.setColor(myStaticClass.cc);
			g.drawRect(e.getCenterX() + 20, e.getCenterY(), 20, 5);
			g.setColor(myStaticClass.cc);

			g.fillRect(e.getCenterX() + 20, e.getCenterY(), myStaticClass.x, 5);
			ArrayList<enemyCheck> check = e.a;
			for (int i = 0; i < check.size(); i++) {
				enemyCheck ec = (enemyCheck) check.get(i);
				if (ec.isVisible()) {
					g.setColor(Color.RED);
					g.fillRect(ec.getX(), ec.getY(), 5, 5);
				}
			}
		}
		if (ab.size() > 0) {
			for (BulletManage b : ab) {
				g.drawImage(this.s.sanim.getCurrentBullet(), b.getCenterX(), b.getcenterY(), 50, 50, s);

			}
		}
		}
	}

	public void resetEnemy() {
		for(Enemy e:em)
		e.clear(e.initialX, e.initialY);
		
	}

	public ArrayList<Enemy> EnemyArray() {
		return em;
	}
}
