package com.sanop.effect;

import com.sanop.Main;

import java.awt.*;
import java.awt.image.VolatileImage;

public class JitterEffect extends ScreenEffect {
	
	public static final boolean HORIZONTAL = true;
	public static final boolean VERTICAL = false;
	
	private double strength;
	private int start;
	private int length;
	private boolean direction;
	
	public JitterEffect (double strength, int start, int length, boolean direction) {
		this.strength = strength;
		this.start = start;
		this.length = length;
		this.direction = direction;
	}
	
	public double getStrength () {
		return strength;
	}
	public int getLength () {
		return length;
	}
	public int getStart () {
		return start;
	}
	public boolean getDirection () {
		return direction;
	}
	
	public void setStrength (double strength) {
		this.strength = strength;
	}
	public void setStart (int start) {
		this.start = start;
	}
	public void setLength (int length) {
		this.length = length;
	}
	public void setDirection (boolean direction) {
		this.direction = direction;
	}
	
	@Override
	public void apply (VolatileImage image) {
		
		if(strength < 1) return;
		
		updateImage();
		graphics.drawImage(image, 0, 0, null);
		Graphics2D g = (Graphics2D)image.getGraphics();
		
		for (int i = 0; i < length && i + start < (direction ? Main.SCREEN_HEIGHT : Main.SCREEN_WIDTH); i ++) {
			
			int amount = (int)((1 - random.nextDouble() * 2) * strength);
			
			if (amount == 0) continue;
			
			g.setClip(	direction ? 0 : (i + start),
						direction ? (i + start) : 0,
						direction ? Main.SCREEN_WIDTH : 1,
						direction ? 1 : Main.SCREEN_HEIGHT );
			
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
			
			g.drawImage(ScreenEffect.image,
						direction ? amount : 0,
						direction ? 0 : amount,
						null );
		}
		
		g.dispose();
		g.setClip(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
	}
	
	@Override
	public void updateProperties (Double[] values) {
		strength = values[0];
		
		if (values.length > 1) {
			start = values[1].intValue();
			length = values[2].intValue();
		}
	}
}
