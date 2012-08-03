/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.game.intelligence;

import net.rowf.gaunt.theory.Criterion;
import net.rowf.gaunt.engine.initializer.criteria.Has;
import net.rowf.gaunt.world.Entity;
import net.rowf.gaunt.world.behavior.Standard;

/**
 *
 * @author woeltjen
 */
public class Hunt extends Decision {
    private static final Criterion<Entity> criterion = new Has(Target.class);
    
    public Hunt() {
        super(criterion, new Pursue(20), Standard.WANDER);
    }
    
}
