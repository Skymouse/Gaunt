/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.game.intelligence;

import net.rowf.gaunt.assets.definitions.Pair;
import net.rowf.gaunt.engine.initializer.Criterion;
import net.rowf.gaunt.world.Behavior;
import net.rowf.gaunt.world.Entity;

/**
 *
 * @author woeltjen
 */
public abstract class Conditional<T> extends Pair<Behavior<T>, Behavior<T>> implements Behavior<T> {
    private Criterion<Entity> condition;

    public Conditional(Criterion<Entity> condition, Behavior<T> then, Behavior<T> otherwise) {
        super(then, otherwise);
        this.condition = condition;
    }

    @Override
    public void invoke(Entity e, T argument) {
        (condition.consider(e) ? getLeft() : getRight()).invoke(e, argument);
    }
}
