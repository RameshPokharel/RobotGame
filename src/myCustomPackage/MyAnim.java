package myCustomPackage;

import java.awt.Image;

public class MyAnim {
	Thread t;
	Image[] i;
	Boolean b= false;
	Image ci;
	String[] s=new String[]{"character.png","character3.png"};

	AnimationImg a;
	start aa;
	public MyAnim(AnimationImg animationImg)
	{
		a=animationImg;

		i= new Image[2];
	
		for(int j=0;j<i.length;j++)
		{
			i[j]=a.getImage(a.getDocumentBase(),"data/"+s[j]);
		}
		
		
	
	}
	public MyAnim(start start) {
		aa=start;

		i= new Image[2];
	
		for(int j=0;j<i.length;j++)
		{
			i[j]=aa.getImage(aa.getDocumentBase(),"data/"+s[j]);
		}
		}
	public Image getImg()
	{
		
		if(b==false)
		{
		ci=i[0];
		b=true;

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		else
		{
			ci=i[1];
			b=false;
			try {
				Thread.sleep(1300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ci;


	}
	public Boolean getB()
	{
		return b;
	}

/*
		
		if(b==false)
		{
		ci=i[0];
		b=true;
		repaint();

		try {
			Thread.sleep(1300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		else
		{
			ci=i[1];
			b=false;
			repaint();
			try {
				Thread.sleep(150);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	*/
}
