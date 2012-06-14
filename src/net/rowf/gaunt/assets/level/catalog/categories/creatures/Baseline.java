/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.assets.level.catalog.categories.creatures;

import net.rowf.gaunt.assets.level.Convertor;
import net.rowf.gaunt.assets.level.Index;
import net.rowf.gaunt.world.components.Health;
import net.rowf.gaunt.world.dungeon.spawns.Replicator;
import net.rowf.gaunt.world.dungeon.spawns.Specification;

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
