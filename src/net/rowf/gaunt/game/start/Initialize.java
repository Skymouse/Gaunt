/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.game.start;

import net.rowf.gaunt.engine.initializer.Initialization;
import net.rowf.gaunt.world.Entity;
import net.rowf.gaunt.world.World;
import net.rowf.gaunt.world.behavior.Common.Task;

/**
 *
 * @author woeltjen
 */
public class Initialize implements Task {
    private Initialization initialization;

    public Initialize(Initialization initialization) {
        this.initialization = initialization;
    }
 
    @Override
    public void invoke(Entity e, World w) {
        for (Entity entity : w.getEntities())
            if (initialization.consider(entity)) {
                initialization.initialize(entity);
                if (initialization.unique()) {
                    w.removeEntity(e); 
                    break; }}                
    }
    
}
