/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.game.projectile;

import net.rowf.gaunt.engine.logic.control.Input;
import net.rowf.gaunt.engine.logic.control.Regulated;
import net.rowf.gaunt.theory.Function;
import net.rowf.gaunt.world.Entity;
import net.rowf.gaunt.world.components.Replicant;
import net.rowf.gaunt.world.components.Score;

/**
 *
 * @author woeltjen
 */
public class Attack extends Regulated implements Replicant<Attack> {

    public Attack(Class<Score> key, Input input) {
        super(new Scored(key), Weapon.class, input);
    }

    @Override
    public Class<Attack> getReplicatedClass() {
        return Attack.class;
    }

    @Override
    public Attack replicate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private static class Scored implements Function<Integer, Entity> {
        private Class<Score> key;

        public Scored(Class<Score> key) {
            this.key = key;
        }

        @Override
        public Integer evaluate(Entity input) {
            int score = 100;
            for (Score s : input.get(key)) score = Math.min(score, 100 / s.get());
            return score;
        }
        
    }
    
    
}
