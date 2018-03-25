package superEnemy;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import myCustomPackage.Robot;
import myCustomPackage.start;

public class SuperAnimation {

	Image currentImg,currentBullet;
	int currentPos = 0;
	int numPos;
	Float angle = 0.0f;
	int centerX, centerY;
	
	ArrayList<AnimFrame> an;
	ArrayList<Image> bullet;
	
	Boolean b= false;
	public SuperAnimation() {
		an = new ArrayList<>();
		bullet = new ArrayList<>();
	}
	public void setAngle(float angle)
	{
		this.angle=angle;
	}
	public void AddFrame(Image[] i, Image[] j) {
		for (Image img : i) {
			an.add(new AnimFrame(img));
		}
		for (Image img : j) {
			bullet.add(img);
		}
	}

	
	public void checkImgnum() {
		if (angle >= 150)
			numPos = 1;
		else if (angle >= 145 && angle < 150)
			numPos = 2;
		else if (angle >= 130 && angle < 145)
			numPos = 3;
		else if (angle >= 125 && angle < 130)
			numPos = 4;
		else if (angle >= 120 && angle <= 125)
			numPos = 5;
		else if (angle >= 115 && angle < 120)
			numPos = 7;
		else if (angle >= 110 && angle < 115)
			numPos = 8;
		else if (angle >= 105 && angle < 110)
			numPos = 9;
		else if (angle >= 100 && angle < 105)
			numPos = 10;
		else if (angle >= 95 && angle < 100)
			numPos = 11;
		else if (angle >= 90 && angle < 95)
			numPos = 12;
		else if (angle >= 80 && angle < 90)
			numPos = 13;
		else if (angle >= 70 && angle <80)
			numPos = 14;
		else if (angle >= 60 && angle <70)
			numPos = 15;
		updateImgPos();
	}

	private Image getPosiimage(int pos) {

		AnimFrame a = an.get(pos);
		return a.image;
	}

	// 20,40,60,80,100,120,140,160,
	private  synchronized void updateImgPos() {

		if (currentPos == numPos) {
		} else if (currentPos < numPos) {
			currentPos++;
			if (currentPos > 15)
				currentPos = 15;

		} else {
			currentPos--;
			if (currentPos < 0)
				currentPos = 0;
		}
		currentImg = getPosiimage(currentPos);
		currentBullet=bullet.get(currentPos-1);

	}

	

	private class AnimFrame {

		Image image;

		public AnimFrame(Image image) {
			this.image = image;
		}
	}

	public Image getImg() {
		return currentImg;
	}
	public Image getCurrentBullet() {
		return currentBullet;
	}

	


}
