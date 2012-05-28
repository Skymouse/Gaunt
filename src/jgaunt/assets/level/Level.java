/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jgaunt.assets.level;

import jgaunt.world.dungeon.Dungeon;

/**
 *
 * @author woeltjen
 */
public class Level extends Dungeon {
    public Level(Populator p) {
        super(p.getWidth(), p.getHeight());
        for (int x = 0; x < p.getWidth(); x++)
            for (int y = 0; y < p.getHeight(); y++)
                setEntity(x, y, p.getEntity(x, y));
    }
}
