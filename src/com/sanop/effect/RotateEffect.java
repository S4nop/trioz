package com.sanop.effect;

import com.sanop.Main;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.VolatileImage;

public class RotateEffect extends ScreenEffect {
	
	private double rotation;
	private double pivotX;
	private double pivotY;
	
	public RotateEffect (double rotation, double pivotX, double pivotY) {
		this.rotation = rotation;
		this.pivotX = pivotX;
		this.pivotY = pivotY;
	}
	
	public RotateEffect (double rotation) {
		this(rotation, Main.SCREEN_WIDTH / 2, Main.SCREEN_HEIGHT / 2);
	}
	
	public double getRotation () {
		return rotation;
	}
	public double getPivotX () {
		return pivotX;
	}
	public double getPivotY () {
		return pivotY;
	}
	
	public void setRotation (double rotation) {
		this.rotation = rotation;
	}
	public void setPivotX (double pivotX) {
		this.pivotX = pivotX;
	}
	public void setPivotY (double pivotY) {
		this.pivotY = pivotY;
	}
	
	@Override
	public void apply (VolatileImage image) {
		
		if(rotation == 0) return;
		
		updateImage();
		ScreenEffect.graphics.drawImage(image, 0, 0, null);

		AffineTransform transform = new AffineTransform();
		transform.translate(pivotX, pivotY);
		transform.rotate(rotation * Math.PI / 180);
		transform.translate(-pivotX, -pivotY);

		Graphics2D g = (Graphics2D)image.getGraphics();
		
		g.setRenderingHints(Main.getRenderingHint());
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		
		g.drawImage(ScreenEffect.image, transform, null);
		
		g.dispose();
	}
	
	@Override
	public void updateProperties (Double[] values) {
		rotation = values[0];
		
		if(values.length > 1) {
			pivotX = values[1];
			pivotY = values[2];
		}
	}
}