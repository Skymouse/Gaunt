/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.world.components;

import net.rowf.gaunt.world.Boundary;
import net.rowf.gaunt.world.Vector;

/**
 *
 * @author woeltjen
 */
public class Size extends Boundary implements Parameterizable<Float> {
    private float size;
    
    public Size(float size) {
        this(size, new Vector(0.5f, 0.5f));
    }
    
    private Size(float size, Vector center) {
        super(center.add(new Vector(-size/2, -size/2)), 
              center.add(new Vector( size/2,  size/2)));
        this.size = size;
    }
    
    @Override
    public void set(Float parameter) {
        this.size = parameter;
    }

    @Override
    public Class getReplicatedClass() {
        return Size.class;
    }

    @Override
    public Parameterizable<Float> replicate() {
        return new Size(size);
    }

    @Override
    public Boundary add(Vector p) {
        return new Size(size, getMinimum().add(p.add(new Vector(size/2, size/2))));
    }
 
    
}
