package com.sanop.platformer.event;

import com.sanop.platformer.Engine;

import java.util.function.Function;

public class SpeedEvent extends TickEvent{
	
	private Engine engine;
	
	public SpeedEvent (int since, int duration, Function<Integer, Double[]> formula, Engine engine) {
		super(since, duration, formula);
		this.engine = engine;
	}
	
	@Override
	public void process (Double[] value) {
		if(value[0] < 0) throw new IllegalArgumentException("Scale of engine cannot be negative.");
		engine.setSpeed(value[0]);
	}
	
	@Override
	public void finish () {
		engine.setSpeed(1);
	}
}
