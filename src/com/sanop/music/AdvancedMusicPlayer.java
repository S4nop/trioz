package com.sanop.music;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.FactoryRegistry;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;
import res.SoundResource;

public class AdvancedMusicPlayer extends MusicPlayer{
	
	private static final int NOT_STARTED = 0;
	private static final int PLAYING = 1;
	private static final int PAUSED = 2;
	private static final int STOPPED = 3;
	
	private int pausedFrame;
	private int state;

	private AdvancedPlayer player;
	private AudioDevice device;
	private PlaybackListener listener;

	private Runnable onResume;
	private MusicPlayerListener playerListener;
	
	public AdvancedMusicPlayer (SoundResource resource, boolean isLoop) {
		super(resource, isLoop);
		
		this.pausedFrame = 0;
		this.state = NOT_STARTED;
		this.device = null;
		this.player = null;
		this.playerListener = null;
		
		this.listener = new PlaybackListener() {
			@Override
			public void playbackFinished (PlaybackEvent playbackEvent) {
				int offset = (int)(pausedFrame * resource.getMsPerFrame());
				pausedFrame = (int)((device.getPosition() + offset) / resource.getMsPerFrame());
				super.playbackFinished(playbackEvent);
			}
		};
		
		this.onPlay = ()->{
			try {
				do {
					player = getNewPlayer();
					player.play(Integer.MAX_VALUE);
				} while (state == PLAYING && this.isLoop);
				
				if(state == PLAYING && playerListener != null) {
					state = STOPPED;
					playerListener.onComplete();
				}
			} catch (JavaLayerException e) {
				// do nothing
			}
		};
		this.onResume = ()->{
			try {
				do {
					player = getNewPlayer();
					player.play(pausedFrame, Integer.MAX_VALUE);
				} while (state == PLAYING && this.isLoop);
				
				if(state == PLAYING && playerListener != null) {
					state = STOPPED;
					playerListener.onComplete();
				}
			} catch (JavaLayerException e) {
				// do nothing
			}
		};
	}
	
	public AdvancedMusicPlayer (SoundResource resource) {
		this(resource, false);
	}
	
	@Override
	public int getTime () {
		int offset = (int)(pausedFrame * resource.getMsPerFrame());
		if(device != null) return device.getPosition() + offset;
		else return offset;
	}
	
	@Override
	public boolean isPlaying () {
		return state == PLAYING;
	}
	
	public void setPlayerListener (MusicPlayerListener listener) {
		this.playerListener = listener;
	}
	
	public void play (MusicPlayerListener listener) {
		if(state == PLAYING)
			throw new IllegalStateException("player was playing, but tried to play again");
		
		state = PLAYING;
		playerListener = listener;
		thread = new Thread(onPlay);
		
		thread.start();
	}
	
	@Override
	public void play () {
		play(null);
	}
	
	@Override
	public void stop () {
		if(state == PLAYING) {
			state = STOPPED;
			player.stop();
			listener = null;
		}
	}
	
	public void pause () {
		if(state != PLAYING)
			throw new IllegalStateException("player was not playing, but tried to pause");
		
		state = PAUSED;
		player.stop();
	}
	public void resume () {
		if(state != PAUSED)
			throw new IllegalStateException("player was not paused, but tried to resume");
		
		state = PLAYING;
		thread = new Thread(onResume);
		thread.start();
	}
	
	private AdvancedPlayer getNewPlayer () {
		try {
			this.device = FactoryRegistry.systemRegistry().createAudioDevice();
		} catch (JavaLayerException e) {
			e.printStackTrace();
		}
		AdvancedPlayer player = resource.getAdvancedPlayer(device);
		player.setPlayBackListener(listener);
		return player;
	}
}