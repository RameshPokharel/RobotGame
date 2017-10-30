package myCustomPackage;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;


public class start extends Applet implements KeyListener, Runnable {

	private Image image, currentSprite, character, character2, character3, characterDown, characterJumped, background,
			heliboy, heliboy2, heliboy3, heliboy4;
	private Graphics second;
	private URL base;
	static Background bg1, bg2;
	static Robot r;
	public static ManageEnemy me;
	Animation ca, hca;
	public static Image tilegrassTop, tilegrassBot, tilegrassLeft, tilegrassRight, tiledirt, tileocean;
	private ArrayList<MyTile> tilearray = new ArrayList<MyTile>();
	Thread thread;
	public static int score = 0;
	private Font font = new Font(null, Font.BOLD, 30);

	enum GameState {
		Running, Dead
	}

	GameState state = GameState.Running;

	@Override
	public void init() {

		setSize(800, 480);
		setBackground(Color.BLACK);
		setFocusable(true);
		addKeyListener(this);
		bg1 = new Background(0, 0);
		bg2 = new Background(2160, 0);
		r = new Robot();
		me = new ManageEnemy();
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Game");
		try {
			base = getDocumentBase();
		} catch (Exception e) {
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

		try {
			MapFileRead();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	private void MapFileRead() throws FileNotFoundException {

		BufferedReader br = new BufferedReader(new FileReader("data/map.txt"));
		int width = 0;
		int height = 0;
		ArrayList<String> lines = new ArrayList<>();

		while (true) {
			try {
				String line = br.readLine();
				if (line == null) {
					br.close();
					break;
				}
				width = Math.max(width, line.length());
				lines.add(line);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		height = lines.size();
		String ln;
		for (int j = 0; j < height; j++) {
			ln = lines.get(j);
			for (int i = 0; i < width; i++) {
				if (i < ln.length()) {

					tilearray.add(new MyTile(i, j, Character.getNumericValue(ln.charAt(i))));

				}
			}
		}
	}

	@Override
	public void start() {

		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void stop() {
	}

	@Override
	public void destroy() {
	}

	@Override
	public void run() {
		if (state == GameState.Running) {

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
				System.out.println("height: " + currentSprite.getHeight(this));
				System.out.println("width: " + currentSprite.getWidth(this));
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
				me.update();

				repaint();
				try {
					Thread.sleep(17);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (r.getCentery() > 500) {
					state = GameState.Dead;
				}
			}

		} else
			repaint();
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
		if (state == GameState.Running) {

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

			me.paintItem(g, hca.getImage(), this);
			g.setFont(font);
			g.setColor(Color.WHITE);
			g.drawString(Integer.toString(score), 740, 30);

		} else if (state == GameState.Dead) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 800, 480);
			g.setColor(Color.WHITE);
			g.drawString("Dead", 360, 240);
			g.drawString("Press 'Enter' to restart The game ..", 320, 300);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			System.out.println("Max y: " + r.yellowRed.getMaxY());
			System.out.println("this height : " + this.getHeight());

			if (/* r.isDuck() == false && */ r.yellowRed.getMaxY() < this.getHeight() - 20) {
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
				r.setReadyToFire(false);
			}
			break;
		case KeyEvent.VK_ENTER:
			if (state == GameState.Dead) {

				state = GameState.Running;
				thread.stop();
				thread = null;
				if (thread == null) {

					bg1.clear(0, 0);
					bg2.clear(2160, 0);
					r.setCenterx(100);
					r.setCentery(377);
					me.resetEnemy();
					tilearray.clear();
					try {
						MapFileRead();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					thread = new Thread(this);
					thread.start();
				}
			}
			break;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			r.setJump(false);
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
		case KeyEvent.VK_CONTROL:
			r.setReadyToFire(true);
			break;

		}

	}

	@Override
	public void keyTyped(KeyEvent e) {

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

	public static Robot getRobot() {
		return r;

	}
}
