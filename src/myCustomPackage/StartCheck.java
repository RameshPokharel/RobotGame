package myCustomPackage;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.ArrayList;

public class StartCheck extends Applet implements KeyListener, Runnable {

	// private Robot robot;
	private Image image, currentSprite, character, character2, character3, characterDown, characterJumped, background,
			heliboy, heliboy2, heliboy3, heliboy4;
	private Graphics second;
	private URL base;
	static Background bg1, bg2;
	Robot r;
	public static HeliBoy hb, hb2;
	Animation ca, hca;
	public static Image tilegrassTop, tilegrassBot, tilegrassLeft, tilegrassRight, tiledirt, tileocean;
	private ArrayList<MyTile> tilearray = new ArrayList<MyTile>();

	@Override
	public void init() {

		setSize(800, 480);
		setBackground(Color.BLACK);
		setFocusable(true);
		addKeyListener(this);
		bg1 = new Background(0, 0);
		bg2 = new Background(2160, 0);
		hb = new HeliBoy(340, 360,400);
		hb2 = new HeliBoy(700, 360,500);
		r = new Robot();
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Game");
		try {
			base = getDocumentBase();
		} catch (Exception e) {
			// TODO: handle exception
		}

		// Image Setups
		character = getImage(base, "data/character.png");
		character2 = getImage(base, "data/character2.png");
		character3 = getImage(base, "data/character3.png");
		characterDown = getImage(base, "data/down.png");
		characterJumped = getImage(base, "data/jumped.png");
		currentSprite = character;
		background = getImage(base, "data/background.png");
		heliboy = getImage(base, "data/heliboy.png");
		heliboy2 = getImage(base, "data/heliboy2.png");
		heliboy3 = getImage(base, "data/heliboy3.png");
		heliboy4 = getImage(base, "data/heliboy4.png");
		tiledirt = getImage(base, "data/tiledirt.png");
		tilegrassTop = getImage(base, "data/tilegrasstop.png");
		tilegrassBot = getImage(base, "data/tilegrassbot.png");
		tilegrassLeft = getImage(base, "data/tilegrassleft.png");
		tilegrassRight = getImage(base, "data/tilegrassright.png");
		tileocean = getImage(base, "data/tileocean.png");

		ca = new Animation();
		hca = new Animation();
		ca.addFrame(character, 4000);
		ca.addFrame(character2, 150);
		ca.addFrame(character3, 150);
		hca.addFrame(heliboy, 150);
		hca.addFrame(heliboy2, 150);
		hca.addFrame(heliboy3, 150);
		hca.addFrame(heliboy4, 150);
	}

	@Override
	public void start() {

		for (int i = 0; i < 200; i++) {
			for (int j = 0; j < 12; j++) {

				if (j == 11) {
					MyTile t = new MyTile(i, j, 2);
					tilearray.add(t);

				}
				if (j == 10) {
					MyTile t = new MyTile(i, j, 1);
					tilearray.add(t);

				}
			}
		}
		Thread thread = new Thread(this);
		thread.start();
	}

	
	@Override
	public void stop() {
		// TODO Auto-generated method stub
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void run() {
		while (true) {
			bg1.update();
			bg2.update();
			r.update();
			animate();
			if (!r.isJumped() && !r.isDuck())
				currentSprite = ca.getImage();
			else if (r.isDuck())
				currentSprite = characterDown;
			else {
				currentSprite = characterJumped;
			}
			ArrayList projectiles = r.getProjectiles();
			for (int i = 0; i < projectiles.size(); i++) {
				Projectile p = (Projectile) projectiles.get(i);
				if (p.isVisible() == true) {
					p.update();
				} else {
					projectiles.remove(i);
				}
			}
			updateTiles();

			hb.update();
			hb2.update();
			repaint();
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void animate() {
		ca.update(30);
		hca.update(50);
	}

	@Override
	public void update(Graphics g) {
		if (image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			second = image.getGraphics();
		}

		second.setColor(getBackground());
		second.fillRect(0, 0, getWidth(), getHeight());
		second.setColor(getForeground());
		paint(second);

		g.drawImage(image, 0, 0, this);

	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(background, bg1.getBgX(), bg1.getBgY(), this);
		g.drawImage(background, bg2.getBgX(), bg2.getBgY(), this);
		paintTiles(g);
		ArrayList projectiles = r.getProjectiles();
		for (int i = 0; i < projectiles.size(); i++) {
			Projectile p = (Projectile) projectiles.get(i);
			g.setColor(Color.YELLOW);
			g.fillRect(p.getX(), p.getY(), 10, 5);
		}
		g.drawImage(currentSprite, r.getCenterx() - 61, r.getCentery() - 63, this);
		g.drawImage(hca.getImage(), hb.getCenterX() - 48, hb.getCenterY() - 48, this);
		g.drawImage(hca.getImage(), hb2.getCenterX() - 48, hb2.getCenterY() - 48, this);
	}

	@Override
	public void keyPressed(KeyEvent e) {

		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			if (r.isDuck() == false) {
				r.setJump(true);
				r.jump();
			}
			break;

		case KeyEvent.VK_DOWN:
			if (!r.isJumped()) {
				r.setDuck(true);
			}
			break;

		case KeyEvent.VK_LEFT:
			r.moveLeft();
			break;

		case KeyEvent.VK_RIGHT:
			r.moveRight();

			break;

		case KeyEvent.VK_SPACE:
			break;

		case KeyEvent.VK_CONTROL:
			if (r.isDuck() == false && r.isJumped() == false) {
				r.shoot();
			}
			break;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			break;

		case KeyEvent.VK_DOWN:
			r.setDuck(false);

			break;

		case KeyEvent.VK_LEFT:
			r.stop();
			break;

		case KeyEvent.VK_RIGHT:
			r.stop();
			bg1.stop();
			bg2.stop();

			break;

		case KeyEvent.VK_SPACE:
			break;

		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public static Background getBg1() {

		return bg1;
	}

	public static Background getBg2() {

		return bg2;
	}

	private void updateTiles() {

		for (int i = 0; i < tilearray.size(); i++) {
			MyTile t = (MyTile) tilearray.get(i);
			t.update();
		}
	}

	private void paintTiles(Graphics g) {
		for (int i = 0; i < tilearray.size(); i++) {
			MyTile t = (MyTile) tilearray.get(i);
			g.drawImage(t.getTileImage(), t.getTileX(), t.getTileY(), this);
		}
	}
}
