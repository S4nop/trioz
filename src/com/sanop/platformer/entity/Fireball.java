package com.sanop.platformer.entity;

import com.sanop.platformer.collision.SquareToCircleCollider;
import res.ImageResource;

import java.awt.*;

import static java.lang.Math.abs;

public class Fireball extends PlayerInteractive{

    public Fireball () {
        this.x = 0;
        this.y = 0;
        this.rotation = 0;
        this.width = 100;
        this.height = 100;
        this.collider = new SquareToCircleCollider();
        this.image = ImageResource.FIREBALL.getImageIcon().getImage().getScaledInstance(100,100, Image.SCALE_FAST);
    }

    @Override
    public boolean interact (Player p) {
        p.addHp(-10);
        return true;
    }
}
