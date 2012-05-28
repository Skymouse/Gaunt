/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jgaunt.world.dungeon.walls;

import jgaunt.world.Entity;
import jgaunt.world.behavior.Common.Damage;

/**
 *
 * @author woeltjen
 */
public class Crumble implements Damage {
    private int damage = 0;

    public void invoke(Entity e, Entity argument) {
        damage++; //TODO: Make commensurate to STRENGTH!
        for (Hardness h : e.get(Hardness.class))
            if (damage > h.get())
                for (Demolish d : e.get(Demolish.class))
                    d.invoke(e);
    }

}
