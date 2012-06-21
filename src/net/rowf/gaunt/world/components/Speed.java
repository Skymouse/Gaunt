/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.world.components;

/**
 *
 * @author woeltjen
 */
public class Speed extends Score {

    public Speed(int score) {
        super(score);
    }

    @Override
    public Class getReplicatedClass() {
        return Speed.class;
    }

    @Override
    public Parameterizable<Integer> replicate() {
        return new Speed(get());
    }
    
}
