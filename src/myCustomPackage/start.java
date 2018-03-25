package myCustomPackage;

import java.applet.Applet;
import java.applet.AudioClip;
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

import RobotPackage.RobotAnimation;
import superEnemy.SuperEnemyClass;

public class start extends Applet implements KeyListener, Runnable {

	AudioClip clip;
	int finl = 1000;
	int chk = 0;
	Image character1, character22, character33, character44, character55, currentImg6, fire7, duckNew, fireBall;

	Image swordMan1, swordMan2, swordMan3, swordMan4;
	// Image tree;

	private int checkRunThread = 0;
	private Image fire, image, currentSprite, background, heliboy, heliboy2, heliboy3, heliboy4;
	private Image enemy1, enemy2, enemy3, enemy4, enemy5, enemy6, enemy7, enemy8, enemy9, enemy10, enemy11, enemy12,
			enemy13, enemy14, enemy15, enemy16, enemy17, enemy18;
	public static Image bullet1, bullet2, bullet3, bullet4, bullet5, bullet6, bullet7, bullet8, bullet9, bullet10,
			bullet11, bullet12, bullet13, bullet14, bullet15;
	private Graphics second;
	private URL base;
	static Background bg1, bg2;
	static Robot r;
	public static ManageEnemy me;
	Animation/* ca, */ hca;
	// public static superAnimation sanim;
	public static Image bombWar, tree, tilegrassTop, tilegrassBot, tilegrassLeft, tilegrassRight, tiledirt, tileocean,
			coin;
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
		bg2 = new Background(1800, 0);
		clip = getAudioClip(getCodeBase(), "data/audio/Cabin.wav");
		r = new Robot();
		me = new ManageEnemy();
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Game");
		try {
			base = getDocumentBase();
		} catch (Exception e) {
		}

		// Image Setups
		background = getImage(base, "data/firstBack.png");
		heliboy = getImage(base, "data/heliboy.png");
		heliboy2 = getImage(base, "data/heliboy2.png");
		heliboy3 = getImage(base, "data/heliboy3.png");
		heliboy4 = getImage(base, "data/heliboy4.png");
		tiledirt = getImage(base, "data/tiledirt.png");
		tilegrassTop = getImage(base, "data/topTile.png");
		tilegrassBot = getImage(base, "data/bottomTile.png");
		tilegrassLeft = getImage(base, "data/tilegrassleft.png");
		tilegrassRight = getImage(base, "data/tilegrassright.png");
		tileocean = getImage(base, "data/tileocean.png");
		coin = getImage(base, "data/coinNew.png");
		bombWar = getImage(base, "data/bombaWarr.png");
		enemy1 = getImage(base, "data/aircaft0.png");
		enemy2 = getImage(base, "data/aircaft1.png");
		enemy3 = getImage(base, "data/aircaft2.png");
		enemy4 = getImage(base, "data/aircaft3.png");
		enemy5 = getImage(base, "data/aircaft4.png");
		enemy6 = getImage(base, "data/aircaft5.png");
		enemy7 = getImage(base, "data/aircaft6.png");
		enemy8 = getImage(base, "data/aircaft7.png");
		enemy9 = getImage(base, "data/aircaft8.png");
		enemy10 = getImage(base, "data/aircaft9.png");
		enemy11 = getImage(base, "data/aircaft10.png");
		enemy12 = getImage(base, "data/aircaft11.png");
		enemy13 = getImage(base, "data/aircaft12.png");
		enemy14 = getImage(base, "data/aircaft13.png");
		enemy15 = getImage(base, "data/aircaft14.png");
		enemy16 = getImage(base, "data/aircaft15.png");
		enemy17 = getImage(base, "data/aircaft16.png");
		enemy18 = getImage(base, "data/aircaft17.png");
		bullet1 = getImage(base, "data/bullet/bullet1.png");
		bullet2 = getImage(base, "data/bullet/bullet2.png");
		bullet3 = getImage(base, "data/bullet/bullet3.png");
		bullet4 = getImage(base, "data/bullet/bullet4.png");
		bullet5 = getImage(base, "data/bullet/bullet5.png");
		bullet6 = getImage(base, "data/bullet/bullet6.png");
		bullet7 = getImage(base, "data/bullet/bullet7.png");
		bullet8 = getImage(base, "data/bullet/bullet8.png");
		bullet9 = getImage(base, "data/bullet/bullet9.png");
		bullet10 = getImage(base, "data/bullet/bullet10.png");
		bullet11 = getImage(base, "data/bullet/bullet11.png");
		bullet12 = getImage(base, "data/bullet/bullet12.png");
		bullet13 = getImage(base, "data/bullet/bullet13.png");
		bullet14 = getImage(base, "data/bullet/bullet14.png");
		bullet15 = getImage(base, "data/bullet/bullet15.png");
		fire = getImage(base, "data/bullet/fire.png");

