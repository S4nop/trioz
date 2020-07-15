package com.sanop.platformer.event;

import com.sanop.platformer.Engine;
import com.sanop.platformer.entity.Block;
import com.sanop.platformer.entity.Bullet;

import javax.script.ScriptException;
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
        F_BULLET_EVENT
    }

    private String[] inp;
    private Function<Integer, Double[]> formula;
    private bufType type;

    public EventBuffer(bufType type, String[] inp){
        this.type = type;
        this.inp = inp;
        this.formula = formula;
    }

    public BlockEvent makeBlockEvent(Engine engine){
        //1 : since, 2 : until, 3 : x, 4 : y, 5 : width, 6 : height
        return new BlockEvent(Integer.parseInt(inp[1]), Integer.parseInt(inp[2]), (Integer integer) -> {return null;}, engine.getBlocks(),
                new Block(Integer.parseInt(inp[3]), Integer.parseInt(inp[4]), Integer.parseInt(inp[5]), Integer.parseInt(inp[6])));
    }

    public EntityEvent makeFBulletEvent(Engine engine){
       return new EntityEvent(getTickByBeat(Integer.parseInt(inp[1])), Integer.parseInt(inp[2]), new Formula(){
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
                    Double[] res = {(Double)sEngine.eval(inp[5]), (Double)sEngine.eval(inp[6]), (Double)sEngine.eval(inp[7])};
                    return res;
               }catch(ScriptException e){ System.out.println(e.getMessage()); return null; }
           }
       }, engine.getEntities(), new Bullet());
    }

    public bufType getType(){
        return this.type;
    }
}
