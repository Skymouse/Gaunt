/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jgaunt.world;

/**
 *
 * @author woeltjen
 */
public class Position implements Component {
    private float x, y;
    public Position(float x, float y) {
        this.x = x; this.y = y;
    }
    public float getX() { return x; }
    public float getY() { return y; }
    public Position add(Position p) { return new Position(x+p.x, y+p.y); }
}
