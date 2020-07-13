package com.sanop.platformer.entity;

import com.sanop.component.ImageComponent;
import com.sanop.platformer.collision.Shape;

public abstract class Entity extends ImageComponent implements Shape {
	
	protected double width;
	protected double height;
	
	@Override
	public double getLeftX () {
		return x;
	}
	
	@Override
	public double getRightX () {
		return x + width;
	}
	
	@Override
	public double getTopY () {
		return y;
	}
	
	@Override
	public double getBottomY () {
		return y + height;
	}
	
	@Override
	public double getRotation () {
		return rotation;
	}

	public void setX (double x) {
		this.x = x;
	}
	public void setY (double y) {
		this.y = y;
	}
	public void setRotation (double rotation) {
		this.rotation = rotation;
	}
}
