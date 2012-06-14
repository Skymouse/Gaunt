/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.world.dungeon.spawns;

import net.rowf.gaunt.world.Component;
import net.rowf.gaunt.world.Entity;

/**
 *
 * @author woeltjen
 */
public interface Specifier<T extends Component> {
    public T        get(Entity e);
    public Class<T> getSpecifiedClass();
}
