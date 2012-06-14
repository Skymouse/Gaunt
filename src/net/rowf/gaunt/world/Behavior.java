/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rowf.gaunt.world;

/**
 *
 * @author woeltjen
 */
public interface Behavior<T> extends Component {
    public void invoke(Entity e, T argument);
}
