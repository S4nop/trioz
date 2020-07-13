package res;

import java.awt.*;
import java.io.File;

public enum FontResource {
	
	PACIFITO(Font.TRUETYPE_FONT, "Pacifico.ttf"),
	BLACK_HAN_SANS(Font.TRUETYPE_FONT, "BlackHanSans.ttf"),
	PASSION_ONE(Font.TRUETYPE_FONT, "PassionOne.ttf");
	
	private Font font;
	private static GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
	
	FontResource (int type, String name) {
		try {
			font = Font.createFont(type, new File(getClass().getResource("fonts/" + name).getFile()));
		} catch (Exception e) {
			e.printStackTrace();
			font = null;
		}
	}
	
	public Font getFont (int style, float size) {
		return font.deriveFont(style, size);
	}
	
	public static void registerFonts () {
		for (FontResource font : FontResource.values())
			env.registerFont(font.font);
	}
}
