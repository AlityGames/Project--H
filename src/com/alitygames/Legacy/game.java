package com.alitygames.Legacy;
/**IMPORTING**/
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.alitygames.Legacy.graphics.screen;
import com.alitygames.Legacy.input.Keyboard;

public class game extends Canvas implements Runnable{
	
private static final long serialVersionUID = 1L;
/**setting resolution**/	
	public static int width = 300;
	public static int height = width / 16 * 9;
	public static int scale = 3;
	public static String title = "ProjectH";
/**New Thread
 * "running" method **/
	private Thread thread;
	private boolean running = false;
	
	/**"screen" method **/
	private screen Screen;
	
	/**"frame" method **/
	private JFrame frame;
	
	/** Input **/
	private Keyboard key;
	
	
	/**BufferImage**/
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	/**setting window size**/
	public game() {
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);
		Screen = new screen(width, height);
		
		frame = new JFrame();
		/** Adding Input **/
		key = new Keyboard();
		addKeyListener(key);
	}
	
	/**start command**/
	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}
	
	/**stop command**/
	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**run processes**/
	//!!!!ESSENTIAL!!!!//
	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		int frames = 0;
		int updates = 0;
		double delta = 0;
		requestFocus();
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if (System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				frame.setTitle(title + "    " + "FPS Counter: " + frames);
				updates = 0;
				frames = 0;
			}
		}
		
	}
	int x=0, y=0;
/**update**/
public void update() {
	key.update();
	if (key.up) y--;
	if (key.down) y++;
	if (key.left) x--;
	if (key.right) x++;
	
}
/**render**/
public void render() {
	BufferStrategy bs = getBufferStrategy();
	if (bs == null) {
		createBufferStrategy(3);
		return; 
	}
	Screen.clear();
	Screen.render(x, y);
	
	for (int i = 0; i < pixels.length; i++) {
		pixels[i] = Screen.pixels[i];
		
	}
	
	Graphics g = bs.getDrawGraphics();
	g.drawImage(image,0, 0, getWidth(), getHeight(), null);
	g.dispose();
	bs.show();
	
} 
/**start "Game"**/
	public static void main(String[] args){
		game Game = new game();
		Game.frame.setResizable(false);
		Game.frame.setTitle(game.title);
		Game.frame.add(Game);
		Game.frame.pack();
		Game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Game.frame.setVisible(true);
		
		Game.start();
	}

}

