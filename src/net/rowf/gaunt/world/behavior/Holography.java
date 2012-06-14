/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rowf.gaunt.world.behavior;

import net.rowf.gaunt.engine.renderer.Hologram;
import net.rowf.gaunt.engine.renderer.View;
import net.rowf.gaunt.world.Boundary;
import net.rowf.gaunt.world.Entity;
import net.rowf.gaunt.world.Facing;
import net.rowf.gaunt.world.World;
import net.rowf.gaunt.world.behavior.Common.Render;

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
