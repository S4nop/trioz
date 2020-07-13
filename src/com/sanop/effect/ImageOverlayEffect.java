package com.sanop.effect;

import com.sanop.Main;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.VolatileImage;

public class ImageOverlayEffect extends ScreenEffect {
	
	private double x;
	private double y;
	private Image image;
	private double opacity;
	private double rotation;
	
	public ImageOverlayEffect (double x, double y, double rotation, Image image, double opacity) {
		this.x = x;
		this.y = y;
		this.image = image;
		this.opacity = opacity;
		this.rotation = rotation;
	}
	
	public ImageOverlayEffect (double x, double y, double rotation, Image image) {
		this(x, y, rotation, image, 1);
	}
	
	public ImageOverlayEffect (double x, double y, Image image) {
		this(x, y, 0, image, 1);
	}
	
	public ImageOverlayEffect (double x, double y, Image image, double opacity) {
		this(x, y, 0, image, opacity);
	}
	
	public double getX () {
		return x;
	}
	public double getY () {
		return y;
	}
	public Image getImage () {
		return image;
	}
	public double getOpacity () {
		return opacity;
	}
	
	public void setX (double x) {
		this.x = x;
	}
	public void setY (double y) {
		this.y = y;
	}
	public void setImage (Image image) {
		this.image = image;
	}
	public void setOpacity (double opacity) {
		this.opacity = opacity;
	}
	
	@Override
	public void apply (VolatileImage image) {
		
		if(opacity == 0) return;
		
		Graphics2D g = (Graphics2D) image.getGraphics();
		
		if(opacity != 1)
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float)opacity));
		
		g.setRenderingHints(Main.getRenderingHint());
		
		AffineTransform transform = new AffineTransform();
		
		transform.rotate(rotation / 180 * Math.PI);
		transform.translate(x, y);
		
		g.drawImage(this.image, transform, null);
		
		g.dispose();
	}
	
	@Override
	public void updateProperties (Double[] values) {
		x = values[0];
		y = values[1];
		if(values.length > 2) rotation = values[2];
		if(values.length > 3) opacity = values[3];
	}
}
