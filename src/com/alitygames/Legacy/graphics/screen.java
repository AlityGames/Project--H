package com.alitygames.Legacy.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class screen {

	private int width;
	private int height;

	public int[] pixels;
	public final int Map_SIZE = 64;
	public final int Map_SIZE_MASK = Map_SIZE - 1;
	public int[] tiles = new int [Map_SIZE * Map_SIZE];
	
	private Random random = new Random();
	
	/**Animation**/
	int xtime = 0, ytime = 0;
	int counter =0;
	
	
	
	public screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height]; //113,850//
		
		for (int i = 0; i < Map_SIZE * Map_SIZE; i++) {
			tiles[i] = random.nextInt(0x3d2faa);
		}
		
	}
	
	public void clear() {
		for (int i = 0; i <pixels.length; i++) {
			pixels[i] = 0;
	}	
	}
	
	/**Render pixels **/
	public void render(int xOffset, int yOffset) {

		for (int y = 0; y< height; y++){
			int yp = y + yOffset;
			if (yp < 0 || yp >= height) continue;
			for (int x = 0; x < width; x++) {
				int xp = x + xOffset;
				if (xp < 0 || xp >= width) continue;
				pixels[xp + yp * width] = Sprite.flowers.pixels[(x & 15) + (y & 15) * Sprite.flowers.SIZE];
				
			}
		}
	}
	
	
}
