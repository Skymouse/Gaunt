/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.world.components;

/**
 *
 * @author woeltjen
 */
public abstract class Score implements Parameterizable<Integer> { //TODO: Replicant!
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
    
    public void set(Integer amount) {
        set(amount.intValue());
    }
    
    public int increment(int amount) {
        return set(get() + amount);
    }
    
    public int decrement(int amount) {
        return increment(-amount);
    }
    
    
}
