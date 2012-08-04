/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.world.adventurer;

import net.rowf.gaunt.engine.logic.control.Pilot;
import net.rowf.gaunt.engine.logic.control.Regulated;
import net.rowf.gaunt.game.projectile.Weapon;
import net.rowf.gaunt.theory.Function;
import net.rowf.gaunt.world.Entity;
import net.rowf.gaunt.world.World;
import net.rowf.gaunt.world.behavior.Behavior;
import net.rowf.gaunt.world.behavior.Common;
import net.rowf.gaunt.world.components.Speed;
import net.rowf.gaunt.world.dungeon.spawns.Reuser;
import net.rowf.gaunt.world.dungeon.spawns.Specification;

/**
 *
 * @author woeltjen
 */
public class Interactive extends Specification {
    public Interactive (Controller controller) {
        add(new Reuser(Common.Task.class, new Pilot(controller.directional())));
        add(new Reuser(Common.Task.class, new Regulated(SPEED_BASED, Weapon.class, controller.fire())));
        //TODO: Attack, special
    }
    
    private static final Function<Integer, Entity> SPEED_BASED = new Function<Integer, Entity>() {

        @Override
        public Integer evaluate(Entity input) {
            int s = 100;
            for (Speed speed : input.get(Speed.class))
                s = Math.min(s, 100/speed.get());
            return s;
        }
        
    };
}
