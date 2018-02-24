package main.states.maps;

import java.awt.Graphics;

import main.Handler;
import main.states.entities.EntityManager;
import main.states.entities.creatures.Player;
import main.states.entities.items.ItemManager;
import main.states.entities.statics.Diamond;
import main.states.entities.statics.Gold;
import main.states.entities.statics.JungleTree;
import main.states.entities.statics.Stone;
import main.states.entities.statics.Tree;
import main.states.entities.statics.enemies.Zombie;
import main.states.tiles.Tile;

public class Map {
	private Handler handler;
	
	private int width;
	private int height;
	@SuppressWarnings("unused")
	private int spawnX;
	@SuppressWarnings("unused")
	private int spawnY;
	
	private int[][] tiles;
	
	// entity manager
	private EntityManager entityManager;
	
	// item manager
	private ItemManager itemManager;
	
	public Map(Handler handler, String path) {
		this.handler = handler;
		
		entityManager = new EntityManager(handler, new Player(handler, 100, 100));
		itemManager = new ItemManager(handler);
		
		entityManager.addEntity(new Tree(handler, 250, 300));
		entityManager.addEntity(new Stone(handler, 350, 200));
		entityManager.addEntity(new Stone(handler, 670, 896));
		entityManager.addEntity(new Tree(handler, 503, 794));
		entityManager.addEntity(new JungleTree(handler, 657, 89));
		entityManager.addEntity(new Stone(handler, 1600, 1000));
		entityManager.addEntity(new Diamond(handler, 1875, 1500));
		entityManager.addEntity(new Diamond(handler, 1696, 1468));
		entityManager.addEntity(new Gold(handler, 1896, 1768));
		entityManager.addEntity(new Stone(handler, 1600, 400));
		entityManager.addEntity(new Stone(handler, 1700, 700));
		entityManager.addEntity(new Tree(handler, 1503, 794));
		entityManager.addEntity(new Tree(handler, 1503, 794));
		entityManager.addEntity(new Tree(handler, 1903, 794));
		entityManager.addEntity(new Tree(handler, 1603, 794));
		entityManager.addEntity(new JungleTree(handler, 1457, 189));
		entityManager.addEntity(new JungleTree(handler, 1357, 489));
		entityManager.addEntity(new JungleTree(handler, 1767, 789));
		
		entityManager.addEntity(new Zombie(handler, 754, 443));
		entityManager.addEntity(new Zombie(handler, 894, 443));
		entityManager.addEntity(new Zombie(handler, 354, 443));
		entityManager.addEntity(new Zombie(handler, 1100, 50));
		entityManager.addEntity(new Zombie(handler, 1400, 50));
		entityManager.addEntity(new Zombie(handler, 1250, 50));
		entityManager.addEntity(new Zombie(handler, 1896, 1368));
		entityManager.addEntity(new Zombie(handler, 1646, 1368));
		entityManager.addEntity(new Zombie(handler, 1996, 1368));
		entityManager.addEntity(new Zombie(handler, 1996, 1668));
		entityManager.addEntity(new Zombie(handler, 1876, 1668));
		entityManager.addEntity(new Zombie(handler, 1596, 1668));
		
		loadMap(path);
	}
	
	public void update() {
		entityManager.update();
		itemManager.update();
	}
	
	public void render(Graphics g) {
		int xStart = (int)Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
		int xEnd = (int)Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
		int yStart = (int)Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
		int yEnd = (int)Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);
		
		for(int y = yStart; y < yEnd; y++) {
			for(int x = xStart; x < xEnd; x++) {
				getTile(x, y).render(g, (int)(x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()), (int)(y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
			}
		}
		
		// entity manager
		entityManager.render(g);
		
		// item manager
		itemManager.render(g);
	}
	
	public Tile getTile(int x, int y) {
		if(x < 0 || y < 0 || x >= width || y >= height)
			return Tile.grassTile;
		
		Tile t = Tile.tiles[tiles[x][y]];
		
		if(t == null)
			return Tile.dirtTile;
		return t;
	}
	
	private void loadMap(String path) {
		String file = Utils.loadFile(path);
		String[] tokens = file.split("\\s+");
		
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		
		tiles = new int[width][height];
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
			}
		}
	}

	public int getWidth() { return width; }
	public void setWidth(int width) { this.width = width; }
	public int getHeight() { return height; }
	public void setHeight(int height) { this.height = height; }
	public EntityManager getEntityManager() { return entityManager; }
	public void setEntityManager(EntityManager entityManager) { this.entityManager = entityManager; }
	public ItemManager getItemManager() { return itemManager; }
	public void setItemManager(ItemManager itemManager) { this.itemManager = itemManager; }
}
