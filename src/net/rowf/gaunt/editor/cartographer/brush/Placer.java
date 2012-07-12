/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.editor.cartographer.brush;

import net.rowf.gaunt.assets.level.Convertor;
import net.rowf.gaunt.assets.level.Index;
import net.rowf.gaunt.editor.cartographer.Architect;
import net.rowf.gaunt.editor.cartographer.Cursor;
import net.rowf.gaunt.world.*;
import net.rowf.gaunt.world.dungeon.Dungeon;

/**
 *
 * @author woeltjen
 */
public class Placer {
    private Architect architect;
    private Convertor<Index, Prototype> convertor;
    private Entity dungeon;
    private Index  index;

    public Placer(Architect architect, Convertor<Index, Prototype> convertor, Entity dungeon, Index index) {
        this.architect = architect;
        this.convertor = convertor;
        this.dungeon   = dungeon;
        this.index     = index;
    }
           
    public void place(Vector v) {
        Vector midpoint = new Vector((float) Math.floor(v.getX()),
                                         (float) Math.floor(v.getY()))
                                         .add(new Vector(0.5f,0.5f));
        Boundary clear = new Boundary(0.5f, midpoint);
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
