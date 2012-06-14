/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jgaunt.world.behavior;

import jgaunt.engine.renderer.Animation;
import jgaunt.engine.renderer.View;
import jgaunt.world.Boundary;
import jgaunt.world.Entity;
import jgaunt.world.World;
import jgaunt.world.behavior.Common.Render;

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
