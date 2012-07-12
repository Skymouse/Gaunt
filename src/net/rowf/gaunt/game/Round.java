/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.game;

import java.util.Arrays;
import java.util.List;
import net.rowf.gaunt.engine.Engine;
import net.rowf.gaunt.engine.Module;
import net.rowf.gaunt.engine.logic.Taskmaster;
import net.rowf.gaunt.engine.physics.Collider;
import net.rowf.gaunt.engine.physics.Physics;
import net.rowf.gaunt.engine.renderer.Renderer;
import net.rowf.gaunt.engine.renderer.View;
import net.rowf.gaunt.engine.timing.Ticker;
import net.rowf.gaunt.game.start.Starter;
import net.rowf.gaunt.world.Component;
import net.rowf.gaunt.world.Entity;
import net.rowf.gaunt.world.Prototype;
import net.rowf.gaunt.world.World;

/**
 *
 * @author woeltjen
 */
public class Round implements Game {
    private Engine          engine;
    
    public Round(Prototype player, View view, List<Prototype> initial) {
        //Initialization initialization = 
                //new Injector(new Has(Start.class), player, true);
        World world = new World();        
        world.addEntity(new Entity(Arrays.<Component>asList(new Starter(player, initial))));
        
        //for (Prototype p : initial) world.addEntity(p.spawn());
        this.engine = new Engine(world,
                Arrays.<Module>asList(
                    new Renderer(view),
                    new Physics(),
                    new Collider(),
                    new Ticker(30.0),
                    new Taskmaster()
                    //new Initializer(Arrays.asList(initialization))
                )
        );
    }
    
    public void begin() {       
        engine.run();
    }
    
    public void stop() {
        engine.halt();
    }
}
