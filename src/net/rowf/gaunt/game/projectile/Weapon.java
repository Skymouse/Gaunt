/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.game.projectile;

import net.rowf.gaunt.world.components.Facing;
import net.rowf.gaunt.world.entity.Prototype;
import net.rowf.gaunt.world.*;
import net.rowf.gaunt.world.components.Parameterizable;

/**
 *
 * @author woeltjen
 */
public class Weapon extends Emit {

    public Weapon(Prototype prototype) {
        super(prototype);
    }

    @Override
    public Class<Parameterizable<Prototype>> getReplicatedClass() {
        return (Class) Weapon.class;
    }

    @Override
    public Parameterizable<Prototype> replicate() {
        return new Weapon(get());
    }

    @Override
    public Entity emit(Entity parent, Vector v, Facing f, float d, World w) {
        Entity e = super.emit(parent, v, f, d, w);
        e.set(Owner.class, new Owner(parent));
        return e;
    }
    
}
