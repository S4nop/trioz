package com.sanop.platformer.entity;

import com.sanop.platformer.collision.OBBCollider;
import res.ImageResource;

import java.awt.*;

public class Laser extends PlayerInteractive{
    private boolean used = true;
    public Laser(){
        this.x = 0;
        this.y = 0;
        this.width = 150;
        this.height = 1300;
        this.collider = new OBBCollider();
        this.image = ImageResource.LASER_R.getImageIcon().getImage().getScaledInstance((int)this.width * 5, (int)this.height, Image.SCALE_FAST);
    }

    @Override
    public boolean interact(Player player) {
        player.addHp(-5);
        return false;
    }
    
    @Override
    public void render (Graphics2D g) {
    	double sin = Math.sin(Math.toRadians(rotation)), cos = Math.cos(Math.toRadians(rotation));
		x -= width * 2 * cos;
		y -= width * 2 * sin;
        super.render(g);
        x += width * 2 * cos;
		y += width * 2 * sin;
    }
}
