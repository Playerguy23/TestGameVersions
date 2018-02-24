package main.gfx;

import java.awt.Font;
import java.awt.image.BufferedImage;

public class Assets {
	private static SpriteSheet sheet;
	public static Font font28;
	
	private static int width = 32;
	private static int height = 32;
	
	public static BufferedImage[] player_up;
	public static BufferedImage[] player_down;
	public static BufferedImage[] player_left;
	public static BufferedImage[] player_right;

	public static BufferedImage[] zombie;
	
	public static BufferedImage grassTile;
	public static BufferedImage dirtTile;
	public static BufferedImage sandTile;
	public static BufferedImage rockTile;
	
	public static BufferedImage tree;
	public static BufferedImage rock;
	public static BufferedImage jungleTree;
	public static BufferedImage gold;
	public static BufferedImage diamond;
	
	public static BufferedImage wood;
	public static BufferedImage jungleWood;
	public static BufferedImage meat;
	public static BufferedImage diamondItem;
	public static BufferedImage goldItem;
	
	public static BufferedImage inventory;
	
	public static void init() {
		sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));
		font28 = Fontloader.fontLoader("res/fonts/slkscr.ttf", 28);
		
		player_down = new BufferedImage[2];
		player_up = new BufferedImage[2];
		player_left = new BufferedImage[2];
		player_right = new BufferedImage[2];
		
		zombie = new BufferedImage[2];
		
		player_down[0] = sheet.crop(width * 4, 0, width, height);
		player_down[1] = sheet.crop(width * 5, 0, width, height);
		
		player_up[0] = sheet.crop(width * 6, 0, width, height);
		player_up[1] = sheet.crop(width * 7, 0, width, height);
		
		player_right[0] = sheet.crop(width * 4, height, width, height);
		player_right[1] = sheet.crop(width * 5, height, width, height);
		
		player_left[0] = sheet.crop(width * 6, height, width, height);
		player_left[1] = sheet.crop(width * 7, height, width, height);
		
		zombie[0] = sheet.crop(width * 4, height * 2, width, height);
		zombie[1] = sheet.crop(width * 5, height * 2, width, height);
		
		grassTile = sheet.crop(width * 2, 0, width, height);
		dirtTile = sheet.crop(width, 0, width, height);
		sandTile = sheet.crop(width, height * 2, width, height);
		rockTile = sheet.crop(width * 3, 0, width, height);
		
		tree = sheet.crop(0, 0, width, height * 2);
		jungleTree = sheet.crop(0, height * 3, width, height * 2);
		rock = sheet.crop(0, height * 2, width, height);
		diamond = sheet.crop(width, height * 3, width, height);
		gold = sheet.crop(width * 3, height * 3, width, height);
		
		wood = sheet.crop(width, height, width, height);
		jungleWood = sheet.crop(width * 2, height * 2, width, height);
		meat = sheet.crop(width * 2, height, width * 2, height);
		diamondItem = sheet.crop(width * 3, height * 2, width, height);
		goldItem = sheet.crop(width * 2, height * 3, width, height);
		
		inventory = ImageLoader.loadImage("/textures/inventoryScreen.png");
	}
}
