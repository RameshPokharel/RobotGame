package myCustomPackage;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.net.MalformedURLException;
import java.net.URL;

public class RotateImg extends Applet {

	Image img;
	URL url;

	@Override
	public void init() {

		setSize(300, 600);
		try {
			url = new URL(getDocumentBase(), "data/heliboy.png");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		img = getImage(url);

	}

	@Override
	public void start() {
		repaint();
	}

	@Override
	public void paint(Graphics g) {

		/*AffineTransform identity = new AffineTransform();
		AffineTransform trans = new AffineTransform();
		trans.setTransform(identity);
		trans.rotate(Math.toRadians(10));
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.drawImage(img, trans, this);
*/
		
		/*g.drawImage(img, 100,200, this);
		g.fillRoundRect(100, 100, 20, 50, 120, 120);
	*/	
		
		int exes[] = {105,110,125,129,130,130,129,125,110 };
		int whys[] = {26,21,21,24,25,26,27,31,31};
		int pts = exes.length;
		Polygon poly = new Polygon(exes,whys,pts);
		g.setColor(Color.GRAY);
		g.fillPolygon(poly);
	}
}
