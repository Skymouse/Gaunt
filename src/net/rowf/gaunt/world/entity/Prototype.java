/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rowf.gaunt.world.entity;

import net.rowf.gaunt.world.Entity;

/**
 *
 * @author woeltjen
 */
public abstract class Prototype {
    public abstract Entity spawn();
    public static final Prototype EMPTY = new Prototype() {
        @Override
        public Entity spawn() {
            return new Entity();
        }
    };
}
