package myCustomPackage;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

public class CustomAnim extends Applet implements Runnable {

	Thread t;
	Image i1, i2;
	URL url;
	Graphics g1;
	Image first;
	Animation a1, a2;

	@Override
	public void init() {
		setSize(300, 400);
		t = new Thread(this);
		t.setName("Thread 1");
		url = getDocumentBase();
		i1 = getImage(url, "data/character.png");
		i2 = getImage(url, "data/character3.png");

		a1 = new Animation();
	}

	@Override
	public void start() {
		t.start();
		a1.addFrame(i1, 4000);
		a1.addFrame(i2, 50);
	}

	@Override
	public void run() {

		while (true) {
			a1.update(1);
			repaint();
		}
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(a1.getImage(), 0, 0, this);
	}

	@Override
	public void update(Graphics g) {
		if (first == null) {
			first = createImage(this.getHeight(), this.getWidth());
			g1 = first.getGraphics();
		}
		g1.setColor(getBackground());
		g1.fillRect(0, 0, getHeight(), getWidth());
		g1.setColor(getForeground());
		paint(g1);

		g.drawImage(first, 0, 0, this);
	}

}
