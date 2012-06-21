/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.world.components;

/**
 *
 * @author woeltjen
 */
public class Power extends Score {

    public Power(int score) {
        super(score);
    }

    @Override
    public Class getReplicatedClass() {
        return Power.class;
    }

    @Override
    public Power replicate() {
        return new Power(get());
    }
    
    
}
