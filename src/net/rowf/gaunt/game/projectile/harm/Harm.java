/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.game.projectile.harm;

import net.rowf.gaunt.world.Entity;
import net.rowf.gaunt.world.behavior.Common.Impact;
import net.rowf.gaunt.world.components.Parameterizable;
import net.rowf.gaunt.world.components.Score;

/**
 *
 * @author woeltjen
 */
public class Harm<C extends Score> extends Harmful implements Impact, Parameterizable<Class<C>> {
    private Class<C> scoreClass;

    public Harm(Class<C> scoreClass) {
        this.scoreClass = scoreClass;
    }
    
    public float harm(Entity e, Entity victim) {
        float s = 0;
        for (C score : e.get(scoreClass)) s = Math.max(s, (float) score.get());
        return s;
    }

    @Override
    public void set(Class<C> parameter) {
        scoreClass = parameter;
    }

    @Override
    public Class<Parameterizable<Class<C>>> getReplicatedClass() {
        return (Class) Harm.class;
    }

    @Override
    public Parameterizable<Class<C>> replicate() {
        return new Harm(scoreClass);
    }
    
}
