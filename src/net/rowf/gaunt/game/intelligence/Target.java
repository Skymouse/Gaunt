/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.game.intelligence;

import net.rowf.gaunt.theory.Provider;
import net.rowf.gaunt.world.Component;
import net.rowf.gaunt.world.Entity;

/**
 *
 * @author woeltjen
 */
public class Target implements Component, Provider<Entity> {
    private Entity entity;

    public Target(Entity entity) {
        this.entity = entity;
    }
    
    @Override
    public Entity get() {
        return entity;
    }
}
