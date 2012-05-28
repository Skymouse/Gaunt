/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jgaunt.world.dungeon;

import java.util.ArrayList;
import java.util.List;
import jgaunt.engine.renderer.Sprite;
import jgaunt.world.Component;
import jgaunt.world.Entity;
import jgaunt.world.Prototype;
import jgaunt.world.behavior.Standard;
import jgaunt.world.dungeon.walls.Demolish;
import jgaunt.world.dungeon.walls.Hardness;

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
