package com.alitygames.Legacy.level;

import com.alitygames.Legacy.graphics.screen;

public class Level {
	
	protected int width;
	protected int height;
	protected int[] tiles;
	
	public Level (int width, int height){
		this.width = width;
		this.height = height;
		tiles = new int[width * height];
		generateRandomlevel();
	}
	
	public Level (String path){
		loadLevel(path);
	}
	
	protected void generateRandomlevel() {
	}
	
	private void loadLevel(String path){
	}
	
	public void update() {
		
	}
	
	private void time() {
	}
	
	public void render( int xScroll, int yScroll, screen Screen){
	}
	
}
