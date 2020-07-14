package com.sanop.activity;

import com.sanop.Main;
import com.sanop.effect.*;
import com.sanop.music.AdvancedMusicPlayer;
import com.sanop.key.Key;
import com.sanop.key.KeyStatus;
import com.sanop.platformer.Engine;
import com.sanop.platformer.Map;
import com.sanop.platformer.Synchronizer;
import com.sanop.platformer.event.EventManager;
import res.ImageResource;
import res.MapResource;
import res.SoundResource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.VolatileImage;

public class PlatformerActivity extends Activity {
	
	private Image bgImage;
	private Image hpBar;
	private Image shieldIco;

	private Map map;
	private Engine engine;
	private EventManager manager;
	private AdvancedMusicPlayer bgm;
	private ScreenEffectIterator effects;
	private Synchronizer sync;
	
	private ImageOverlayEffect pauseEffect;
	private ImageOverlayEffect gameoverEffect;
	
	private JButton resume;
	private JButton restart;
	private JButton stop;
	private MouseAdapter resumeAdapter;
	private MouseAdapter restartAdapter;
	private MouseAdapter stopAdapter;
	
	private boolean paused;
	
	public PlatformerActivity () {
		
		title = "Platformer Activity";
		engine = new Engine();
		effects = new ScreenEffectIterator();
		manager = new EventManager(engine, effects);
		map = MapResource.TestMap.getMapData().setManager(manager);
		//map = ((Map)getParam()).init(engine, effects);
		bgImage = map.getBgImage();
		bgm = map.getBgm();

		pauseEffect = new ImageOverlayEffect(0, 0, ImageResource.PAUSE_OVERLAY.getImageIcon().getImage(), 0);
		gameoverEffect = new ImageOverlayEffect(0, 0, ImageResource.GAMEOVER.getImageIcon().getImage(), 0);

		resume = new JButton(ImageResource.RESUME_BUTTON.getImageIcon(160, 160));
		restart = new JButton(ImageResource.RESTART_BUTTON.getImageIcon(160, 160));
		stop = new JButton(ImageResource.STOP_BUTTON.getImageIcon(160, 160));
		
		hpBar = ImageResource.HPBAR
				.getImageIcon()
				.getImage()
				.getScaledInstance(26, 400, Image.SCALE_FAST);
		shieldIco = ImageResource.SHIELDICO
				.getImageIcon()
				.getImage()
				.getScaledInstance(30, 30 , Image.SCALE_FAST);

		resumeAdapter = new MouseAdapter() {
			@Override
			public void mouseClicked (MouseEvent e) {
				super.mouseClicked(e);
				resumeGame();
			}
		};
		restartAdapter = new MouseAdapter() {
			@Override
			public void mouseClicked (MouseEvent e) {
				super.mouseClicked(e);
				requestActivityChange(PlatformerActivity.class);
			}
		};
		stopAdapter = new MouseAdapter() {
			@Override
			public void mouseClicked (MouseEvent e) {
				super.mouseClicked(e);
				requestActivityChange(MainActivity.class);
			}
		};
		
		paused = false;
	}
	
	@Override
	public void start () {
		sync = new Synchronizer(engine, bgm, effects, manager);
		bgm.play(()->requestActivityChange(MainActivity.class));
		createImage();
		
		resume.setVisible(false);
		restart.setVisible(false);
		stop.setVisible(false);
		
		addButton(resume, Main.SCREEN_WIDTH / 2 - 80,Main.SCREEN_HEIGHT / 2 + 180, 160, 160, resumeAdapter);
		addButton(restart, Main.SCREEN_WIDTH / 2 - 320,Main.SCREEN_HEIGHT / 2 + 180, 160, 160, restartAdapter);
		addButton(stop, Main.SCREEN_WIDTH / 2 + 160,Main.SCREEN_HEIGHT / 2 + 180, 160, 160, stopAdapter);
	}
	
	@Override
	public void close () {
		bgm.stop();
		
		resume.removeMouseListener(resumeAdapter);
		restart.removeMouseListener(restartAdapter);
		stop.removeMouseListener(stopAdapter);
	}
	
	@Override
	protected void onPause () {
		if (!paused) pauseGame();
	}
	
	@Override
	protected void onResume () {
	}
	
	@Override
	public VolatileImage getScreen () {
		
		if(KeyStatus.isKeyJustPressed(Key.ESCAPE)) {
			KeyStatus.setKeyProcessed(Key.ESCAPE);
			if(paused) resumeGame();
			else pauseGame();
		}

		if(engine.getPlayerHp() <= 0){
			gameover();
		}

		updateImage();
		
		graphics.setRenderingHints(Main.getRenderingHint());
		
		// Pre-renderer goes here
		graphics.drawImage(bgImage, 0, 0, null);
		
		// Engine renders here
		sync.update();
		engine.render(graphics);
		
		// Post-renderer goes here
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, Main.SCREEN_WIDTH * bgm.getTime() / bgm.getLength(), 3);
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 3, Main.SCREEN_WIDTH * bgm.getTime() / bgm.getLength(), 1);

		effects.apply(image);
		
		AffineTransform hb = new AffineTransform();
		hb.translate(1235, 200);
		
		graphics.drawImage(ImageResource.HP.getImageIcon().getImage(), 1237, 199 + 4 * (100 - engine.getPlayerHp()), 24, engine.getPlayerHp() * 4 + 1, null);
		graphics.drawImage(hpBar, hb,null);
		
		for(int i = 0; i < engine.getPlayerSheilds(); i++){
			AffineTransform sd = new AffineTransform();
			sd.translate(10 + 30 * i, 10);
			graphics.drawImage(shieldIco, sd,null);
		}
		
		pauseEffect.apply(image);
		gameoverEffect.apply(image);
		
		graphics.dispose();
		
		return image;
	}
	public void gameover(){
		bgm.stop();
		paused = true;

		restart.setVisible(true);
		stop.setVisible(true);

		gameoverEffect.setOpacity(1);
	}
	private void pauseGame () {
		bgm.pause();
		paused = true;
		
		resume.setVisible(true);
		restart.setVisible(true);
		stop.setVisible(true);
		
		pauseEffect.setOpacity(1);
	}
	
	private void resumeGame () {
		bgm.resume();
		paused = false;
		
		resume.setVisible(false);
		restart.setVisible(false);
		stop.setVisible(false);
		
		pauseEffect.setOpacity(0);
	}
}
