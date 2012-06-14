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

    public Position eject(Boundary b) {
        if (!overlaps(b)) return new Position(0.0f, 0.0f);

        float x1 = maximum.getX() - b.getMinimum().getX();
        float x2 = minimum.getX() - b.getMaximum().getX();
        float y1 = maximum.getY() - b.getMinimum().getY();
        float y2 = minimum.getY() - b.getMaximum().getY();
        float x  = (Math.abs(x1) < Math.abs(x2)) ? x1 : x2;
        float y  = (Math.abs(y1) < Math.abs(y2)) ? y1 : y2;

        if (Math.abs(x) < Math.abs(y)) return new Position(x, 0);
        if (Math.abs(x) > Math.abs(y)) return new Position(0, y);        
        return new Position(x, y);
    }
}
