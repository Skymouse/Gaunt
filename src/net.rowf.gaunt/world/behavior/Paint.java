/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jgaunt.world.behavior;

import jgaunt.engine.renderer.Sprite;
import jgaunt.engine.renderer.View;
import jgaunt.world.Boundary;
import jgaunt.world.Entity;
import jgaunt.world.behavior.Common.Render;

/**
 *
 * @author woeltjen
 */
public class Paint implements Render {

    public void invoke(Entity e, View view) {
        for (Boundary boundary : e.get(Boundary.class))
            for (Sprite sprite : e.get(Sprite.class))
                view.draw(sprite, boundary);
    }

}
