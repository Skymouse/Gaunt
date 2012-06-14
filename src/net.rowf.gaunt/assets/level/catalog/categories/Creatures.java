/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jgaunt.assets.level.catalog.categories;

import jgaunt.assets.level.Convertor;
import jgaunt.assets.level.Index;
import jgaunt.engine.renderer.Hologram;
import jgaunt.world.Prototype;
import jgaunt.world.behavior.Common.Render;
import jgaunt.world.behavior.Standard;
import jgaunt.world.dungeon.Spawn;
import jgaunt.world.dungeon.spawns.Reuser;
import jgaunt.world.dungeon.spawns.Specification;

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
         
            Specification s = specifications.convert(index).apply(prototype);
            s.add(new Reuser(Hologram.class, holograms.convert(index)));
            s.add(new Reuser(Render.class, Standard.HOLOGRAPHY));
            prototypes[index.get()] = new Spawn( s );            
        }
    }
    
    @Override
    public Prototype convert(Index key) {
        return prototypes[key.get()];
    }
    
}
