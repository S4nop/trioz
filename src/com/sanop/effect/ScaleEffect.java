package com.sanop.effect;

import com.sanop.Main;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.VolatileImage;

public class ScaleEffect extends ScreenEffect {
	
	private double scaleX;
	private double scaleY;
	private double pivotX;
	private double pivotY;
	
	public ScaleEffect (double scaleX, double scaleY, double pivotX, double pivotY) {
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		this.pivotX = pivotX;
		this.pivotY = pivotY;
	}
	
	public ScaleEffect (double scaleX, double scaleY) {
		this(scaleX, scaleY, Main.SCREEN_WIDTH / 2., Main.SCREEN_HEIGHT / 2.);
	}
	
	public double getScaleX () {
		return scaleX;
	}
	public double getScaleY () {
		return scaleY;
	}
	public double getPivotX () {
		return pivotX;
	}
	public double getPivotY () {
		return pivotY;
	}
	
	public void setScaleX (double scaleX) {
		this.scaleX = scaleX;
	}
	public void setScaleY (double scaleY) {
		this.scaleY = scaleY;
	}
	public void setScale (double scale) {
		this.scaleX = this.scaleY = scale;
	}
	public void setPivotX (double pivotX) {
		this.pivotX = pivotX;
	}
	public void setPivotY (double pivotY) {
		this.pivotY = pivotY;
	}
	
	@Override
	public void apply (VolatileImage image) {
		
		if(scaleX == 1 && scaleY == 1) return;
		
		updateImage();
		graphics.drawImage(image, 0, 0, null);
		
		AffineTransform transform = new AffineTransform();
		transform.scale(scaleX, scaleY);
		transform.translate(((1 - scaleX) * pivotX / scaleX), ((1 - scaleY) * pivotY / scaleY));
		
		Graphics2D g = (Graphics2D)image.getGraphics();
		
		g.setRenderingHints(Main.getRenderingHint());
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		
		g.drawImage(ScreenEffect.image, transform, null);
		
		g.dispose();
	}
	
	@Override
	public void updateProperties (Double[] values) {
		scaleX = values[0];
		scaleY = values[(values.length > 1) ? 1 : 0];
		
		if(values.length > 2) {
			pivotX = values[2];
			pivotY = values[3];
		}
	}
}
