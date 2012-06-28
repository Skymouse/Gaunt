/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.world.components;

/**
 *
 * @author woeltjen
 */
public class Size implements Parameterizable<Float> {
    private float size;
    
    public Size (float size) {
        this.size = size;
    }
    
    public float get() {
        return size;
    }
    
    @Override
    public void set(Float parameter) {
        size = parameter;
    }

    @Override
    public Class getReplicatedClass() {
        return Size.class;
    }

    @Override
    public Parameterizable<Float> replicate() {
        return new Size(size);
    }
    
}
