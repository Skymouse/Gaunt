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
public class Paint extends Painter implements Render {

    @Override
    public void paint(Entity e, Boundary boundary, View view) {
        for (Sprite sprite : e.get(Sprite.class))
            paint(sprite, boundary, view);
    }

    
}
