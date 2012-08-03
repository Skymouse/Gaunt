/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.theory;

/**
 *
 * @author woeltjen
 */
public interface Function<K, V> {
    K evaluate(V input);
}
