/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.world.components;

/**
 *
 * @author woeltjen
 */
public class Strength extends Score {

    public Strength(int score) {
        super(score);
    }

    @Override
    public Class getReplicatedClass() {
        return Strength.class;
    }

    @Override
    public Strength replicate() {
        return new Strength(get());
    }
    
    
}
