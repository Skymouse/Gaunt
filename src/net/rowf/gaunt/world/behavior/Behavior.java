/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rowf.gaunt.world.behavior;

import net.rowf.gaunt.world.Component;
import net.rowf.gaunt.world.Entity;

/**
 *
 * @author woeltjen
 */
public interface Behavior<T> extends Component {
    public void invoke(Entity e, T argument);
}
