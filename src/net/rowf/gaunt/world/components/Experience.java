/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.world.components;

/**
 *
 * @author woeltjen
 */
public class Experience extends Score {

    public Experience(int score) {
        super(score);
    }

    @Override
    public Class getReplicatedClass() {
        return Experience.class;
    }

    @Override
    public Parameterizable<Integer> replicate() {
        return new Experience(get());
    }
    
}
