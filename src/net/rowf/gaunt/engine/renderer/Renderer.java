/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rowf.gaunt.engine.renderer;

import net.rowf.gaunt.engine.Module;
import net.rowf.gaunt.world.Entity;
import net.rowf.gaunt.world.World;
import net.rowf.gaunt.world.behavior.Common.Render;

/**
 *
 * @author woeltjen
 */
public class Renderer implements Module {

    View view;

    public Renderer (View view) {
        this.view = view;
    }

    public void run(World w) {
        for (Entity e : w.getEntities())
            for (Render r : e.get(Render.class))
                r.invoke(e, view);
        view.update();
    }

}
