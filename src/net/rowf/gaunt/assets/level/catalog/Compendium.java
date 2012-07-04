/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rowf.gaunt.assets.level.catalog;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.rowf.gaunt.assets.Depot;
import net.rowf.gaunt.assets.definitions.Dictionary;
import net.rowf.gaunt.assets.definitions.grimoire.Grimoire;
import net.rowf.gaunt.assets.definitions.parser.Text;
import net.rowf.gaunt.assets.level.Convertor;
import net.rowf.gaunt.assets.level.Index;
import net.rowf.gaunt.assets.level.catalog.categories.*;
import net.rowf.gaunt.engine.renderer.*;
import net.rowf.gaunt.engine.renderer.Sprite.Drawable;
import net.rowf.gaunt.engine.renderer.swing.Canvas;
import net.rowf.gaunt.world.Boundary;
import net.rowf.gaunt.world.Component;
import net.rowf.gaunt.world.Entity;
import net.rowf.gaunt.world.Prototype;
import net.rowf.gaunt.world.behavior.Common.Render;
import net.rowf.gaunt.world.dungeon.spawns.Specification;

/**
 *
 * @author woeltjen
 */
public class Compendium {
    private Depot      depot;
    private Dictionary dictionary;

    public Compendium(Depot depot) {
        this.depot = depot;
        this.dictionary = 
                new Grimoire(depot.retrieve(Text.class, "grimoire", Text.EMPTY));
    }

    public Catalog<Prototype> getCatalog() {
        List<Category> categories = new ArrayList<Category>();

        //TODO: Move this highly coupled stuff somewhere ???
        Gallery tiles   = depot.retrieve(Gallery.class, "wall_tiles");
        Gallery sprites = depot.retrieve(Gallery.class, "sprites");
        categories.add(new Floors (makeTileset(new Row(tiles, 0)))); // 0
        categories.add(new Walls  (makeTileset(new Row(tiles, 1)))); // 16
        categories.add(new Walls  (makeTileset(new Row(tiles, 2)))); // 32
        categories.add(new Walls  (makeTileset(new Row(tiles, 3)))); // 48
        categories.add(makeCreatures("00")); //64 creatures
        categories.add(makeCreatures("00")); //80 pickups
        categories.add(makeZones("zones"));  //96 spwans
        categories.add(new Numbers());

        /*Original:
         * 0   Floors
         * 16  Walls
         * 32  Breakables
         * 48  Doors
         * 64  Creatures
         * 80  Pickups
         * 96  Spawns
        */
        
        return new Catalog(categories, NONENTITY, 256);
    }
    
    public Catalog<Prototype> getHeroes() {
        return new Catalog(
                Collections.singletonList(makeHeroes("heroes")), 
                NONENTITY, 
                4);
    }
    
    private Category makeZones(String name) {
        Text index = depot.retrieve(Text.class, name);
        Convertor<Index, Specification> convertor = new Numerology(index, dictionary);
        return new Zones(new Specification(), convertor);
    }

    
    private Category makeHeroes(String name) {
        Gallery sprites = depot.retrieve(Gallery.class, name);
        Text    index   = depot.retrieve(Text.class,    name);
        //TODO: May need to distinguish index from text (name collisions)
        
        Convertor<Index, Specification> convertor = new Numerology(index, dictionary);
        return new Heroes(new Specification(), makeHologramSet(sprites), convertor);        
    }
    
    private Category makeCreatures(String name) {
        Gallery sprites = depot.retrieve(Gallery.class, name);
        Text    index   = depot.retrieve(Text.class,    name);
        //TODO: May need to distinguish index from text (name collisions)
        
        Convertor<Index, Specification> convertor = new Numerology(index, dictionary);
        return new Creatures(new Specification(), makeHologramSet(sprites), convertor);
    }
    
    private Convertor<Index, Sprite> makeTileset (final Animation anim) {
        return new Convertor<Index, Sprite>() {
            @Override
            public Sprite convert(Index key) {
                return anim.getSprite(key.get() % anim.frames());
            }
            @Override
            public Index maximum() {
                return new Index(anim.frames());
            };
        };
    }

    private Convertor<Index, Sprite> makeTileset (final Gallery gallery) {
        return new Convertor<Index, Sprite>() {
            public Sprite convert(Index key) {
                int index  = key.get();
                int row    = index % gallery.columns();
                int column = index / gallery.columns();
                return gallery.getSprite(row, column);
            }

            public Index maximum() {
                return new Index(gallery.rows() * gallery.columns());
            };
        };
    }
    
    private Convertor<Index, Hologram> makeHologramSet (final Gallery gallery) {
        return new Convertor<Index, Hologram>() {

            @Override
            public Hologram convert(Index key) {
                return new Rotary(
                        new Column (gallery, key.get() % gallery.columns()),
                        4,
                        -(float) (Math.PI / 3));
            }

            @Override
            public Index maximum() {
                return new Index(gallery.columns());
            }
            
        };
    }

    private static final Prototype NONENTITY  = new Prototype() {

        @Override
        public Entity spawn() {
            return new Entity();
        }

    };
    
    private class Numbers extends Category {
        private final Index SIZE = new Index(200);
        
        @Override
        public Index maximum() {
            return SIZE;
        }
        
        @Override
        public Prototype convert(Index key) {
            return new NumberedPrototype(key.get());
        }
        
    }
    
    private class NumberedPrototype extends Prototype 
        implements Render, Sprite, Drawable<Canvas> {

        private BufferedImage image;
        
        public NumberedPrototype (int number) {   
            this.image  = new BufferedImage(32,32,BufferedImage.TYPE_INT_RGB);
            
            Graphics g = image.getGraphics();
            g.setColor(Color.WHITE);
            g.drawString(Integer.toString(number), 16, 16);
        }
        
        @Override
        public Entity spawn() {
            return new Entity(Collections.<Component>singletonList(this));
        }

        @Override
        public void invoke(Entity e, View argument) {
            for (Boundary b : e.get(Boundary.class))
                for (Sprite s : e.get(Sprite.class))
                    argument.draw(s, b);
        }

        @Override
        public <V extends View> Drawable<V> getDrawable(Class<V> view) {
            if (Canvas.class.isAssignableFrom(view)) 
                return (Drawable<V>) this;
            else return null;
        }

        @Override
        public void draw(Canvas display, Boundary b) {
            display.draw(image, b);
        }
        
        
        
        
    }

}
