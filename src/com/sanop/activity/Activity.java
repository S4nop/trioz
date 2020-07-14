package com.sanop.activity;

import com.sanop.GameFrame;
import com.sanop.Main;
import com.sanop.effect.ScreenEffect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.image.VolatileImage;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public abstract class Activity {
	
	private static GameFrame frame;
	
	private boolean paused = false;
	
	protected static GraphicsConfiguration config;
	
	protected String title;
	protected VolatileImage image;
	protected Graphics2D graphics;
	protected final ArrayList<JComponent> components = new ArrayList<>();
	protected Object param;
	
	public static void init (GameFrame f) {
		frame = f;
		config = frame.getGraphicsConfiguration();
		ScreenEffect.init(config);
	}
	public void syncState (Activity target) {
	}
	
	public String getTitle () {
		return title;
	}
	public ArrayList<JComponent> getComponents () {
		return components;
	}
	
	public final void pause () {
		if(!paused) {
			paused = true;
			onPause();
		}
	}
	public final void resume () {
		if(paused) {
			paused = false;
			onResume();
		}
	}
	
	public abstract void start ();
	public abstract void close ();
	public abstract VolatileImage getScreen();
	
	protected void onPause () {
		// do nothing.
	}
	protected void onResume () {
		// do nothing
	}
	protected boolean isPaused () {
		return paused;
	}
	
	protected final void createImage () {
		image = config.createCompatibleVolatileImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
	}
	protected final void updateImage () {
		if (image.validate(config) == VolatileImage.IMAGE_INCOMPATIBLE)
			createImage();
		graphics = image.createGraphics();
	}
	protected final void requestActivityChange (Class<? extends Activity> target) {
		Activity newActivity = null;
		
		try {
			newActivity = target.getConstructor().newInstance();
			syncState(newActivity);
		} catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
			System.out.println("Invalid Request : " + e.getMessage());
		}
		
		frame.changeActivity(newActivity);
	}
	
	protected void addButton (JButton btn, int x, int y, int width, int height, MouseAdapter adapter){
		btn.setBounds(x, y, width, height);
		btn.setBorderPainted(false);
		btn.setContentAreaFilled(false);
		btn.setFocusPainted(false);
		btn.addMouseListener(adapter);
		btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		components.add(btn);
	}

	public void setParam(Object param){
		this.param = param;
	}

	public Object getParam(){ return this.param; }
}
