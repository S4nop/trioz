package com.sanop.effect;

import java.awt.image.VolatileImage;
import java.util.ArrayList;
import java.util.Arrays;

public class ScreenEffectIterator extends ScreenEffect {
	
	public static final boolean AT_FIRST = false;
	public static final boolean AT_LAST = true;
	
	private ArrayList<ScreenEffect> iterator;
	
	public ScreenEffectIterator (ArrayList<ScreenEffect> effects) {
		iterator = effects;
	}
	
	public ScreenEffectIterator (ScreenEffect... args) {
		this(new ArrayList<>(Arrays.asList(args)));
	}
	
	@Override
	public void apply (VolatileImage image) {
		for (ScreenEffect elem : iterator)
			elem.apply(image);
	}
	
	@Override
	public void updateProperties (Double[] value) {
		// do not call this function on iterator.
	}
	
	public void addScreenEffect (ScreenEffect effect, boolean where) {
		if(where) iterator.add(effect);
		else iterator.add(0, effect);
	}
	
	public void removeScreenEffect (ScreenEffect effect) {
		if(iterator.contains(effect))
			iterator.remove(effect);
	}
}
