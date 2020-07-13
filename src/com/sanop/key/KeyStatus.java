package com.sanop.key;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;

public final class KeyStatus {
	
	private static boolean isInitialized = false;
	private static HashMap<Key, Integer> status;
	
	private KeyStatus () {
		// none
	}
	
	public static void init () {
		if (isInitialized) throw new IllegalStateException("KeyStatus already initialized");
		
		isInitialized = true;
		status = new HashMap<>();
		for (Key k : Key.values())
			status.put(k, 0);
	}
	
	public static void register (JFrame frame) {
		
		JComponent component = frame.getRootPane();
		
		for (Key key : Key.values()) {
			component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
					KeyStroke.getKeyStroke(key.getKeyCode(), 0), key.toString() + " pressed");
			
			component.getActionMap().put(key.toString() + " pressed", new AbstractAction() {
				@Override
				public void actionPerformed (ActionEvent e) {
					if (status.get(key) == 0) status.put(key, 2);
				}
			});
			
			component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
					KeyStroke.getKeyStroke(key.getKeyCode(), 0, true), key.toString() + " released");
			
			component.getActionMap().put(key.toString() + " released", new AbstractAction() {
				@Override
				public void actionPerformed (ActionEvent e) {
					status.put(key, 0);
				}
			});
		}
	}
	
	public static boolean isKeyPressed (Key key) {
		return isInitialized && status.get(key) > 0;
	}
	public static boolean isKeyJustPressed (Key key) {
		return isInitialized && status.get(key) == 2;
	}
	
	public static void setKeyProcessed (Key key) {
		if (isInitialized && status.get(key) != 0) status.put(key, 1);
	}
}
