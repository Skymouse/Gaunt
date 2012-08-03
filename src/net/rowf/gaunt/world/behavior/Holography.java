/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rowf.gaunt.world.behavior;

import net.rowf.gaunt.engine.renderer.Hologram;
import net.rowf.gaunt.engine.renderer.View;
import net.rowf.gaunt.world.components.Boundary;
import net.rowf.gaunt.world.Entity;
import net.rowf.gaunt.world.components.Facing;
import net.rowf.gaunt.world.World;
import net.rowf.gaunt.world.behavior.Common.Render;

/**
 *
 * @author woeltjen
 */
public class Holography extends Painter implements Render {

    @Override
    public void paint(Entity e, Boundary b, View view) {
        for (Hologram holo : e.get(Hologram.class))
            for (Facing f : e.get(Facing.class))
                for (World w : e.first(World.class))
                    paint(holo.getAnimation(f.getAngle())
                                    .getSprite(w.getTicks() / 4), b, view);
    }

}
