/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.world.components;

/**
 *
 * @author woeltjen
 */
public class Mind extends Score {

    public Mind(int score) {
        super(score);
    }

    @Override
    public Class getReplicatedClass() {
        return Mind.class;
    }

    @Override
    public Mind replicate() {
        return new Mind(get());
    }
    
    
}
