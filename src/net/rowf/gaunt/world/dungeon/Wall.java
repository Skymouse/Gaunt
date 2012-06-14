/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rowf.gaunt.world.dungeon;

import java.util.ArrayList;
import java.util.List;
import net.rowf.gaunt.engine.renderer.Sprite;
import net.rowf.gaunt.world.Component;
import net.rowf.gaunt.world.Entity;
import net.rowf.gaunt.world.Prototype;
import net.rowf.gaunt.world.behavior.Standard;
import net.rowf.gaunt.world.dungeon.walls.Demolish;
import net.rowf.gaunt.world.dungeon.walls.Hardness;

/**
 *
 * @author woeltjen
 */
public class Wall extends Prototype {
    private List<Component> components = new ArrayList<Component>();

    public Wall(Sprite sprite) {
        components.add(Standard.RENDER );
        components.add(Standard.COLLIDE);
        components.add(sprite);
    }

    public Wall(Sprite sprite, int hardness) {
        components.add(new Hardness(hardness));
        components.add(DEMOLISH);
    }

    @Override
    public Entity spawn() {
        return new Entity(components);
    }

    private static final Component DEMOLISH = new Demolish();
}
