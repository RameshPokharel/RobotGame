package myCustomPackage;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageCreate extends Applet implements Runnable {

	boolean b= false;
	Image image,image2;
	Graphics second;
	int x,y=0;
	Image i;
	Thread t;
	URL  url;
	@Override
	public void run() {
		
	while(true)
	{
		
		check();
		
			repaint();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
		
	}
		
		
	}

	private void check() {
		if(x>=200)
		{
			x-=10;
			b=true;
		}
		if(b==true)
		{
			x-=10;
			if(x<=0)
			{
				b=false;
				x=0;
			}
		}
		
		 if(b==false)
			x+=10;
		
	}

	@Override
	public void init() {
		super.init();
		setSize(400,500);
		try {
			url=new URL(getDocumentBase(),"data/background.png");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		i=getImage(url);
		if(t==null)
		{
		t= new Thread(this);
		t.start();
		}
	}
	
	@Override
	public void start() {
		super.start();
	}
	@Override
	public void stop() {
		super.stop();
	}
	@Override
	public void destroy() {
		super.destroy();
	}

	@Override
	public void paint(Graphics g) {
		 g.drawImage(i,0,0,this);
		 g.setColor(Color.red);
		 g.drawOval(x,5,90,90);
		
	
	}
	@Override
	public void update(Graphics g) {
		
		
		paint(g);	}

	
}
