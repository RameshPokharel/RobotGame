package myCustomPackage;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Date;

public class TimeCheckers extends Applet implements
Runnable
{
	Thread runner;
	int xpos;
	int ux1,ux2;
	Image image1;
	Graphics gg;
	Date d;
	@Override
	public void init() {
		// TODO Auto-generated method stub
		super.init();
		setSize(400,400);
		setBackground(Color.blue);
	}
	
	 public void start() {
	 if(runner == null) {
	 runner = new Thread(this);
	 runner.start();
	 }
	 }
	
	 public void stop() {
	 if (runner != null) {
	 runner.stop();
	 runner = null;
	 }
	 }
	
	 public void run() {
	 setBackground(Color.blue);
	 while (true) {
		 d = new Date();
		 repaint();
		 
	 }
	 }
	
	 public void paint(Graphics g) {

	 // Draw background
	/* g.setColor(Color.blue);
	 g.fillRect(0,0,400,400);	
	*/	 g.setFont(new Font("TimesRoman",Font.BOLD,20)); //“TimesRoman”, “Courier”, or “Helvetica”.
		 g.setColor(Color.WHITE);
		 
		 g.drawString(d.toString(), 50,100);
	 }
	 public void update(Graphics g) {
		 if(image1==null)
		 {
			 image1= createImage(this.getWidth(),this.getHeight());
			 gg= image1.getGraphics();
		 }
		   gg.setColor(getBackground());
			gg.fillRect(0, 0, getWidth(), getHeight());
			gg.setColor(getForeground());
			paint(gg);
			g.drawImage(image1, 0, 0, this);
		 }
	
	 }
