package com.sanop.platformer.entity;

import com.sanop.platformer.collision.SquareToCircleCollider;
import res.ImageResource;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Ghost extends PlayerInteractive{

    private Image wing;
    
    public Ghost (int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.collider = new SquareToCircleCollider();
        this.wing = ImageResource.GHOST_2.getImageIcon().getImage().getScaledInstance(3*(width - 5), 3*(height - 5),Image.SCALE_FAST);
        //this.image = ImageResource.GHOSTTMP.getImageIcon().getImage().getScaledInstance(width ,height,Image.SCALE_SMOOTH);
    }

    @Override
    public boolean interact (Player p) {
        p.addHp(-10);
        return true;
    }

    @Override
    public void render (Graphics2D g) {
        super.render(g);

            AffineTransform t = new AffineTransform();
            t.translate(x - width , y - height);
            g.drawImage(wing, t, null);
    }

}
