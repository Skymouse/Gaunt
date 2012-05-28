/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jgaunt.world.dungeon.walls;

import jgaunt.world.Component;
import jgaunt.world.Entity;
import jgaunt.world.dungeon.Dungeon;

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
