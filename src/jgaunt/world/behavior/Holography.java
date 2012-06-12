/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jgaunt.world.behavior;

import jgaunt.engine.renderer.Hologram;
import jgaunt.engine.renderer.View;
import jgaunt.world.Boundary;
import jgaunt.world.Entity;
import jgaunt.world.Facing;
import jgaunt.world.World;
import jgaunt.world.behavior.Common.Render;

/**
 *
 * @author woeltjen
 */
public class Holography implements Render {

    public void invoke(Entity e, View view) {
        for (Boundary b : e.get(Boundary.class))
            for (Hologram holo : e.get(Hologram.class))
                for (Facing f : e.get(Facing.class))
                    for (World w : e.first(World.class))
                        view.draw(holo.getAnimation(f.getAngle())
                                      .getSprite(w.getTicks() / 4), b);
    }

}
