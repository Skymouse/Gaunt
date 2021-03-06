/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.assets.level.catalog.categories;

import net.rowf.gaunt.theory.Convertor;
import net.rowf.gaunt.assets.level.Index;
import net.rowf.gaunt.engine.renderer.Hologram;
import net.rowf.gaunt.world.entity.Prototype;
import net.rowf.gaunt.world.behavior.Common.Render;
import net.rowf.gaunt.world.behavior.Standard;
import net.rowf.gaunt.world.dungeon.Spawn;
import net.rowf.gaunt.world.dungeon.spawns.Reuser;
import net.rowf.gaunt.world.dungeon.spawns.Specification;

/**
 *
 * @author woeltjen
 */
public class Creatures extends Category {    
    private Prototype[] prototypes;
    
    public Creatures(Prototype                       prototype,
                     Convertor<Index, Hologram>      holograms,
                     Convertor<Index, Specification> specifications) {
        prototypes = new Prototype[maximum().get()];
        for (Index index = new Index(0); 
             index.get() < maximum().get(); 
             index = new Index(index.get() + 1)) {
         
            Specification s = specifications.evaluate(index).apply(prototype);
            s.add(new Reuser(Hologram.class, holograms.evaluate(index)));
            s.add(new Reuser(Render.class, Standard.HOLOGRAPHY));
            prototypes[index.get()] = new Spawn( s );            
        }
    }
    
    @Override
    public Prototype evaluate(Index key) {
        return prototypes[key.get()];
    }
    
}
