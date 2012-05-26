/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jgaunt.world.behavior;

import jgaunt.engine.renderer.View;
import jgaunt.world.Behavior;
import jgaunt.world.Entity;
import jgaunt.world.World;

/**
 *
 * @author woeltjen
 */
public class Common {
    public static interface Render  extends Behavior<View>   {}
    public static interface Think   extends Behavior<World>  {}
    public static interface Move    extends Behavior<World>  {}
    public static interface Collide extends Behavior<Entity> {}
    public static interface Impact  extends Behavior<Entity> {}
    public static interface Damage  extends Behavior<Entity> {}
}
