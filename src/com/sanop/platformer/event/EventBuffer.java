package com.sanop.platformer.event;

import com.sanop.platformer.Engine;
import com.sanop.platformer.entity.Block;

import java.util.function.Function;

public class EventBuffer {
    public enum bufType{
        BLOCK_EVENT,
        ENTITY_EVENT,
        GRAVITY_EVENT,
        INPUT_EVENT,
        SCREEN_EFFECT_EVENT,
        SPEED_EVENT
    }

    private int since, until, x, y, width, height;
    private Function<Integer, Double[]> formula;
    private bufType type;


    public EventBuffer(bufType type, int since, int until, int x, int y, int width, int height, Function<Integer, Double[]> formula) {
        this.type = type;
        this.since = since;
        this.until = until;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.formula = formula;
    }

    public BlockEvent makeBlockEvent(Engine engine){
        return new BlockEvent(since, until, formula, engine.getBlocks(), new Block(x, y, width, height));
    }

    public bufType getType(){
        return this.type;
    }
}
