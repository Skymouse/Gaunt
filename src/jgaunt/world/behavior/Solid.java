/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jgaunt.world.behavior;

import jgaunt.world.Boundary;
import jgaunt.world.Entity;
import jgaunt.world.behavior.Common.Collide;
import jgaunt.world.behavior.Common.Impact;

/**
 *
 * @author woeltjen
 */
public class Solid implements Collide {

    public void invoke(Entity e, Entity other) {
        for (Boundary b1 : e.get(Boundary.class))
            for (Boundary b2 : other.get(Boundary.class))
                if (b1.overlaps(b2))
                    for (Impact impact : other.get(Impact.class))
                        impact.invoke(other, e);
    }

}
