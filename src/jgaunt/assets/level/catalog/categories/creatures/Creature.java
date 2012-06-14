/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jgaunt.assets.level.catalog.categories.creatures;

import jgaunt.world.*;
import jgaunt.world.behavior.Standard;
import jgaunt.world.components.Speed;

/**
 *
 * @author woeltjen
 */
public abstract class Creature extends Prototype {
    private static final Boundary BOUNDARY =
            new Boundary(new Position(0.0f, 0.0f), new Position(1.0f, 1.0f));
    
    @Override
    public Entity spawn() {
        Entity e = new Entity();
        
        e.add(BOUNDARY);
        e.add(new Facing(0));
        e.add(Standard.COLLIDE);
        e.add(Standard.IMPACT);
        
        e.add(new Player("test"));
        e.add(new Speed(20));
        
        return e;
    }
    
}
