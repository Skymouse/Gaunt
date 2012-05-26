/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jgaunt.world;

/**
 *
 * @author woeltjen
 */
public class Facing extends Position {
    private float angle;

    public Facing (float radians) {
        super ((float)Math.cos(radians), (float)Math.sin(radians));
        angle = radians;
    }

    public float getAngle() { return angle; }
}
