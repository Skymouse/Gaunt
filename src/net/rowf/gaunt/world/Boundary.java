/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rowf.gaunt.world;

/**
 *
 * @author woeltjen
 */
public class Boundary implements Component {

    private Vector minimum;
    private Vector maximum;

    public Boundary (float width, float height) {
        minimum = new Vector(-width/2, -height/2);
        maximum = new Vector( width/2,  height/2);
    }

    public Boundary (Vector min, Vector max) {
        minimum = min;
        maximum = max;
    }

    public Vector getMaximum() {
        return maximum;
    }

    public Vector getMinimum() {
        return minimum;
    }

    public Boundary add(Vector p) {
        return new Boundary(minimum.add(p), maximum.add(p));
    }

    public boolean overlaps(Boundary b) {
        return minimum.getX() < b.getMaximum().getX() &&
               maximum.getX() > b.getMinimum().getX() &&
               minimum.getY() < b.getMaximum().getY() &&
               maximum.getY() > b.getMinimum().getY();
    }

    public Vector eject(Boundary b) {
        if (!overlaps(b)) return new Vector(0.0f, 0.0f);

        float x1 = maximum.getX() - b.getMinimum().getX();
        float x2 = minimum.getX() - b.getMaximum().getX();
        float y1 = maximum.getY() - b.getMinimum().getY();
        float y2 = minimum.getY() - b.getMaximum().getY();
        float x  = (Math.abs(x1) < Math.abs(x2)) ? x1 : x2;
        float y  = (Math.abs(y1) < Math.abs(y2)) ? y1 : y2;

        if (Math.abs(x) < Math.abs(y)) return new Vector(x, 0);
        if (Math.abs(x) > Math.abs(y)) return new Vector(0, y);        
        return new Vector(x, y);
    }
}
