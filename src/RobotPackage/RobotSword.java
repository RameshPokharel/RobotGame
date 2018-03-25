package RobotPackage;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.ArrayList;

public class RobotSword extends Applet implements Runnable, KeyListener {

	Image i,currentImage;
	Graphics gs;
	Thread t;
	Boolean b=false;
	URL url;
	int chkIntSword=0,xSword=0,countSword=0,xPos=0;
	Image swordMan1,swordMan2,swordMan3,swordMan4;
	ArrayList<Image> a;
	@Override
	public void init() {
		
		setSize(400,500);
		addKeyListener(this);
		t= new Thread(this);
		a= new ArrayList<>();
		url= getDocumentBase();
		swordMan1=getImage(url,"data/robot/swordCharacter1.png");
		swordMan2=getImage(url,"data/robot/swordCharacter2.png");
		swordMan3=getImage(url,"data/robot/swordCharacter3.png");
		swordMan4=getImage(url,"data/robot/swordDown.png");
		
	}
	@Override
	public void start() {
		t.start();
	}
	@Override
	public void stop() {
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		
		switch(e.getKeyCode())
		{
		case KeyEvent.VK_SPACE:
			b=true;
			chkIntSword=1;
			System.out.println("b: "+b);
			
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode())
		{
		case KeyEvent.VK_SPACE:
			b=false;
			
			
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}

	@Override
	public void run() {
		while(true)
		{
			System.out.println("run");
			currentImage=swordMan1;
				if (b|| chkIntSword > 0) {

					xSword += 1;
					if (xSword < 10) {
						{
							countSword += 1;
							if (countSword >3) {
								xPos += 1;
								countSword = 0;
							}
						}
						currentImage =swordMan2;

					} else if (xSword >= 10 && xSword < 20) {
						xSword += 1;

						countSword += 1;
						if (countSword >5) {
							xPos += 2;
							countSword = 0;
						}

						currentImage = swordMan3;
					} else if (xSword >= 20 && xSword < 30) {
						xSword += 1;

						countSword += 1;
						if (countSword > 5) {
							xPos += 2;
							countSword = 0;
						}
						currentImage =swordMan2;

					} else if (xSword >= 30 && xSword < 40) {

						xSword += 1;

						countSword += 1;
						if (countSword > 5) {
							xPos += 2;
							countSword = 0;
						}
						currentImage = swordMan4;
						chkIntSword = 0;
						xSword=0;
					}


			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			repaint();

		}
		}
	}

	@Override
	public void update(Graphics g) {
		if(i==null)
		{
			i=createImage(this.getWidth(),this.getHeight());
			gs=i.getGraphics();
		}
		gs.setColor(getBackground());
		gs.fillRect(0,0,this.getWidth(),this.getHeight());
		gs.setColor(getForeground());
		paint(gs);
		g.drawImage(i, 0, 0, this);

	}
	@Override
	public void paint(Graphics g) {
		g.drawImage(currentImage, xPos, 200, 100,100,this);
	}
}
