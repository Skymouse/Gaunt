/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.editor.cartographer.brush;

import net.rowf.gaunt.world.Vector;

/**
 *
 * @author woeltjen
 */
public abstract class Applicator implements Brush, Ink {
    private Ink ink;

    public Applicator(Ink ink) {
        this.ink = ink;
    }
    
    public void setInk(Ink ink) {
        this.ink = ink;
    }
    
    @Override
    public void apply(Vector v) {
        ink.apply(v);
    }
}
