/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.game.projectile.harm;

import net.rowf.gaunt.world.Entity;
import net.rowf.gaunt.world.components.Mind;
import net.rowf.gaunt.world.components.Parameterizable;
import net.rowf.gaunt.world.components.Score;
import net.rowf.gaunt.world.components.Strength;

/**
 *
 * @author woeltjen
 */
public class Empowered extends Harmful implements Parameterizable<Float> {
    private float multiplier = 1f;
    private Harm<?> harm;

    public Empowered(Class<? extends Score> score) {
        this.harm = new Harm(score);
    }
    public Empowered(Harm<?> harm, float multiplier) {
        this.harm = harm;
        this.multiplier = multiplier;
    }

    @Override
    public float harm(Entity e, Entity victim) {
        return harm.harm(e, victim) * multiplier;
    }

    @Override
    public void set(Float parameter) {
        multiplier = parameter;
    }

    @Override
    public Class<Parameterizable<Float>> getReplicatedClass() {
        return (Class) Empowered.class;
    }

    @Override
    public Parameterizable<Float> replicate() {
        return new Empowered(harm, multiplier);
    }
    
    public static class Mighty extends Empowered {
        public Mighty() {
            super(Strength.class);
        }        
    } 
    public static class Willful extends Empowered {
        public Willful() {
            super(Mind.class);
        }        
    } 
}
