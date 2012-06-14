/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jgaunt.world.dungeon;

import java.util.Arrays;
import jgaunt.engine.renderer.View;
import jgaunt.world.*;
import jgaunt.world.behavior.Common.Collide;
import jgaunt.world.behavior.Common.Render;
import jgaunt.world.behavior.Common.Think;


/**
 *
 * @author woeltjen
 */
public class Dungeon extends Prototype implements Component { //TODO: Implements world?
    private int width;
    private int height;

    private Entity[] tiles;

    public Dungeon(int width, int height) {
        this.width  = width;
        this.height = height;
        tiles = new Entity[width * height];
        for (int i = 0; i < tiles.length; i++) tiles[i] = EMPTY;
    }
    
    public Entity setEntity(int x, int y, Entity e) {
        if (x < 0 || x >= width ) return EMPTY;
        if (y < 0 || y >= height) return EMPTY;

        e = e.clone();  //TODO: Just use e & warn in javadoc?
        e.set(Boundary.class, new Boundary(
                new Position(x * 1.0f, y * 1.0f),
                new Position(x * 1.0f + 1.0f, y * 1.0f + 1.0f)));
        e.set(Dungeon.class, this);
        
        return tiles[x + y * width] = e;
    }

    public Entity clearEntity(int x, int y) {
        if (x < 0 || x >= width ) return EMPTY;
        if (y < 0 || y >= height) return EMPTY;

        /* Duplicate some nearby floor if possible. */
        for (int d = 1; d < 5; d++)
            for (int u = x - d; u <= x + d; u++)
                for (int v = y - d; v <= y + d; v += Math.abs(x-u) < d ? d : 1)
                    for (Floor f : getEntity(u, v).first(Floor.class))
                        return setEntity(x, y, f.spawn());

        return tiles[x + y * width] = EMPTY;
    }

    public Entity clearEntity(Entity e) {
        for (Boundary b : e.get(Boundary.class))
            for (int x = (int) b.getMinimum().getX();
                 x     < (int) b.getMaximum().getX(); x++)
                 for (int y = (int) b.getMinimum().getY();
                      y     < (int) b.getMaximum().getY(); y++)
                     if (getEntity(x,y) == e)
                         return clearEntity(x, y);
        return EMPTY;
    }

    public Entity getEntity (int x, int y) {
        if (x < 0 || x >= width ) return EMPTY;
        if (y < 0 || y >= height) return EMPTY;
        return tiles[x + y * width];
    }

    @Override
    public Entity spawn() {
        return new Entity(Arrays.asList(
                this,
                RENDER,
                THINK,
                COLLIDE,
                new Boundary(new Position(0.0f, 0.0f), 
                             new Position(1.0f * width, 1.0f * height))
        ));
    }

    private static final Render RENDER = new Render() {
        public void invoke(Entity e, View view) {
            Boundary visible = view.getVisibleBoundary();

            int x1 = (int) Math.floor((double)visible.getMinimum().getX());
            int y1 = (int) Math.floor((double)visible.getMinimum().getY());
            int x2 = (int) Math.ceil ((double)visible.getMaximum().getX());
            int y2 = (int) Math.ceil ((double)visible.getMaximum().getY());

            for (Dungeon d : e.get(Dungeon.class))
                for (int x = x1; x < x2; x++)
                    for (int y = y1; y < y2; y++)
                        for (Render r : (e=d.getEntity(x, y)).get(Render.class))
                            r.invoke(e, view);
        }
    };
    
    private static final Think THINK = new Think() {

        @Override
        public void invoke(Entity e, World world) {       
            e.remove(this);
            for (Dungeon d : e.get(Dungeon.class))
                for (int x = 0; x < d.width; x++)
                    for (int y = 0; y < d.height; y++)
                        for (Think t : (e=d.getEntity(x, y)).get(Think.class) )
                            t.invoke(e, world);                        
        }
        
    };
    
    private static final Collide COLLIDE = new Collide() {

        @Override
        public void invoke(Entity e, Entity other) {
            for (Boundary b : other.get(Boundary.class)) {
                int x1 = (int) Math.floor(b.getMinimum().getX());
                int y1 = (int) Math.floor(b.getMinimum().getY());
                int x2 = (int) Math.ceil (b.getMaximum().getX());
                int y2 = (int) Math.ceil (b.getMaximum().getY());
                for (Dungeon d : e.get(Dungeon.class))
                    for (int x = x1; x <= x2; x++)
                        for (int y = y1; y <= y2; y++)
                            for (Collide c : d.getEntity(x, y).get(Collide.class))
                                c.invoke(d.getEntity(x,y), other);
            }
        }
        
    };

    private static final Entity   EMPTY = new Entity();
}
