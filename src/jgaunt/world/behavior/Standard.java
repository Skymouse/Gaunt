/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jgaunt.world.behavior;

import jgaunt.world.behavior.Common.Collide;
import jgaunt.world.behavior.Common.Render;

/**
 *
 * @author woeltjen
 */
public class Standard {
    public static final Render  RENDER  = new Paint();
    public static final Collide COLLIDE = new Solid();

}
