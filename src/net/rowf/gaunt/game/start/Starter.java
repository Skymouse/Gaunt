/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.game.start;

import java.util.Arrays;
import java.util.List;
import net.rowf.gaunt.engine.initializer.Injector;
import net.rowf.gaunt.engine.initializer.criteria.Has;
import net.rowf.gaunt.world.Component;
import net.rowf.gaunt.world.Entity;
import net.rowf.gaunt.world.Prototype;
import net.rowf.gaunt.world.World;
import net.rowf.gaunt.world.behavior.Common.Task;
import net.rowf.gaunt.world.dungeon.zone.Start;

/**
 *
 * @author woeltjen
 */
public class Starter implements Task {
    private boolean started = false;
    
    private Prototype       player;
    private List<Prototype> initial;

    public Starter(Prototype player, List<Prototype> initial) {
        this.player  = player;
        this.initial = initial;
    }    
    
    private long next = 0;
    
    @Override
    public void invoke(Entity e, World world) {
        if (System.currentTimeMillis() > next) {
            started = false;
            next = System.currentTimeMillis() + 2500;
        }
        
        if  (started) return;
        else started = true;
        
        for (Entity entity : world.getEntities())
            world.removeEntity(entity);              
        
        for (Prototype p : initial)
            world.addEntity(p.spawn());
        
        world.addEntity(new Entity(Arrays.<Component>asList(
                new Initialize(
                    new Injector(new Has(Start.class), player, true)))));
        
        world.addEntity(new Entity(Arrays.<Component>asList(this)));                
    }
    
    
    
}
