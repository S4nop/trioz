package com.sanop.platformer.collision;

import com.sanop.platformer.entity.Player;

public class OBBCollider extends Collider {
	
	@Override
	public boolean isCollided (Shape player, Shape s2) {
		
		double rad = Math.toRadians(s2.getRotation());
		double sin = Math.sin(rad), cos =  Math.cos(rad);
		double w = s2.getRightX() - s2.getLeftX(), h = s2.getBottomY() - s2.getTopY();
		double size = ((Player) player).getSize();
		double relativeX = s2.getLeftX() - player.getLeftX(), relativeY = s2.getTopY() - player.getTopY();
		double[][] points = {
								{relativeX, relativeY},
								{relativeX + w * cos, relativeY + w * sin},
								{relativeX - h * sin, relativeY + h * cos},
								{relativeX + w * cos - h * sin, relativeY + w * sin + h * cos}
							};
		
		double minX = points[0][0], minY = points[0][1], maxX = points[0][0], maxY = points[0][1];
		
		for (double[] point : points) {
			if(minX > point[0]) minX = point[0];
			if(minY > point[1]) minY = point[1];
			if(maxX < point[0]) maxX = point[0];
			if(maxY < point[1]) maxY = point[1];
		}
		
		if ((minX < 0 ? 0 : minX) > (size < maxX ? size : maxX) ||
			(minY < 0 ? 0 : minY) > (size < maxY ? size : maxY))
			return false;
		
		sin = -sin;
		relativeX = -relativeX;
		relativeY = -relativeY;
		
		points[0][0] = relativeX * cos - relativeY * sin;
		points[0][1] = relativeX * sin + relativeY * cos;
		
		relativeX += size;
		
		points[1][0] = relativeX * cos - relativeY * sin;
		points[1][1] = relativeX * sin + relativeY * cos;
		
		relativeX -= size;
		relativeY += size;
		
		points[2][0] = relativeX * cos - relativeY * sin;
		points[2][1] = relativeX * sin + relativeY * cos;
		
		relativeX += size;
		
		points[3][0] = relativeX * cos - relativeY * sin;
		points[3][1] = relativeX * sin + relativeY * cos;
		
		for (double[] point : points) {
			if(minX > point[0]) minX = point[0];
			if(minY > point[1]) minY = point[1];
			if(maxX < point[0]) maxX = point[0];
			if(maxY < point[1]) maxY = point[1];
		}
		
		if ((minX < 0 ? 0 : minX) > (w < maxX ? w : maxX) ||
			(minY < 0 ? 0 : minY) > (h < maxY ? h : maxY))
			return false;
		
		return true;
	}
}
