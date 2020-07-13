package com.sanop.platformer.entity;

import com.sanop.platformer.collision.AABBCollider;
import res.ImageResource;

import java.awt.*;

public class Block extends PlayerInteractive {
	
	public Block (int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.rotation = 0;
		this.collider = new AABBCollider();
		this.image = ImageResource.BLOCK_1.getImageIcon().getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
	}

	@Override
	public boolean interact (Player p) {
		double spX = p.getSpeedX(), spY = p.getSpeedY();
		
		// If the player is moved only vertically or horizontal area of this block covers the player's one,
		// it must be top-to-bottom collision.
		if ((x < p.getLeftX() && p.getRightX() < x + width) || spX == 0) {
			if (spY > 0) {
				p.setY(y - p.getSize());
				p.setJumped(0);
			} else {
				p.setY(y + height);
				p.setSpeedY(0);
			}

//			System.out.println("X Range");
			return false;
		}
		
		// If the player is moved only horizontally or vertical area of this block covers the player's one,
		// it must be left-to-right collision.
		if ((y < p.getTopY() && p.getBottomY() < y + height) || spY == 0) {
			if (spX > 0) p.setX(x - p.getSize());
			else p.setX(x + width);

//			System.out.println("Y Range");
			return false;
		}
		
		double deltaX = (spX > 0) ? (x - p.getRightX() + spX) : (x + width - p.getLeftX() + spX),
				deltaY = (spY > 0) ? (y - p.getBottomY() + spY) : (y + height - p.getTopY() + spY);
		
		if (deltaX / spX > deltaY / spY) {
			// left-to-right collision.
			if (spX > 0) p.setX(x - p.getSize());
			else p.setX(x + width);
		} else {
			// top-to-bottom collision.
			if (spY > 0) {
				p.setY(y - p.getSize());
				p.setJumped(0);
			}
			else p.setY(y + height);
			
			p.setSpeedY(0);
		}
		return false;
	}

}
