/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rowf.gaunt.world.behavior;

import net.rowf.gaunt.world.behavior.Common.Collide;
import net.rowf.gaunt.world.behavior.Common.Impact;
import net.rowf.gaunt.world.behavior.Common.Render;
import net.rowf.gaunt.world.behavior.movement.Expulsion;

/**
 *
 * @author woeltjen
 */
public class Standard {
    public static final Render  RENDER     = new Paint();
    public static final Collide COLLIDE    = new Solid();
    public static final Render  HOLOGRAPHY = new Holography();
    public static final Impact  IMPACT     = new Expulsion();
}
