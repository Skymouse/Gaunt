/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.world.adventurer;

import net.rowf.gaunt.engine.logic.control.Pilot;
import net.rowf.gaunt.world.behavior.Common;
import net.rowf.gaunt.world.dungeon.spawns.Reuser;
import net.rowf.gaunt.world.dungeon.spawns.Specification;

/**
 *
 * @author woeltjen
 */
public class Interactive extends Specification {
    public Interactive (Controller controller) {
        add(new Reuser(Common.Task.class, new Pilot(controller.directional())));
        //add(new Reuser(Common.Task.class, new Attack(controller.fire())));
    }
}
