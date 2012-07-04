/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.world.adventurer;

import net.rowf.gaunt.engine.renderer.Hologram;
import net.rowf.gaunt.world.Entity;
import net.rowf.gaunt.world.Prototype;
import net.rowf.gaunt.world.behavior.Common.Render;
import net.rowf.gaunt.world.behavior.Standard;

/**
 *
 * @author woeltjen
 */
public class Adventurer extends Prototype {
    
    private Prototype base;
    private Hologram  hologram;

    public Adventurer(String name, Prototype base, Hologram hologram) {
        this.base = base;
        this.hologram = hologram;
    }    
    
    private Adventurer (int experience) {
        // TODO: Derive a similar adventurer - also, inventory!
    }
    
    @Override
    public Entity spawn() {
        Entity e = base.spawn();
        
        e.set(Render.class, Standard.HOLOGRAPHY);
        e.add(hologram);
        
        return e;
    }
    
}
