package com.sanop.init;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

public class RenderingHintInitializer {
	
	private RenderingHintInitializer () {}
	
	public static RenderingHints loadRenderingHints () {
		
		RenderingHints hints = new RenderingHints(null);
		File config = new File("trioz.cfg");
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(config));
			String line;
			while((line = reader.readLine()) != null) {
				RenderingHints.Key key;
				Object value;
				
				String[] keyValue = line.split(": ");
				
				switch (keyValue[0]) {
					case "text_antialias":
						key = RenderingHints.KEY_TEXT_ANTIALIASING;
						
						if (keyValue[1].equals("true"))
							value = RenderingHints.VALUE_TEXT_ANTIALIAS_ON;
						else
							value = RenderingHints.VALUE_TEXT_ANTIALIAS_OFF;
						break;
					
					case "interpolation":
						key = RenderingHints.KEY_INTERPOLATION;
						
						if (keyValue[1].equals("bicubic"))
							value = RenderingHints.VALUE_INTERPOLATION_BICUBIC;
						else if (keyValue[1].equals("bilinear"))
							value = RenderingHints.VALUE_INTERPOLATION_BILINEAR;
						else
							value = RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR;
						break;
					
					case "antialias":
						key = RenderingHints.KEY_ANTIALIASING;
						
						if (keyValue[1].equals("true"))
							value = RenderingHints.VALUE_ANTIALIAS_ON;
						else
							value = RenderingHints.VALUE_ANTIALIAS_OFF;
						break;
					
					case "render":
						key = RenderingHints.KEY_RENDERING;
						
						if (keyValue[1].equals("quality"))
							value = RenderingHints.VALUE_RENDER_QUALITY;
						else
							value = RenderingHints.VALUE_RENDER_SPEED;
						break;
					
					default:
						key = null;
						value = null;
				}
				
				hints.put(key, value);
			}
			
		} catch (Exception e) {
			
			hints.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			hints.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
			
			try {
				
				config.createNewFile();
				FileOutputStream fos = new FileOutputStream(config);
				
				fos.write( ("text_antialias: true\n" +
							"interpolation: bilinear\n" +
							"antialias: true\n" +
							"render: quality").getBytes() );
				fos.close();
				
			} catch (Exception exc) {
				exc.printStackTrace();
			}
		}
		
		return hints;
	}
}
