
package net.rowf.gaunt.game.intelligence;

import net.rowf.gaunt.theory.Criterion;
import net.rowf.gaunt.world.behavior.Behavior;
import net.rowf.gaunt.world.Entity;
import net.rowf.gaunt.world.World;
import net.rowf.gaunt.world.behavior.Common.Think;

public class Decision extends Conditional<World> implements Think {
    public Decision(Criterion<Entity> condition, Behavior<World> then, Behavior<World> otherwise) {
        super(condition, then, otherwise);
    }
}
