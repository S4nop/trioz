package com.sanop.effect;

import com.sanop.Main;

import java.awt.*;
import java.awt.image.VolatileImage;

public class TextOverlayEffect extends ScreenEffect {
	
	private double x;
	private double y;
	private String text;
	private Font font;
	private Color color;
	
	public TextOverlayEffect (double x, double y, String text, Font font, Color color) {
		this.x = x;
		this.y = y;
		this.text = text;
		this.font = font;
		this.color = color;
	}
	
	public double getX () {
		return x;
	}
	public double getY () {
		return y;
	}
	public String getText () {
		return text;
	}
	public Font getFont () {
		return font;
	}
	public Color getColor () {
		return color;
	}
	
	public void setX (double x) {
		this.x = x;
	}
	public void setY (double y) {
		this.y = y;
	}
	public void setText (String text) {
		this.text = text;
	}
	public void setFont (Font font) {
		this.font = font;
	}
	public void setColor (Color color) {
		this.color = color;
	}
	
	@Override
	public void apply (VolatileImage image) {
		if(text.isEmpty()) return;
		
		Graphics2D g = (Graphics2D) image.getGraphics();
		
		g.setRenderingHints(Main.getRenderingHint());
		g.setColor(color);
		g.setFont(font);
		
		g.drawString(text, (float)x, (float)y);
		
		g.dispose();
	}
	
	@Override
	public void updateProperties (Double[] values) {
		x = values[0];
		y = values[1];
		
		if (values.length > 2)
			color = new Color(values[2].intValue(), true);
	}
}
