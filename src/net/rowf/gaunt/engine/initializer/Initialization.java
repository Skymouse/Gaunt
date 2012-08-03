/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.engine.initializer;

import net.rowf.gaunt.theory.Criterion;
import net.rowf.gaunt.world.Entity;

/**
 *
 * @author woeltjen
 */
public interface Initialization extends Criterion<Entity> {
    public Entity initialize(Entity e);
    public boolean unique();
}
