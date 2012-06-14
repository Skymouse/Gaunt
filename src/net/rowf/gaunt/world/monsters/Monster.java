/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rowf.gaunt.world.monsters;

import java.util.Arrays;
import net.rowf.gaunt.engine.renderer.Hologram;
import net.rowf.gaunt.world.Component;
import net.rowf.gaunt.world.Entity;
import net.rowf.gaunt.world.Prototype;

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
