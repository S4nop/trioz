package com.sanop.platformer.event;

import java.util.function.Function;

public abstract class Formula implements Function <Integer,Double[]> {
	
	private Object init;
	
	public abstract Double[] apply (Integer tick);
	
	public void initialize (Object init) {
		this.init = init;
	}
	
	public Object getInit () {
		return init;
	}
}
