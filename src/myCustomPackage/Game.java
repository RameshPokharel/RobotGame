package myCustomPackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class Game extends JComponent implements Runnable {

    Thread game = null;

    /** The constructor. I would like to initialize here */
    public Game(){
        // Create and set up the frame
        JFrame frame = new JFrame("My Game");
        frame.setSize(500, 700);
        frame.setLocationRelativeTo(null);
        frame.add(this);
        frame.setVisible(true);
          
    }
    public static void main(String[] args) {
    	 Thread game = new Thread(new Game());
    	 
         game.start();
	}

    /** The run() method. I'll be using it as a game loop */
    @Override
    public void run(){
        while(true){
            repaint();
            try {
                Thread.sleep(30);
            } catch (InterruptedException ex) {
                // I don't want to do anything
            }
        }
    }

    /** I was doing a test draw here, but getting an error */
    @Override
    public void paintComponent(Graphics gr){
        Graphics2D g = (Graphics2D) gr;
        g.setColor(Color.BLACK);
        g.fillRect(200, 200, 200, 200);
    }

}