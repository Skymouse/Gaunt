/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.world.components;

import net.rowf.gaunt.world.Replicant;

/**
 *
 * @author woeltjen
 */
public class Health extends Score implements Replicant<Health> {
 
    public Health(int health) {
        super(health);
    }

    @Override
    public Class getReplicatedClass() {
        return Health.class;
    }

    @Override
    public Health replicate() {
        return new Health(get());
    }
    
}
