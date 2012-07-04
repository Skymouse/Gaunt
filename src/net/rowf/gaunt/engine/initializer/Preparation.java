/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.engine.initializer;

import net.rowf.gaunt.world.Entity;
import net.rowf.gaunt.world.dungeon.spawns.Specification;

/**
 *
 * @author woeltjen
 */
public class Preparation implements Initialization {
    private Criterion     criterion;
    private Specification specification;
    private boolean       unique;
    
    public Preparation(Criterion criterion, Specification specification, boolean unique) {
        this.criterion     = criterion;
        this.specification = specification;
        this.unique        = unique;
    }

    @Override
    public boolean consider(Entity e) {
        return criterion.consider(e);
    } 
    
    @Override
    public Entity initialize(Entity e) {
        return specification.apply(e);
    }
    
    public boolean unique() {
        return unique;
    }
}
