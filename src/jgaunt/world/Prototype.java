/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jgaunt.world;

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
