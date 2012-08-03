/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.editor.cartographer.brush;

import net.rowf.gaunt.theory.Provider;
import net.rowf.gaunt.editor.cartographer.Architect;
import net.rowf.gaunt.editor.cartographer.Palette;
import net.rowf.gaunt.world.Vector;
import net.rowf.gaunt.world.behavior.Common.Render;

/**
 *
 * @author woeltjen
 */
public class Dropper implements Brush, Provider<Brush> {
    private Architect architect;
    private Palette   palette;

    public Dropper(Architect architect, Palette palette) {
        this.architect = architect;
        this.palette   = palette;
    }
    
    @Override
    public void advance(Vector v) {
    }

    @Override
    public void begin(Vector v) {
    }

    @Override
    public void conclude(Vector v) {
        palette.setPrimary(architect.get((int)v.getX(), (int)v.getY()));
    }

    @Override
    public void prepare(Vector v) {
    }
    
    
    @Override
    public Render getRepresentation() {
        return Brush.NONE;
    }

    @Override
    public Brush get() {
        return this;
    }

    
}
