/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.game.projectile.harm;

import net.rowf.gaunt.game.damage.Damage;
import net.rowf.gaunt.world.Entity;
import net.rowf.gaunt.world.behavior.Common.Impact;

/**
 *
 * @author woeltjen
 */
public abstract class Harmful implements Impact {

    @Override
    public void invoke(Entity e, Entity victim) {
        float harm = harm(e, victim);
        for (Damage d : victim.get(Damage.class))
            d.invoke(victim, e, harm);
    }
    
    public abstract float harm(Entity e, Entity victim);
}
