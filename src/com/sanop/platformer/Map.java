package com.sanop.platformer;

import com.sanop.music.AdvancedMusicPlayer;
import com.sanop.platformer.entity.Block;
import com.sanop.platformer.entity.PlayerInteractive;
import com.sanop.platformer.event.BlockEvent;
import com.sanop.platformer.event.EventBuffer;
import com.sanop.platformer.event.EventManager;
import res.ImageResource;
import res.SoundResource;

import java.awt.*;
import java.util.ArrayList;
import java.util.function.Function;

public class Map {
	private ArrayList<BlockEvent> blockEvents;
	private ArrayList<PlayerInteractive> entities;
	private ArrayList<EventBuffer> eventBuffers;
	private EventManager manager;

	//TODO : Read From File!!
	AdvancedMusicPlayer bgm = new AdvancedMusicPlayer(SoundResource.THE_GHOST);
	Image bgImage = ImageResource.MAP_1.getImageIcon().getImage();

	public Map () {
		eventBuffers = new ArrayList<>();
	}

	public Map init(EventManager manager, Engine engine){
		this.manager = manager;
		for(EventBuffer e : eventBuffers){
			if(e.getType() == EventBuffer.bufType.BLOCK_EVENT) manager.addEvent(e.makeBlockEvent(engine));
			else if(e.getType() == EventBuffer.bufType.F_BULLET_EVENT) manager.addEvent(e.makeFBulletEvent(engine));
		}
		manager.sortEvents();
		return this;
	}

	public void addEvent (EventBuffer.bufType type, String[] inp) {
		eventBuffers.add(new EventBuffer(type, inp));
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