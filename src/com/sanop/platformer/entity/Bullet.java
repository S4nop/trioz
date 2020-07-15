package com.sanop.platformer.entity;

import com.sanop.platformer.collision.OBBCollider;
import com.sanop.platformer.collision.SquareToCircleCollider;
import res.ImageResource;

import java.awt.*;

public class Bullet extends PlayerInteractive {

	public Bullet () {
		this.x = 0;
		this.y = 0;
		this.rotation = 0;
		this.width = 50;
		this.height = 50;
		this.collider = new SquareToCircleCollider();
		this.image = ImageResource.BULLET_ORB_2.getImageIcon().getImage().getScaledInstance((int)this.width, (int)this.height, Image.SCALE_SMOOTH);
	}

	@Override
	public boolean interact (Player p) {
		p.addHp(-10);
		return true;
	}
}
