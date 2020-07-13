package com.sanop.platformer.event;

import com.sanop.effect.ScreenEffect;
import com.sanop.effect.ScreenEffectIterator;

import java.util.function.Function;

public class ScreenEffectEvent extends TickEvent {
	
	private ScreenEffect effect;
	private ScreenEffectIterator effects;
	private boolean where;
	
	public ScreenEffectEvent (int since, int duration, Function<Integer, Double[]> formula,
							  ScreenEffectIterator effects, ScreenEffect effect, boolean where) {
		super(since, duration, formula);
		this.effect = effect;
		this.effects = effects;
		this.where = where;
	}
	
	@Override
	public void start () {
		effects.addScreenEffect(effect, where);
	}
	
	@Override
	public void process (Double[] value) {
		effect.updateProperties(value);
	}
	
	@Override
	public void finish () {
		effects.removeScreenEffect(effect);
	}
}