		tree = getImage(base, "data/tree.png");
		character1 = getImage(base, "data/robot/character1.png");
		character33 = getImage(base, "data/robot/character3.png");
		character44 = getImage(base, "data/robot/character4.png");
		character55 = getImage(base, "data/robot/character5.png");
		swordMan1 = getImage(base, "data/robot/swordCharacter1.png");
		swordMan2 = getImage(base, "data/robot/swordCharacter2.png");
		swordMan3 = getImage(base, "data/robot/swordCharacter3.png");
		swordMan4 = getImage(base, "data/robot/swordDown.png");

		fire7 = getImage(base, "data/bullet/fireright.png");
		duckNew = getImage(base, "data/robot/ducknew.png");
		fireBall = getImage(base, "data/fireBall.png");
		r.addFrame(character33);
		r.addFrame(character44);
		r.addFrame(character1);
		r.addFrame(character55);
		r.addFrame(character1);
		r.addFrame(character22);
		r.addFrame(duckNew);
		r.addFrame(swordMan1);
		r.addFrame(swordMan2);
		r.addFrame(swordMan3);
		r.addFrame(swordMan4);

		try {
			MapFileRead();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		hca = new Animation();
		me.s.sanim.AddFrame(
				new Image[] { enemy1, enemy2, enemy3, enemy4, enemy5, enemy6, enemy7, enemy8, enemy9, enemy10, enemy11,
						enemy12, enemy13, enemy14, enemy15, enemy16, enemy17, enemy18 },
				new Image[] { bullet1, bullet2, bullet3, bullet4, bullet5, bullet6, bullet7, bullet8, bullet9, bullet10,
						bullet11, bullet12, bullet13, bullet14, bullet15 });
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
		currentSprite = character1;
		checkRunThread = 0;
		thread = new Thread(this);
		thread.start();

	}

	@Override
	public void stop() {
		thread.stop();
		checkRunThread = 1;

	}

	@Override
	public void destroy() {
	}

	@Override
	public void run() {
		if (state == GameState.Running) {

			while (true) {

				/*
				 * if(chk<15 ){ clip.play(); } chk+=14; if(chk>finl) {
				 * System.out.println("chk: "+chk); chk= chk%finl; }
				 */
				bg1.update();
				bg2.update();
				r.update();
				currentSprite = r.getImage();
				if (currentSprite == null) {
					currentSprite = character1;
				}

				animate();

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
				if (r.getCentery() > 500 || r.getHealth()<=0) {
					state = GameState.Dead;
				}
			}

		} else
			repaint();
	}

