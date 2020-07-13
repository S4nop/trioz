package com.sanop.platformer.event;

import java.util.function.Function;

abstract class TickEvent {
	private int since, duration;
	private Function<Integer, Double[]> formula;
	
	protected TickEvent (int since, int duration, Function<Integer, Double[]> formula) {
		this.since = since;
		this.duration = duration;
		this.formula = formula;
	}
	
	public int getSince () {
		return since;
	}
	
	public boolean update (int tick) {
		int elapsed = tick - since;
		
		process(formula.apply(elapsed));
		
		return elapsed >= duration;
	}
	
	public void start() {
		// nothing
	}
	public void finish () {
		// null
	}
	
	protected abstract void process(Double[] value);
}
