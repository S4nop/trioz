package com.sanop.effect;

import com.sanop.Main;

import java.awt.*;
import java.awt.image.VolatileImage;
import java.util.Random;

public abstract class ScreenEffect {
	protected static VolatileImage image;
	protected static Graphics2D graphics;
	protected static Random random;
	
	private static GraphicsConfiguration config;
	
	public static void init (GraphicsConfiguration c) {
		config = c;
		random = new Random();
		createImage();
	}
	
	protected static void createImage () {
		image = config.createCompatibleVolatileImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
	}
	
	protected static void updateImage () {
		if (image.validate(config) == VolatileImage.IMAGE_INCOMPATIBLE)
			createImage();
		graphics = image.createGraphics();
	}
	
	public static void setSeed (int seed) {
		random = new Random(seed);
		random.nextDouble();
	}
	
	public abstract void apply (VolatileImage image);
	
	public abstract void updateProperties (Double[] value);
}
