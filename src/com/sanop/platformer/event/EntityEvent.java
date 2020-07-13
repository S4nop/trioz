package com.sanop.platformer.event;

import com.sanop.platformer.entity.PlayerInteractive;

import java.util.ArrayList;
import java.util.function.Function;

public class EntityEvent extends TickEvent {
	
	private PlayerInteractive entity;
	private ArrayList<PlayerInteractive> list;
	
	public EntityEvent (int since, int duration, Function<Integer, Double[]> formula,
						ArrayList<PlayerInteractive> list, PlayerInteractive entity) {
		super(since, duration, formula);
		this.entity = entity;
		this.list = list;
	}
	
	@Override
	public void process (Double[] value) {
		entity.setX(value[0]);
		entity.setY(value[1]);
		entity.setRotation(value[2]);
	}

	@Override
	public void start () {
		list.add(entity);
	}
	
	@Override
	public void finish () {
		if(list.contains(entity))
			list.remove(entity);
	}
}
