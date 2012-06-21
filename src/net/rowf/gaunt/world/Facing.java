/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rowf.gaunt.world;

/**
 *
 * @author woeltjen
 */
public class Facing extends Vector implements Component {
    private float angle;

    public Facing (float radians) {
        super ((float)Math.cos(radians), (float)Math.sin(radians));
        angle = radians;
    }

    public float getAngle() { return angle; }

}
