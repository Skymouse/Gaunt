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

//    public Entity spawn(Position p) {
//        Entity e = spawn();
//        e.set(Position.class, p);
//        return e;
//    }
//
//    public Entity spawn(World w, Position p) {
//        Entity e = spawn(p);
//        e.add(w);
//        return e;
//    }
}
