package com.sanop.platformer.entity;

import com.sanop.platformer.collision.OBBCollider;
import res.ImageResource;

import java.awt.*;

public class Laser extends PlayerInteractive{
    private boolean used = true;
    public Laser(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.collider = new OBBCollider();
        this.image = ImageResource.LASER_R.getImageIcon().getImage().getScaledInstance(width * 5, height, Image.SCALE_FAST);
    }

    @Override
    public boolean interact(Player player) {
        if(used) {
            player.addHp(-10);
            used = false;
        }
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
