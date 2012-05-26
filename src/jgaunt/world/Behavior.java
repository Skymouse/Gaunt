/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jgaunt.world;

/**
 *
 * @author woeltjen
 */
public interface Behavior<T> extends Component {
    public void invoke(Entity e, T argument);
}
