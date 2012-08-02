/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.game.projectile;

import net.rowf.gaunt.world.*;
import net.rowf.gaunt.world.components.Parameterizable;
import net.rowf.gaunt.world.components.Size;

/**
 *
 * @author woeltjen
 */
public class Emit implements Behavior<World>, Parameterizable<Prototype> {
    private Prototype prototype;

    public Emit(Prototype prototype) {
        this.prototype = prototype;
    }

    @Override
    public void invoke(Entity e, World w) {
        for (Boundary b : e.get(Boundary.class))
            for (Facing f : e.get(Facing.class))
                for (Size sz : e.get(Size.class))
                   w.addEntity( emit(e, b.center(), f, sz.get(), w) );
    }
    
    public Entity emit(Entity parent, Vector v, Facing f, float d, World w) {
        Entity entity = prototype.spawn();
        for (Boundary b : entity.get(Boundary.class))
            entity.set(Boundary.class, b.recenter(v.add(f.scale(d))));
        return entity;
    }

    @Override
    public void set(Prototype parameter) {
        prototype = parameter;
    }
    
    public Prototype get() {
        return prototype;
    }

    @Override
    public Class<Parameterizable<Prototype>> getReplicatedClass() {
        return (Class) Emit.class;
    }

    @Override
    public Parameterizable<Prototype> replicate() {
        return new Emit(prototype);
    }
    
    
}
