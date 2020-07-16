package com.sanop.platformer.event;

import com.sanop.platformer.Engine;
import com.sanop.platformer.entity.*;

import javax.script.ScriptException;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.function.Function;

import static com.sanop.platformer.Utils.getTickByBeat;
import static com.sanop.platformer.Utils.toPlayerset;

public class EventBuffer {
    public enum bufType{
        BLOCK_EVENT,
        ENTITY_EVENT,
        GRAVITY_EVENT,
        INPUT_EVENT,
        SCREEN_EFFECT_EVENT,
        SPEED_EVENT,
        F_BULLET_EVENT,
        BULLET_EVENT,
        LASER_EVENT,
        FIREBALL_EVENT,
        GHOST_EVENT
    }

    private String[] inp;
    private bufType type;
    ScriptEngineManager mgr = new ScriptEngineManager();
    ScriptEngine sEngine = mgr.getEngineByName("JavaScript");

    public EventBuffer(bufType type, String[] inp){
        this.type = type;
        this.inp = inp;
        sEngine.put("rand", Math.random());
    }

    public BlockEvent makeBlockEvent(Engine engine){
        //1 : since, 2 : until, 3 : x, 4 : y, 5 : width, 6 : height
        return new BlockEvent(Integer.parseInt(inp[1]), Integer.parseInt(inp[2]), (Integer integer) -> {return null;}, engine.getBlocks(),
                new Block(Integer.parseInt(inp[3]), Integer.parseInt(inp[4]), Integer.parseInt(inp[5]), Integer.parseInt(inp[6])));
    }

    public EntityEvent makeFBulletEvent(Engine engine){
       return new EntityEvent(getTickByBeat(Double.parseDouble(inp[1])), Integer.parseInt(inp[2]), new Formula(){
           @Override
           public Double[] apply(Integer integer) {
               try {
                    if(integer == 0)
                        initialize(toPlayerset((Double)sEngine.eval(inp[3]), (Double)sEngine.eval(inp[4]), engine));

                    Double[] vec = (Double[])getInit();
                    sEngine.put("vec_x", vec[0]);
                    sEngine.put("vec_y", vec[1]);
                    sEngine.put("vec_r", vec[2]);
                    sEngine.put("integer", integer);
                    sEngine.put("aRand", Math.random());
                    Double[] res = {(Double)sEngine.eval(inp[5]), (Double)sEngine.eval(inp[6]), (Double)sEngine.eval(inp[7])};
                    return res;
               }catch(ScriptException e){ System.out.println(e.getMessage()); return null; }
           }
       }, engine.getEntities(), new Bullet());
    }

    public EntityEvent makeNormalEntityEvent(Engine engine, bufType type){
        PlayerInteractive entity;
        if(type == bufType.LASER_EVENT) entity = new Laser();
        else if(type == bufType.BULLET_EVENT) entity = new Bullet();
        else if(type == bufType.FIREBALL_EVENT) entity = new Fireball();
        else if(type == bufType.GHOST_EVENT) entity = new Ghost();
        else return null;
        return new EntityEvent(getTickByBeat(Double.parseDouble(inp[1])), Integer.parseInt(inp[2]), (Integer integer) -> {
            try {
                sEngine.put("integer", integer);
                sEngine.put("aRand", Math.random());
                Double[] res = {(Double)sEngine.eval(inp[3]), (Double)sEngine.eval(inp[4]), (Double)sEngine.eval(inp[5])};
                return res;
            }catch(ScriptException e) { System.out.println(e.getMessage()); return null; }
        }, engine.getEntities(), entity);
    }
    public bufType getType(){
        return this.type;
    }
}
