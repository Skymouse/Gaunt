/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.world.behavior;

import net.rowf.gaunt.engine.renderer.Sprite;
import net.rowf.gaunt.engine.renderer.View;
import net.rowf.gaunt.world.components.Boundary;
import net.rowf.gaunt.world.Entity;
import net.rowf.gaunt.world.behavior.Common.Render;

/**
 *
 * @author woeltjen
 */
public abstract class Painter implements Render {

    @Override
    public void invoke(Entity e, View view) {
        for (Boundary b : e.get(Boundary.class))
            paint (e, b.resize(1.0f), view);
    }
    
    public abstract void paint(Entity e, Boundary boundary, View view);
    
    public void paint(Sprite sprite, Boundary boundary, View view) {
        view.draw(sprite, boundary);
    }
}
