/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rowf.gaunt.world.dungeon.doors;

import net.rowf.gaunt.world.components.Boundary;
import net.rowf.gaunt.world.Entity;
import net.rowf.gaunt.world.dungeon.Dungeon;
import net.rowf.gaunt.world.dungeon.walls.Demolish;

/**
 *
 * @author woeltjen
 */
public class Unlock extends Demolish {

    @Override
    public void invoke(Entity e) {
        int x = 0, y = 0;
        boolean located = false;
        for (Boundary b : e.get(Boundary.class)) {
            x = (int) b.getMinimum().getX();
            y = (int) b.getMaximum().getY();
            located = true;
        }

        super.invoke(e);

        if (!located) return;

        for (Rating r1 : e.get(Rating.class)) 
            for (Dungeon d : e.get(Dungeon.class))
                for (int u = x - 1; u <= x + 1; u++)
                    for (int v = y - 1; v <= y + 1; v++)
                        for (Rating r2 : (e=d.getEntity(u,v)).get(Rating.class))
                            if (r1.get() == r2.get())
                                for (Unlock unlock : e.get(Unlock.class))
                                    unlock.invoke(e);
    }


}
