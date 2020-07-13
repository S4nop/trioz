package com.sanop;

import com.sanop.init.RenderingHintInitializer;
import com.sanop.key.KeyStatus;
import res.FontResource;

import java.awt.*;

public class Main {
	
	// global constants goes here
	
	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;
	private static RenderingHints RENDERING_HINT;
	
	public static void main (String[] args) {
		
		// Hardware Acceleration
		System.setProperty("sun.java2d.opengl", "true");
		
		// Load Rendering Setting
		RENDERING_HINT = RenderingHintInitializer.loadRenderingHints();
		
		// Initialize
		FontResource.registerFonts();
		KeyStatus.init();
		
		// Create a JFrame
		GameFrame frame = new GameFrame();
		
		// Register JFrame object
		KeyStatus.register(frame);
	}
	
	public static RenderingHints getRenderingHint () {
		return RENDERING_HINT;
	}
}
