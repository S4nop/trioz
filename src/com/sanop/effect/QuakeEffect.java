package com.sanop.effect;

import com.sanop.Main;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.VolatileImage;

public class QuakeEffect extends ScreenEffect {
	
	private double strengthX;
	private double strengthY;
	
	public QuakeEffect (double strengthX, double strengthY) {
		this.strengthX = strengthX;
		this.strengthY = strengthY;
	}
	
	public QuakeEffect (double strength) {
		this(strength, strength);
	}
	
	public double getStrengthX () {
		return strengthX;
	}
	public double getStrengthY () {
		return strengthY;
	}
	
	public void setStrengthX (double strengthX) {
		this.strengthX = strengthX;
	}
	public void setStrengthY (double strengthY) {
		this.strengthY = strengthY;
	}
	
	@Override
	public void apply (VolatileImage image) {
		
		if(strengthX < 1 && strengthY < 1) return;
		
		updateImage();
		graphics.drawImage(image, 0, 0, null);
		
		AffineTransform transform = new AffineTransform();
		transform.translate((1 - random.nextDouble() * 2) * strengthX, (1 - random.nextDouble() * 2) * strengthY);
		
		Graphics2D g = (Graphics2D)image.getGraphics();
		
		g.setRenderingHints(Main.getRenderingHint());
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		
		g.drawImage(ScreenEffect.image, transform, null);
		
		g.dispose();
	}
	
	@Override
	public void updateProperties (Double[] values) {
		strengthX = values[0];
		strengthY = values[values.length - 1];
	}
}
