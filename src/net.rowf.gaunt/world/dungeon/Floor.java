/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jgaunt.world.dungeon;

import java.util.Arrays;
import jgaunt.engine.renderer.Sprite;
import jgaunt.world.Component;
import jgaunt.world.Entity;
import jgaunt.world.Prototype;
import jgaunt.world.behavior.Standard;

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
