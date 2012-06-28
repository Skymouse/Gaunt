/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rowf.gaunt.world.behavior;

import net.rowf.gaunt.engine.renderer.Sprite;
import net.rowf.gaunt.engine.renderer.View;
import net.rowf.gaunt.world.Boundary;
import net.rowf.gaunt.world.Entity;
import net.rowf.gaunt.world.behavior.Common.Render;

/**
 *
 * @author woeltjen
 */
public class Paint extends Painter implements Render {

    public void invoke(Entity e, View view) {
        for (Boundary boundary : e.get(Boundary.class))
            for (Sprite sprite : e.get(Sprite.class))
                paint(sprite, boundary, view);
    }

    
}
