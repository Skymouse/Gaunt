/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.engine.initializer;

import net.rowf.gaunt.world.Boundary;
import net.rowf.gaunt.world.Entity;
import net.rowf.gaunt.world.Prototype;
import net.rowf.gaunt.world.World;
import net.rowf.gaunt.world.components.Size;

/**
 *
 * @author woeltjen
 */
public class Injector implements Initialization {
    private Criterion criterion;
    private Prototype prototype;
    private boolean   unique;

    public Injector(Criterion criterion, Prototype prototype, boolean unique) {
        this.criterion = criterion;
        this.prototype = prototype;
        this.unique    = unique;
    }
            
    @Override
    public boolean consider(Entity e) {
        return !e.get(Boundary.class).isEmpty() && criterion.consider(e);
    }

    @Override
    public Entity initialize(Entity e) {
        Entity entity = prototype.spawn();
        for (Boundary zone : e.get(Boundary.class))
            for (Size sz : entity.get(Size.class))
                entity.set(Boundary.class,
                           new Boundary(sz.get()).recenter(zone.center()));
        for (World w : e.get(World.class))
            w.addEntity(entity);
        return entity;
    }

    @Override
    public boolean unique() {
        return unique;
    }
    
}
