/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rowf.gaunt.world;

/**
 * TODO: Move, rename "Vector!" Entities have boundaries, not positions - right?
 * @author woeltjen
 */
public class Position {
    private float x, y;
    public Position(float x, float y) {
        this.x = x; this.y = y;
    }
    public float getX() { return x; }
    public float getY() { return y; }
    public Position add(Position p) { return new Position(x+p.x, y+p.y); }
    public Position scale(float s)  { return new Position(x*s,   y*s);   }
}
