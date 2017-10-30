package myCustomPackage;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class AnimationImg extends Applet implements Runnable {

	Thread t;
	Image[] i;
	Boolean b = false;
	Image ci;
	String[] s = new String[] { "character.png", "character3.png" };
	MyAnim m;

	@Override
	public void init() {

		setBackground(Color.black);
		m = new MyAnim(this);
		setSize(400, 400);

		/*
		 * i= new Image[2];
		 * 
		 * for(int j=0;j<i.length;j++) {
		 * i[j]=getImage(getDocumentBase(),"data/"+s[j]); }
		 */

	}

	@Override
	public void start() {

		if (t == null) {
			t = new Thread(this);
		}
		t.start();
	}

	@Override
	public void stop() {
		if (t != null)
			t.stop();
		t = null;
	}

	@Override
	public void destroy() {
		super.destroy();
	}

	@Override
	public void run() {

		while (true) {
			// check();
			ci = m.getImg();
			repaint();
		}
	}

	private void check() {

		if (b == false) {
			ci = i[0];
			b = true;
			repaint();

			try {
				Thread.sleep(1300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			ci = i[1];
			b = false;
			repaint();
			try {
				Thread.sleep(150);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void paint(Graphics g) {

		g.drawImage(ci, 0, 200, this);
	}

}
