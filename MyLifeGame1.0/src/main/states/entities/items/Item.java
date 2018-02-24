package main.states.entities.items;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.Handler;
import main.gfx.Assets;

public class Item {
	// static stuff
	
	public static Item[] items = new Item[256];
	public static Item woodItem = new Item(Assets.wood, "Wood", 0);
	public static Item rockItem = new Item(Assets.rock, "Rock", 1);
	public static Item jungleWood = new Item(Assets.jungleWood, "Jungle Wood", 2);
	public static Item meat = new Item(Assets.meat, "Meat", 3);
	public static Item goldItem = new Item(Assets.goldItem, "Gold", 4);
	public static Item diamondItem = new Item(Assets.diamondItem, "Diemond", 5);
	
	// class
	public static final int ITEMWIDTH = 32;
	public static final int ITEMHEIGHT = 32;
	
	protected Handler handler;
	protected BufferedImage texture;
	protected String name;
	protected final int id;
	
	// bounds
	protected Rectangle bounds;
	
	// position
	protected int x;
	protected int y;
	protected int count;
	
	// booleans
	protected boolean pickedUp = false;
	
	public Item(BufferedImage texture, String name, int id) {
		this.texture = texture;
		this.name = name;
		this.id = id;
		count = 1;
		
		bounds = new Rectangle(x, y, ITEMWIDTH, ITEMHEIGHT);
		
		items[id] = this;
	}
	
	
	public void update() {
		if(handler.getMap().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(bounds)) {
			pickedUp = true;
			handler.getMap().getEntityManager().getPlayer().getInventory().addItem(this);
		}
	}
	
	public void render(Graphics g) {
		if(handler == null)
			return;
		render(g, (int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()));
	}
	
	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, ITEMWIDTH, ITEMHEIGHT, null);
	}
	
	public Item createNew(int count) {
		Item i = new Item(texture, name, id);
		i.setPickedUp(true);
		i.setCount(count);
		return i;
	}
	
	public Item createNew(int x, int y) {
		Item i = new Item(texture, name, id);
		i.setPosition(x, y);
		return i;
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
		bounds.x = x;
		bounds.y = y;
	}


	public Handler getHandler() {
		return handler;
	}


	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public boolean isPickedUp() {
		return pickedUp;
	}


	public void setPickedUp(boolean pickedUp) {
		this.pickedUp = pickedUp;
	}

	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}


	public int getId() {
		return id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public BufferedImage getTexture() {
		return texture;
	}


	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}
}
