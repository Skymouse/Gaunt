package net.rowf.gaunt.world;

/**
 * @author woeltjen
 */
public class Vector {
    public static final float EPSILON = 0.0000001f;
    private float x, y;
    public Vector(float x, float y) {
        this.x = x; this.y = y;
    }
    public float getX() { return x; }
    public float getY() { return y; }
    public Vector add(Vector p) { return new Vector(x+p.x, y+p.y); }
    public Vector scale(float s)  { return new Vector(x*s,   y*s);   }
}
