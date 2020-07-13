package com.sanop.key;

import java.awt.event.KeyEvent;

public enum Key {
	LEFT(KeyEvent.VK_LEFT),
	RIGHT(KeyEvent.VK_RIGHT),
	UP(KeyEvent.VK_UP),
	DOWN(KeyEvent.VK_DOWN),
	SPACE(KeyEvent.VK_SPACE),
	S(KeyEvent.VK_S),
	ESCAPE(KeyEvent.VK_ESCAPE);
	
	
	private final int keyCode;
	
	Key (int keyCode) {
		this.keyCode = keyCode;
	}
	
	public static Key getKey (int keyCode) {
		for (Key key : Key.values())
			if (key.keyCode == keyCode) return key;
		
		return null;
	}
	
	public int getKeyCode () {
		return keyCode;
	}
}