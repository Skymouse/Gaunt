/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.world.dungeon.spawns;

import net.rowf.gaunt.world.Entity;
import net.rowf.gaunt.world.components.Replicant;

/**
 *
 * @author woeltjen
 */
public class Replicator<T extends Replicant<T>> implements Specifier<T> {
    private T replicant;
    
    public Replicator(Replicant<T> replicant) {
        this.replicant = replicant.replicate();
    }
    
    @Override
    public T get(Entity e) {
        return replicant.replicate();
    }

    @Override
    public Class<T> getSpecifiedClass() {
        return replicant.getReplicatedClass();
    }
    
}
