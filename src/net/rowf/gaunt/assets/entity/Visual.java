/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.assets.entity;

import net.rowf.gaunt.assets.Depot;
import net.rowf.gaunt.engine.renderer.Sprite;
import net.rowf.gaunt.engine.renderer.View;
import net.rowf.gaunt.world.Boundary;
import net.rowf.gaunt.world.components.Parameterizable;

/**
 *
 * @author woeltjen
 */
public class Visual implements Sprite, Parameterizable<String> {
    private Sprite sprite = NONE;
    private Depot  depot;
    
    public Visual (Depot d) {
        this.depot = d;
    }

    @Override
    public <V extends View> Drawable<V> getDrawable(Class<V> view) {
        return sprite.getDrawable(view);
    }

    @Override
    public void set(String parameter) {
        sprite = depot.retrieve(Sprite.class, parameter, sprite);
    }

    @Override
    public Class<Parameterizable<String>> getReplicatedClass() {
        return (Class) Visual.class;
    }

    @Override
    public Parameterizable<String> replicate() {
        Visual v = new Visual(depot);
        v.sprite = sprite;
        return v;
    }

    private static final Drawable BLANK = new Drawable() {
        @Override
        public void draw(View display, Boundary b) {}            
    };
    
    private static final Sprite NONE = new Sprite() {
        @Override
        public <V extends View> Drawable<V> getDrawable(Class<V> view) {
            return BLANK;
        }        
    };
    
}
