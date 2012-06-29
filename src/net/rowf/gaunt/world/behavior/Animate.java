/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rowf.gaunt.world.behavior;

import net.rowf.gaunt.engine.renderer.Animation;
import net.rowf.gaunt.engine.renderer.View;
import net.rowf.gaunt.world.Boundary;
import net.rowf.gaunt.world.Entity;
import net.rowf.gaunt.world.World;
import net.rowf.gaunt.world.behavior.Common.Render;

/**
 *
 * @author woeltjen
 */
public class Animate extends Painter implements Render {

    @Override
    public void paint(Entity e, Boundary boundary, View view) {
        for (Animation anim : e.get(Animation.class))
            for (World w : e.first(World.class))
                paint(anim.getSprite(w.getTicks()), boundary, view);
    }

}
