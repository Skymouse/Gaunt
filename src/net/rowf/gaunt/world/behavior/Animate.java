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
public class Animate implements Render {

    public void invoke(Entity e, View view) {
        for (Boundary b : e.get(Boundary.class))
            for (Animation anim : e.get(Animation.class))
                for (World w : e.first(World.class))
                    view.draw(anim.getSprite(w.getTicks()), b);
    }

}
