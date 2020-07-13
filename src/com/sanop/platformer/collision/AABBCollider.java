package com.sanop.platformer.collision;

public class AABBCollider extends Collider {
	@Override
	public boolean isCollided (Shape s1, Shape s2) {
		if (s1.getRotation() != 0 || s2.getRotation() != 0)
			throw new IllegalArgumentException("AABB Collider cannot handle rotated shape.");
		
		double  pivotLeft = (s1.getLeftX() > s2.getLeftX()) ? s1.getLeftX() : s2.getLeftX(),
				pivotRight = (s1.getRightX() < s2.getRightX()) ? s1.getRightX() : s2.getRightX(),
				pivotTop = (s1.getTopY() > s2.getTopY()) ? s1.getTopY() : s2.getTopY(),
				pivotBottom = (s1.getBottomY() < s2.getBottomY()) ? s1.getBottomY() : s2.getBottomY();
		
		return pivotLeft < pivotRight && pivotTop < pivotBottom;
	}
}
