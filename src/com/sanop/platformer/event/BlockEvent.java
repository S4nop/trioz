package com.sanop.platformer.event;

import com.sanop.platformer.entity.Block;

import java.util.ArrayList;
import java.util.function.Function;

public class BlockEvent extends TickEvent {
	
	private Block block;
	private ArrayList<Block> list;
	
	public BlockEvent (int since, int duration, Function<Integer, Double[]> formula,
						ArrayList<Block> list, Block block) {
		super(since, duration, formula);
		this.block = block;
		this.list = list;
		list.add(block);
	}
	
	@Override
	public void process (Double[] value) {
		// null, block should NOT move.
	}
	
	
	@Override
	public void finish () {
		list.remove(block);
	}
}
