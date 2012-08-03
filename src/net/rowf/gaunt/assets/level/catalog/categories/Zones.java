/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.assets.level.catalog.categories;

import net.rowf.gaunt.theory.Convertor;
import net.rowf.gaunt.assets.level.Index;
import net.rowf.gaunt.world.entity.Prototype;
import net.rowf.gaunt.world.dungeon.Spawn;
import net.rowf.gaunt.world.dungeon.spawns.Specification;

/**
 *
 * @author woeltjen
 */
public class Zones extends Category {    
    private Prototype[] prototypes;
    
    public Zones(Prototype                       prototype,
                     Convertor<Index, Specification> specifications) {
        prototypes = new Prototype[maximum().get()];
        for (Index index = new Index(0); 
             index.get() < maximum().get(); 
             index = new Index(index.get() + 1)) {
         
            Specification s = specifications.convert(index).apply(prototype);
            prototypes[index.get()] = new Spawn( s );            
        }
    }
    
    @Override
    public Prototype convert(Index key) {
        return prototypes[key.get()];
    }
    
}
