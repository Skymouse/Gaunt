/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.editor.cartographer.brush;

import net.rowf.gaunt.assets.level.Convertor;
import net.rowf.gaunt.assets.level.Index;
import net.rowf.gaunt.assets.level.Provider;
import net.rowf.gaunt.editor.cartographer.Architect;
import net.rowf.gaunt.editor.cartographer.stylus.Cursor;
import net.rowf.gaunt.world.*;
import net.rowf.gaunt.world.dungeon.Dungeon;

/**
 *
 * @author woeltjen
 */
public class Placer implements Ink {
    private Architect architect;
    private Convertor<Index, Prototype> convertor;
    private Entity dungeon;
    private Provider<Index>  indexer;

    public Placer(Architect architect, Convertor<Index, Prototype> convertor, Entity dungeon, Provider<Index> index) {
        this.architect = architect;
        this.convertor = convertor;
        this.dungeon   = dungeon;
        this.indexer   = index;
    }
           
    public void apply(Vector v) {
        Index index = indexer.get();
        Vector midpoint = new Vector((float) Math.floor(v.getX()),
                                     (float) Math.floor(v.getY()))
                                         .add(new Vector(0.5f,0.5f));
        Boundary clear = new Boundary(0.25f, midpoint);
        architect.set((int) v.getX(), (int) v.getY(), index.get());
        for (World w : dungeon.first(World.class))
            for (Entity e : w.getEntities())
                if (e != dungeon && e.get(Cursor.class).isEmpty())
                    for (Boundary b : e.get(Boundary.class))
                        if (b.overlaps(clear))
                            w.removeEntity(e);
        for (Dungeon d : dungeon.first(Dungeon.class))
            d.setEntity((int) v.getX(), (int) v.getY(), 
                    convertor.convert(index).spawn());
    }
    
}
