/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rowf.gaunt.world.dungeon.walls;

import net.rowf.gaunt.world.Component;
import net.rowf.gaunt.world.Entity;
import net.rowf.gaunt.world.dungeon.Dungeon;

/**
 *
 * @author woeltjen
 */
public class Demolish implements Component {
    public void invoke(Entity e) {
        for (Dungeon d : e.get(Dungeon.class))
            d.clearEntity(e);
    }
}
