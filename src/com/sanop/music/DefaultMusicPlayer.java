package com.sanop.music;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import res.SoundResource;

public class DefaultMusicPlayer extends MusicPlayer {
	
	private Player player;
	private boolean isPlaying;
	
	public DefaultMusicPlayer (SoundResource resource, boolean isLoop) {
		super(resource, isLoop);
		
		this.player = null;
		this.onPlay = ()->{
			try {
				do {
					player = resource.getPlayer();
					isPlaying = true;
					player.play();
				} while (isPlaying && this.isLoop);
			} catch (JavaLayerException e) {
				// do nothing
			} finally {
				isPlaying = false;
			}
		};
		this.isPlaying = false;
	}
	
	public DefaultMusicPlayer (SoundResource resource) {
		this(resource, false);
	}
	
	@Override
	public int getTime () {
		if(isPlaying) return player.getPosition();
		else return 0;
	}
	
	@Override
	public boolean isPlaying () {
		return isPlaying;
	}
	
	@Override
	public void play () {
		if(isPlaying)
			throw new IllegalStateException("player was playing, but tried to play again");
		
		thread = new Thread(onPlay);
		thread.start();
	}
	
	@Override
	public void stop () {
		if(!isPlaying)
			throw new IllegalStateException("player was not playing, but tried to stop");
		
		isPlaying = false;
		player.close();
	}
}