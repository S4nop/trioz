package com.sanop.platformer.collision;

import static java.lang.Math.sqrt;

public class SquareToCircleCollider extends Collider {
	double chkX, chkY;
	@Override
	public boolean isCollided (Shape player, Shape s) {
		if (getCenterX(s) < getCenterX(player) - getLengthX(s)/2.0f)
			chkX = getCenterX(player) - getLengthX(s)/2.0f;
		else if ( getCenterX(s) > getCenterX(player) + getLengthX(s)/2.0f )
			chkX = getCenterX(player) + getLengthX(s)/2.0f;
		else
			chkX =  getCenterX(s) ;

		if (getCenterY(s) < getCenterY(player) - getLengthY(s)/2.0f)
			chkY = getCenterY(player) - getLengthY(s)/2.0f;
		else if ( getCenterY(s) > getCenterY(player) + getLengthY(s)/2.0f )
			chkY = getCenterY(player) + getLengthY(s)/2.0f;
		else
			chkY =  getCenterY(s);
		if(sqrt((chkY - getCenterY(s))*(chkY - getCenterY(s)) + (chkX - getCenterX(s))*(chkX - getCenterX(s))) < getLengthX(s)/2.0f) return true;
		return false;
	}

	double getCenterX(Shape s){ return (s.getLeftX() + s.getRightX()) / 2; }
	double getLengthX(Shape s){ return (s.getRightX() - s.getLeftX());}
	double getCenterY(Shape s){ return (s.getTopY() + s.getBottomY()) / 2; }
	double getLengthY(Shape s){ return (s.getBottomY() - s.getTopY());}

}
