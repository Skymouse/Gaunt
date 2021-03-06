/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.assets.level.catalog.categories.creatures;

import net.rowf.gaunt.world.components.Facing;
import net.rowf.gaunt.world.entity.Prototype;
import net.rowf.gaunt.world.components.Boundary;
import net.rowf.gaunt.world.*;
import net.rowf.gaunt.world.behavior.Standard;

/**
 *
 * @author woeltjen
 */
public abstract class Creature extends Prototype {
    private static final Boundary BOUNDARY =
            new Boundary(new Vector(0.0f, 0.0f), new Vector(1.0f, 1.0f));
    
    @Override
    public Entity spawn() {
        Entity e = new Entity();
        
        e.add(BOUNDARY);
        e.add(new Facing(0));
        e.add(Standard.COLLIDE);
        e.add(Standard.IMPACT);
                
        return e;
    }
    
}
