/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.editor.cartographer.brush;

import net.rowf.gaunt.world.Vector;
import net.rowf.gaunt.world.behavior.Common.Render;

/**
 *
 * @author woeltjen
 */
public class Pencil extends Applicator {
    private Vector last = new Vector(0,0);
    
    public Pencil(Ink ink) {
        super(ink);
    }
    
    

    @Override
    public void advance(Vector v) {
        if (Math.abs(v.getX() - last.getX()) > Vector.EPSILON ||
            Math.abs(v.getY() - last.getY()) > Vector.EPSILON)
            begin(v);
    }

    @Override
    public void begin(Vector v) {
        apply(v);
        last = v;
    }

    @Override
    public void conclude(Vector v) {        
    }

    @Override
    public void prepare(Vector v) {
    }

    @Override
    public Render getRepresentation() {
        return Brush.NONE;
    }    
}
