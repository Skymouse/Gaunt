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
public class Marker extends Pencil {
    private float radius;
    
    public Marker(Ink ink, float radius) {
        super(ink);
        this.radius = radius;
    
    }

    @Override
    public void apply(Vector v) {
        for (float x = -radius; x < radius; x += 1.0f)
            for (float y = -radius; y < radius; y += 1.0f)
                if (x*x + y*y < radius*radius)
                    super.apply(v.add(new Vector(x, y)));
    }
    
    
    
    
    
}
