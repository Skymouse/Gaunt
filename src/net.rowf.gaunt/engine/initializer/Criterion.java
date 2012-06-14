/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jgaunt.engine.initializer;

import jgaunt.world.Entity;

/**
 *
 * @author woeltjen
 */
public interface Criterion {
    public boolean consider(Entity e);
}
