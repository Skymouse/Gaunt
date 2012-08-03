/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.game.projectile;

import net.rowf.gaunt.theory.Provider;
import net.rowf.gaunt.world.Component;
import net.rowf.gaunt.world.Entity;

/**
 *
 * @author woeltjen
 */
public class Owner implements Component, Provider<Entity> {
    private Entity owner;

    public Owner(Entity owner) {
        this.owner = owner;
    }

    @Override
    public Entity get() {
        return owner;
    }
    
}
