package main.states.entities.statics;

import java.awt.Graphics;

import main.Handler;
import main.gfx.Assets;
import main.states.entities.items.Item;
import main.states.tiles.Tile;

public class Diamond extends StaticEntity{

	public Diamond(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
		
		bounds.x = 6;
		bounds.y = (int)(height / 1.5f);
		bounds.width = width - 30;
		bounds.height = (int)(height - height / 1.5f);
	}

	@Override
	public void update() {
		
	}

	@Override
	public void die() {
		handler.getMap().getItemManager().addItem(Item.diamondItem.createNew((int)x, (int)y));
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.diamond, (int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), width, height, null);
	}

}
