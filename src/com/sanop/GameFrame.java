package com.sanop;

import com.sanop.activity.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.VolatileImage;

public class GameFrame extends JFrame {
	
	private static final long serialVersionUID = -711163588504124217L;
	
	private Activity activity;
	private final Object sync = new Object();
	
	public GameFrame () {
		setUndecorated(true);
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setFocusable(true);
		setLayout(null);
		setBackground(new Color(0, 0, 0, 0));
		
		Activity.init(this);
		addFocusListener(new FocusListener() {
			@Override
			public void focusGained (FocusEvent e) {
				activity.resume();
			}
			
			@Override
			public void focusLost (FocusEvent e) {
				activity.pause();
			}
		});
		
		activity = new MainActivity();
		setTitle(activity.getTitle());
		activity.start();
		
		for (JComponent comp : activity.getComponents())
			add(comp);

		setVisible(true);    // JFrame.paint is called after here.
		// so it must be called AFTER all members are initialized.
		
	}
	
	@Override
	public void paint (Graphics g) {
		VolatileImage img;
		synchronized (sync) {
			do {
				img = activity.getScreen();
				paintComponents(img.getGraphics());
				g.drawImage(img, 0, 0, null);
			} while (img.contentsLost());
		}
		
		repaint();
	}

	public void changeActivity (Activity newActivity) {
		
		requestFocus();
		
		synchronized (sync) {
			
			for (JComponent comp : activity.getComponents())
				remove(comp);
			activity.close();
			
			activity = newActivity;
			setTitle(activity.getTitle());
			activity.start();
			for (JComponent comp : activity.getComponents())
				add(comp);
		}
	}
}