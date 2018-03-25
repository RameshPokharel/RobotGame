package RobotPackage;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;

import myCustomPackage.Animation;

public class MyRobot extends Applet implements Runnable, KeyListener {

	Boolean b = false;
	int limit = 200000, chk;
	int x, xx, stat = 0, xPos = 3;

	Thread t;
	Image image,bombWar;
	Graphics myg;
	Image character1, character2, character3, character4, character5, currentImg, fire;
	//Image[] characters;
	URL url;
	RobotAnimation anim;
	ArrayList<bulletClas> a;
	ArrayList<Image> imageArray = new ArrayList<>();

	@Override
	public void init() {
		setSize(1000, 500);
		url = getDocumentBase();
		addKeyListener(this);
		bombWar = getImage(url, "data/bombaWarr.png");
		character1 = getImage(url, "data/robot/character1.png");
		character2 = getImage(url, "data/robot/character2.png");
		character3 = getImage(url, "data/robot/character3.png");
		character4 = getImage(url, "data/robot/character4.png");
		character5 = getImage(url, "data/robot/character5.png");
		fire = getImage(url, "data/bullet/fireright.png");
		anim = new RobotAnimation();
		anim.addFrame(character3);
		anim.addFrame(character4);
		anim.addFrame(character1);
		anim.addFrame(character5);
		anim.addFrame(character1);
		anim.addFrame(character2);
	}

	@Override
	public void start() {
		currentImg = character1;
		t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		while (true) {
			/*
			 * currentImg = anim.getImage(); x += 1; if (x > limit) { xPos +=
			 * 10; chk += 1; if (chk > 6) { a.add(new bulletClas(xPos, 100));
			 * chk = 0; }
			 * 
			 * for (bulletClas b : a) { b.update(); } x = 0;
			 * 
			 * } anim.update(this);
			 * 
			 */
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			anim.update();
			currentImg=anim.getImage();
			if(currentImg==null)
			{
				currentImg=character1;
			}
			repaint();

		}
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(currentImg, anim.getX(), 100, this);
		g.drawImage(bombWar, 300, 300, this);
		/*
		 * for (bulletClas b : a) { // g.fillRect(b.getCenterX(), b.getCenterY()
		 * + 10, 10, 5); g.drawImage(fire, b.getCenterX(), b.getCenterY(),
		 * this);
		 * 
		 * }
		 */
	}

	@Override
	public void update(Graphics g) {

		if (image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			myg = image.getGraphics();
		}

		myg.setColor(getBackground());
		myg.fillRect(0, 0, getWidth(), getHeight());
		myg.setColor(getForeground());
		paint(myg);

		g.drawImage(image, 0, 0, this);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_RIGHT: {
			System.out.println("true ");
			anim.setMove(true,1);
			anim.setReadyToFire(false);
		}
		case KeyEvent.VK_CONTROL: {
			anim.setReadyToFire(true);
			System.out.println("control");
		}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_RIGHT:
			anim.setMove(false,1);
		case KeyEvent.VK_CONTROL: {
			anim.setReadyToFire(false);
		}
		}

	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

	public boolean value() {
		return b;
	}
}
