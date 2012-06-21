/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.world.components;

/**
 *
 * @author woeltjen
 */
public class Toughness extends Score {

    public Toughness(int score) {
        super(score);
    }

    @Override
    public Class getReplicatedClass() {
        return Toughness.class;
    }

    @Override
    public Toughness replicate() {
        return new Toughness(get());
    }
    
    
}
