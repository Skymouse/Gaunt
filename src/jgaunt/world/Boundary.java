/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jgaunt.world;

/**
 *
 * @author woeltjen
 */
public class Boundary implements Component {

    private Position minimum;
    private Position maximum;

    public Boundary (float width, float height) {
        minimum = new Position(-width/2, -height/2);
        maximum = new Position( width/2,  height/2);
    }

    public Boundary (Position min, Position max) {
        minimum = min;
        maximum = max;
    }

    public Position getMaximum() {
        return maximum;
    }

    public Position getMinimum() {
        return minimum;
    }

    public Boundary add(Position p) {
        return new Boundary(minimum.add(p), maximum.add(p));
    }

    public boolean overlaps(Boundary b) {
        return minimum.getX() < b.getMaximum().getX() &&
               maximum.getX() > b.getMinimum().getX() &&
               minimum.getY() < b.getMaximum().getY() &&
               maximum.getY() > b.getMinimum().getY();
    }
}
