package myCustomPackage;

import java.awt.Color;

public class myStaticClass {

	public static int screenWidth = 800;
	public static int screenHeight = 480;
	public static int tileHeight = 40;
	public static int tileWidth = 40;
	public static int robotXpos = 0;
	public static int robotYpos = 80;
	public static int robotHeight = 80;
	public static int robotWidth = 50;
	public static int robotCenterXValue = robotWidth / 2;
	public static int robotCenterYValue = robotHeight / 2;
	public static int enemyHealth = 100;
	public static int heliBoyHeight = 40;
	public static int heliBoyWidth = 40;
	public static Color cc;
	public static int x;
	public static int enemyHealth(int ch, Color c) {
		cc=c;
		if (ch > 80)
			x = 20;

		else if (ch <= 80 && ch > 60)
			x = 16;
		else if (ch <= 60 && ch > 40)
			x = 12;
		else if (ch <= 40 && ch > 20) {
			x = 8;
			cc = Color.RED;
		} else if (ch <= 20 & ch >5) {
			x = 5;
			cc = Color.RED;
		} else {
			x = 3;
			cc = Color.RED;
		}	
		return x;
		}

	

}
