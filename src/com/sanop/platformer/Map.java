package com.sanop.platformer;

import com.sanop.music.AdvancedMusicPlayer;
import com.sanop.platformer.entity.Block;
import com.sanop.platformer.entity.PlayerInteractive;
import com.sanop.platformer.event.EventManager;
import com.sanop.music.DefaultMusicPlayer;
import com.sanop.effect.ScreenEffectIterator;
import com.sanop.platformer.Synchronizer;
import res.ImageResource;
import res.SoundResource;

import java.awt.*;
import java.util.ArrayList;

public class Map {
	private ArrayList<Block> blocks;
	private ArrayList<PlayerInteractive> entities;
	private EventManager manager;

	//TODO : Read From File!!
	AdvancedMusicPlayer bgm = new AdvancedMusicPlayer(SoundResource.THE_GHOST);
	Image bgImage = ImageResource.MAP_1.getImageIcon().getImage();

	public Map () {
		blocks = new ArrayList<>();
	}

	public Map setManager(EventManager manager){
		this.manager = manager;
		return this;
	}

	public void addBlock (Block newBlock) {
		blocks.add(newBlock);
	}

	public ArrayList<Block> getBlocks () {
		return blocks;
	}

	public ArrayList<PlayerInteractive> getEntities() {
		return entities;
	}

	public EventManager getManager() {
		return manager;
	}

	public AdvancedMusicPlayer getBgm() {
		return bgm;
	}

	public Image getBgImage() {
		return bgImage;
	}
}