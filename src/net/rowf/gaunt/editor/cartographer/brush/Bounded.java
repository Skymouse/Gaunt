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
public abstract class Bounded extends Applicator {
    private Vector  start = new Vector(0f,0f);
    private Vector  current = start;
    private boolean active = false;

    public Bounded(Ink ink) {
        super(ink);
    }

    
    public abstract Render getActiveRepresentation(Vector from, Vector to);
    public abstract void   apply(Vector start, float w, float h);
    
    @Override
    public void advance(Vector v) {
        current = v;
    }

    @Override
    public void begin(Vector v) {
        current = start  = v;
        active = true;
    }

    @Override
    public void conclude(Vector v) {
        Vector difference = v.add(start.scale(-1f));
        float w = difference.getX();
        float h = difference.getY();
        apply(start, w, h);
        active = false;
    }

    @Override
    public Render getRepresentation() {
        return active ? getActiveRepresentation(start, current) : Brush.NONE;
    }
    

    @Override
    public void prepare(Vector v) {        
    }
    
}
