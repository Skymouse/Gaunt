/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jgaunt.world.dungeon;

import jgaunt.engine.renderer.View;
import jgaunt.world.Entity;
import jgaunt.world.behavior.Common.Render;


/**
 *
 * @author woeltjen
 */
public class Dungeon extends Entity {

    public Dungeon() {
        add (new DungeonRender());
    }

    private class DungeonRender implements Render {

        public void invoke(Entity e, View argument) {

        }

    }
}
