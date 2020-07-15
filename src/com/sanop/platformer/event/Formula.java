package com.sanop.platformer.event;

import java.util.function.Function;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public abstract class Formula implements Function <Integer,Double[]> {
	
	private Object init;
	ScriptEngineManager mgr = new ScriptEngineManager();
	ScriptEngine sEngine = mgr.getEngineByName("JavaScript");

	public Formula(){
		sEngine.put("rand", Math.random());
	}

	public abstract Double[] apply (Integer tick);
	
	public void initialize (Object init) {
		this.init = init;
	}
	
	public Object getInit () {
		return init;
	}
}
