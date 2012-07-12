/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.engine.renderer;

import net.rowf.gaunt.world.Boundary;
import net.rowf.gaunt.world.Entity;
import net.rowf.gaunt.world.Vector;
import net.rowf.gaunt.world.World;
import net.rowf.gaunt.world.behavior.Common.Task;

/**
 *
 * @author woeltjen
 */
public class Watcher implements Camera, Task {
    private Boundary extents;
    private Entity   entity;

    public Watcher() {
//        this.extents = extents;
//        this.entity  = entity;
    }
  
    
    @Override
    public Boundary getVisibleBoundary(float width, float height) {
        if (entity == null)
            return new Boundary(new Vector(0,0), new Vector(width,height));
        
        Vector half   = new Vector (width / 2 , height / 2);
        Vector middle = half;
        
        for (Boundary b : entity.get(Boundary.class)) 
            middle = b.getMinimum().add(b.getMaximum()).scale(0.5f);
        
        Vector low  = middle.add(half.scale(-1.0f));
        Vector high = middle.add(half);
        
        return new Boundary(low, high); //TODO: Clamp to extents
    }

    @Override
    public void invoke(Entity e, World argument) {
        this.entity = e;
    }
    
}
