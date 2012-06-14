/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.world.components;

import net.rowf.gaunt.world.Component;

/**
 *
 * @author woeltjen
 */
public abstract class Score implements Component { //TODO: Replicant!
    private int score;
    
    public Score(int score) {
        this.score = score;
    }
    
    public int get() {
        return score;
    }
    
    public int set(int amount) {
        return score = amount;
    }
    
    public int increment(int amount) {
        return set(get() + amount);
    }
    
    public int decrement(int amount) {
        return increment(-amount);
    }
}
