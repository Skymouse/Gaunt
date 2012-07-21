/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.engine.initializer;

/**
 *
 * @author woeltjen
 */
public interface Criterion<E> {
    public boolean consider(E e);
}
