/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jgaunt.engine.renderer;

import jgaunt.engine.Module;
import jgaunt.world.Entity;
import jgaunt.world.World;
import jgaunt.world.behavior.Common.Render;

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
