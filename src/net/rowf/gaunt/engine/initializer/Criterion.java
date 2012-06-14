/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.engine.initializer;

import net.rowf.gaunt.world.Entity;

/**
 *
 * @author woeltjen
 */
public interface Criterion {
    public boolean consider(Entity e);
}
