/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jgaunt.world.dungeon.spawns;

import jgaunt.world.Component;
import jgaunt.world.Entity;

/**
 *
 * @author woeltjen
 */
public interface Specifier<T extends Component> {
    public T        get(Entity e);
    public Class<T> getSpecifiedClass();
}
