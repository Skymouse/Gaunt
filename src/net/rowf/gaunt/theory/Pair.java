/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.theory;

/**
 *
 * @author woeltjen
 */
public class Pair<K, V> {
    private K left;
    private V right;
    
    
    public Pair(K left, V right) {
        this.left = left;
        this.right = right;
    }

    public K getLeft() {
        return left;
    }

    public V getRight() {
        return right;
    }
    
}
