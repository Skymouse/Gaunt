/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rowf.gaunt.world.behavior;

import net.rowf.gaunt.game.intelligence.Wander;
import net.rowf.gaunt.world.behavior.Common.Collide;
import net.rowf.gaunt.world.behavior.Common.Impact;
import net.rowf.gaunt.world.behavior.Common.Render;
import net.rowf.gaunt.world.behavior.Common.Think;
import net.rowf.gaunt.world.behavior.movement.Expulsion;
import net.rowf.gaunt.world.behavior.movement.Maneuver;
import net.rowf.gaunt.world.behavior.movement.Propulsion;

/**
 *
 * @author woeltjen
 */
public class Standard {
    public static final Render   RENDER     = new Paint();
    public static final Collide  COLLIDE    = new Solid();
    public static final Render   HOLOGRAPHY = new Holography();
    public static final Impact   IMPACT     = new Expulsion();
    public static final Maneuver RUN        = new Propulsion(1.5f);
    public static final Maneuver WALK       = new Propulsion();
    public static final Maneuver STAND      = new Propulsion(0f);
    public static final Think    WANDER     = new Wander(30);
} 
