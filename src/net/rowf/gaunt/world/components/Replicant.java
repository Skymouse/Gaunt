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
public interface Replicant<T> extends Component {
    public Class<T> getReplicatedClass();
    public T        replicate();
}
