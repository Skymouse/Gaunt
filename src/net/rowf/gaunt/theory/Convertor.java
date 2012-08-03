/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rowf.gaunt.theory;

/**
 *
 * @author woeltjen
 */
public interface Convertor<K, V> extends Function<V, K> {
    public K maximum();
}
