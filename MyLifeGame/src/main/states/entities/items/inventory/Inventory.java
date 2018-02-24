package main.states.entities.items.inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import main.Handler;
import main.gfx.Assets;
import main.gfx.Text;
import main.states.entities.items.Item;

public class Inventory {
	private Handler handler;
	private boolean active = false;
	private ArrayList<Item> inventoryItems;
	
	// inventory items position
	private int invX = 64;
	private int invY = 48;
	
	// inventory items size
	private int invWidth = 512;
	private int invHeight = 384;
	
	// inventory items lists
	private int invListCenterX = invX + 171;
	private int invListCenterY = invY + invHeight / 2 + 5;
	private int invSpaceingList = 30;
	
	// inventory items images
	private int invImageX = 452;
	private int invImageY = 82;
	
	// inventory images size
	private int invImageWidth = 64;
	private int invImageHeight = 64;
	
	// inventory count
	private int invCountX = 484;
	private int invCountY = 172;
	
	private int selectItem = 0;
	
	public Inventory(Handler handler) {
		this.handler = handler;
		inventoryItems = new ArrayList<Item>();
	}
	
	public void update() {
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_E))
			active = !active;
		if(!active)
			return;
		
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_W))
			selectItem--;
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_S))
			selectItem++;
		
		if(selectItem < 0) {
			selectItem = inventoryItems.size() - 1;
		}else if(selectItem >= inventoryItems.size()) {
			selectItem = 0;
		}
	}
	
	public void render(Graphics g) {
		if(!active)
			return;
		g.drawImage(Assets.inventory, invX, invY, invWidth, invHeight, null);
		
		int len = inventoryItems.size();
		
		if(len == 0)
			return;
		
		for(int i = -5; i < 6; i++) {
			if(selectItem + i < 0 || selectItem + i >= len)
				continue;
			if(i == 0) {
				Text.drawString(g, "> " + inventoryItems.get(selectItem + i).getName() + " <", invListCenterX, invListCenterY + i * invSpaceingList, true, Color.YELLOW, Assets.font28);
			}else {
				Text.drawString(g, "> " + inventoryItems.get(selectItem + i).getName() + " <", invListCenterX, invListCenterY + i * invSpaceingList, true, Color.WHITE, Assets.font28);
			}
			
			Item item = inventoryItems.get(selectItem);
			g.drawImage(item.getTexture(), invImageX, invImageY, invImageWidth, invImageHeight, null);
			Text.drawString(g, Integer.toString(item.getCount()), invCountX, invCountY, true, Color.WHITE, Assets.font28);
		}
	}
	
	public void addItem(Item item) {
		for(Item i : inventoryItems) {
			if(i.getId() == item.getId()) {
				i.setCount(i.getCount() + item.getCount());
				return;
			}
		}
		inventoryItems.add(item);
	}

	public boolean isActive() { return active; }
	public void setActive(boolean active) { this.active = active; }
	public ArrayList<Item> getInventoryItems() { return inventoryItems; }
	public void setInventoryItems(ArrayList<Item> inventoryItems) { this.inventoryItems = inventoryItems; }
	
}
