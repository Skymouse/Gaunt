/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rowf.gaunt.world.dungeon;

import java.util.Arrays;
import net.rowf.gaunt.engine.renderer.Sprite;
import net.rowf.gaunt.world.Component;
import net.rowf.gaunt.world.Entity;
import net.rowf.gaunt.world.Prototype;
import net.rowf.gaunt.world.behavior.Standard;

/**
 *
 * @author woeltjen
 */
public class Floor extends Prototype implements Component {
    private Sprite sprite;

    public Floor(Sprite sprite) {
        this.sprite = sprite;
    }

    @Override
    public Entity spawn() {
        return new Entity(Arrays.asList(
                this,
                sprite,
                Standard.RENDER
        ));
    }

}
