package com.sanop.platformer;

import com.sanop.effect.ScreenEffect;
import com.sanop.effect.ScreenEffectIterator;
import com.sanop.music.MusicPlayer;
import com.sanop.key.Key;
import com.sanop.key.KeyStatus;
import com.sanop.platformer.event.EventManager;

public class Synchronizer {
	
	private Engine engine;
	private MusicPlayer player;
	private ScreenEffectIterator effects;
	private EventManager eventManager;
	private int ticks;
	
	public Synchronizer (Engine engine, MusicPlayer player, ScreenEffectIterator effects) {
		this.engine = engine;
		this.player = player;
		this.effects = effects;
		this.eventManager = new EventManager(engine, effects);
		ticks = 0;
	}
	
	public void update () {
		while(ticks * 50 < player.getTime() * 3) {
			ticks ++;
			
			eventManager.update(ticks);
			engine.tick();
			ScreenEffect.setSeed(ticks);
		}
		
		
		if(KeyStatus.isKeyJustPressed(Key.DOWN)) {
			KeyStatus.setKeyProcessed(Key.DOWN);
			System.out.println(ticks);
		}
	}
	
	public int getTicks () {
		return ticks;
	}
	public Engine getEngine () {
		return engine;
	}
	public ScreenEffectIterator getEffects () {
		return effects;
	}
	
}

