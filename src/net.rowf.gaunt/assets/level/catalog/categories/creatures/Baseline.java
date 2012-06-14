/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jgaunt.assets.level.catalog.categories.creatures;

import jgaunt.assets.level.Convertor;
import jgaunt.assets.level.Index;
import jgaunt.world.components.Health;
import jgaunt.world.dungeon.spawns.Replicator;
import jgaunt.world.dungeon.spawns.Specification;

/**
 *
 * @author woeltjen
 */
public class Baseline implements Convertor<Index, Specification> {
    private static final Index SIZE = new Index(16);
    
    @Override
    public Specification convert(Index key) {
        Specification s = new Specification();
        
        s.add(new Replicator(new Health(key.get() * 10 + 10)));
        
        return s;
    }

    @Override
    public Index maximum() {
        return SIZE;
    }
    
}
