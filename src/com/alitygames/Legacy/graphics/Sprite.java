package com.alitygames.Legacy.graphics;

public class Sprite {
	
	public final int SIZE;
	private int x;
	private int y;
	public int[] pixels;
	private SpriteSheet sheet;
	
	public static Sprite flowers = new Sprite (16, 0, 0, SpriteSheet.tiles);
	public static Sprite grass = new Sprite (16, 0, 0, SpriteSheet.tiles);
	public static Sprite tree = new Sprite (16, 0, 0, SpriteSheet.tiles);
	public static Sprite tunnelwladder = new Sprite (16, 0, 0, SpriteSheet.tiles);
	
	
	
	
	
	public Sprite(int size, int x, int y, SpriteSheet sheet){
		SIZE = size;
		pixels = new int [SIZE * SIZE];
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		load();
	}
	
	private void load(){
		for (int y = 0; y < SIZE; y++){
			for (int x = 0; x < SIZE; x++){
				pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
			}
		}
		
	}
	
}
