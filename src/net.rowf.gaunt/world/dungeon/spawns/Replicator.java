/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jgaunt.world.dungeon.spawns;

import jgaunt.world.Entity;
import jgaunt.world.Replicant;

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
