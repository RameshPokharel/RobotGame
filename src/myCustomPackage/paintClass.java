package myCustomPackage;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

public class paintClass extends Applet{
	/*g.setColor(Color.WHITE);
	this.getAppletContext().showStatus("Displaying image");
	g.drawImage(image,0,0,800,480,this);
	g.drawString ("Hello World",25,50);
*//*	int exes[] = { 39,94,97,142,53,58,26 };
	int whys[] = { 33,74,36,70,108,80,106 };
	int pts = exes.length;
	Polygon poly = new Polygon(exes,whys,pts);
	g.fillPolygon(poly);
	g.drawArc(10,20,150,50,25,-130);
	
	
	int x[] ={2,300};
	int y[] ={30,500};
	int t=x.length;
	Polygon poly = new Polygon(x,y,t);
	g.drawPolygon(poly);

	//g.fillArc(10,80,150,50,25,-130);	

	g.fill3DRect(20,20,60,60,false);
	// the lamp platform
	 g.drawRect(0,250,290,290);
	 g.drawRoundRect(0,250,290,290,290,290);

	 // the base of the lamp
	
	  g.drawLine(125,250,125,160);
	 g.drawLine(175,250,175,160);
	 // the lamp shade, top and bottom edges
	 g.drawArc(85,157,130,50,-65,312);
	 g.drawArc(85,87,130,50,62,58);
	 // lamp shade, sides
	g.drawLine(85,177,119,89);
	 g.drawLine(215,177,181,89);
	
	 // dots on the shade
	 g.fillArc(78,120,40,40,63,-174);
	 g.fillRoundRect(120,96,40,40,120,120);
	 g.fillArc(173,100,40,40,110,180);
*/

	@Override
	public void init() {
		AffineTransform identity = new AffineTransform();

		setSize(800, 480);
		setBackground(Color.BLACK);
		setFocusable(true);

	
	}
	@Override
	public void start() {
		repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		  Graphics2D g2d = (Graphics2D) g.create();
		  g.setColor(Color.blue);
		  g.fillRect(20,20,100,100);

		  
		  Rectangle rectangle= new Rectangle(100,200, 300,200);
		  //Rotate rectangle by 1 radian(Math.PI) from the bottom corner
		  g2d.rotate(60, rectangle.x + rectangle.width/2, rectangle.y + rectangle.height/2);

		  g2d.setColor(Color.red);
		  //Draw rectangle
		  g2d.fill(rectangle);

	}
}

// g2d.rotate(60, rectangle.x + rectangle.width/2, 
//rectangle.y + rectangle.height/2);

