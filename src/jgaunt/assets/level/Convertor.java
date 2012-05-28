/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jgaunt.assets.level;

/**
 *
 * @author woeltjen
 */
public interface Convertor<K, V> {
    public V convert(K key);
    public K maximum();
}