	public void animate() {
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
			g.drawImage(background, bg2.getBgX(), bg2.getBgY(), this);

			g.drawImage(background, bg1.getBgX(), bg1.getBgY(), 2160, 480, this);
			// g.drawImage(background, bg2.getBgX(), bg2.getBgY(), this);
			paintTiles(g);
			ArrayList projectiles = r.getProjectiles();
			for (int i = 0; i < projectiles.size(); i++) {
				Projectile p = (Projectile) projectiles.get(i);
				g.setColor(Color.YELLOW);
				// g.fillRect(p.getX(), p.getY(), 10, 5);
				g.drawImage(fireBall, p.getX(), p.getY() - 5, 15, 15, this);
			}

			int startX = r.getCenterx() - myStaticClass.robotCenterXValue;
			int startY = r.getCentery() - myStaticClass.robotCenterYValue;
			int yy = myStaticClass.robotHeight;
			int xx = myStaticClass.robotWidth;
			if (currentSprite == duckNew) {
				startY += 20;
				yy -= 20;

			}
			g.drawImage(currentSprite, startX, startY, xx, yy, this);
				 int health = r.getHealth(); 
				 Color c=Color.GREEN;
				 myStaticClass.enemyHealth(health,c);
				 g.setColor(myStaticClass.cc);
				 g.drawRect(r.getCenterx() - 50, r.getCentery() - 12, 20, 10);

				 g.fillRect(r.getCenterx() - 50, r.getCentery() - 12, myStaticClass.x, 10);
				 
			
			me.paintItem(g, hca.getImage(), this);

			g.drawImage(me.s.sanim.getImg(), me.s.getX(), 40, 100, 100, this);
			g.setFont(font);
			g.setColor(Color.WHITE);
			g.drawString(Integer.toString(score), 740, 30);
			g.drawImage(coin, 504, 0, 40, 40, this);
			g.drawString(Integer.toString(r.getGoldCoin()), 470, 30);

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
			r.setMovingRight(true, 1);
			r.setReadyToFire(false);

			break;

		case KeyEvent.VK_SPACE:
			break;

		case KeyEvent.VK_CONTROL:
			if (r.isDuck() == false && r.isJumped() == false) {
				r.setReadyToFire(true);
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
					r.clear();
					me.resetEnemy();
					tilearray.clear();
					try {
						MapFileRead();
					} catch (FileNotFoundException e1) {

					}
					thread = null;
					thread = new Thread(this);
					thread.start();
				}
			}
			break;

		case KeyEvent.VK_PAGE_DOWN:
			if (r.isDuck() == false)
				System.out.println("sword Release");
			r.setToSword(true, 1);
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
			r.setMovingRight(false, 1);

			bg1.stop();
			bg2.stop();

			break;

		case KeyEvent.VK_SPACE:
			if (checkRunThread == 0)
				this.stop();
			else {
				this.start();
			}
			break;
		case KeyEvent.VK_CONTROL:
			r.shoot();
			r.setReadyToFire(false);

			break;

		case KeyEvent.VK_PAGE_DOWN:
			r.setToSword(false, 1);

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
			ArrayList<enemyCheck> a = t.a;
			for (enemyCheck e : a) {
				if (e.isVisible()) {
					e.update();
				}
			}
		}

	}

	private void paintTiles(Graphics g) {
		for (int i = 0; i < tilearray.size(); i++) {
			MyTile t = (MyTile) tilearray.get(i);
			if (t.type == 0) {

			} else {

				g.drawImage(t.getTileImage(), t.getTileX(), t.getTileY(), myStaticClass.tileWidth,
						myStaticClass.tileHeight, this);
				Color c = Color.GREEN;
				myStaticClass.enemyHealth(t.getCurrentHealth(), c);
				if (t.type == 5) {
					g.setColor(myStaticClass.cc);
					g.drawRect(t.getTileX() + 20, t.getTileY() + 10, 20, 5);
					g.setColor(myStaticClass.cc);

					g.fillRect(t.getTileX() + 20, t.getTileY() + 10, myStaticClass.x, 5);
					ArrayList<enemyCheck> a = t.a;
					for (enemyCheck e : a) {
						g.drawImage(fire, e.getX(), e.getY() + 10, 20, 10, this);
					}
				}
				
			}

		}
	}

	public static Robot getRobot() {
		return r;

	}

}
