/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.world.components;

/**
 *
 * @author woeltjen
 */
public interface Parameterizable<T> extends Replicant<Parameterizable<T>> {
   public void set(T parameter);  
}
