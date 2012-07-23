/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rowf.gaunt.world.behavior;

import net.rowf.gaunt.engine.renderer.View;
import net.rowf.gaunt.world.Behavior;
import net.rowf.gaunt.world.Entity;
import net.rowf.gaunt.world.World;

/**
 *
 * @author woeltjen
 */
public class Common {
    public static interface Render  extends Behavior<View>   {}
    public static interface Think   extends Behavior<World>  {}
    public static interface Task    extends Behavior<World>  {}
    public static interface Move    extends Behavior<World>  {}
    public static interface Collide extends Behavior<Entity> {}
    public static interface Impact  extends Behavior<Entity> {}
    public static interface Damage  extends Behavior<Entity> {}
}
