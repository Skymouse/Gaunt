/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jgaunt.world.monsters;

import java.util.Arrays;
import jgaunt.engine.renderer.Hologram;
import jgaunt.world.Component;
import jgaunt.world.Entity;
import jgaunt.world.Prototype;

/**
 *
 * @author woeltjen
 */
public class Monster extends Prototype {
    private Hologram hologram;

    public Monster (Hologram hologram) {

    }

    @Override
    public Entity spawn() {
        return new Entity(Arrays.<Component>asList(
                hologram
        ));
    }

}
