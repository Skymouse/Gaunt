/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rowf.gaunt;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JFrame;
import net.rowf.gaunt.assets.Depot;
import net.rowf.gaunt.assets.level.Level;
import net.rowf.gaunt.assets.level.Populator;
import net.rowf.gaunt.assets.resource.Resources;
import net.rowf.gaunt.engine.Engine;
import net.rowf.gaunt.engine.Module;
import net.rowf.gaunt.engine.initializer.Criterion;
import net.rowf.gaunt.engine.initializer.Initialization;
import net.rowf.gaunt.engine.initializer.Initializer;
import net.rowf.gaunt.engine.logic.Thinker;
import net.rowf.gaunt.engine.logic.control.Pilot;
import net.rowf.gaunt.engine.logic.control.swing.Arrows;
import net.rowf.gaunt.engine.physics.Collider;
import net.rowf.gaunt.engine.physics.Physics;
import net.rowf.gaunt.engine.renderer.Renderer;
import net.rowf.gaunt.engine.renderer.Sprite;
import net.rowf.gaunt.engine.renderer.Watcher;
import net.rowf.gaunt.engine.renderer.swing.Canvas;
import net.rowf.gaunt.engine.timing.Ticker;
import net.rowf.gaunt.world.*;
import net.rowf.gaunt.world.behavior.Common.Move;
import net.rowf.gaunt.world.behavior.Common.Render;
import net.rowf.gaunt.world.behavior.Common.Think;
import net.rowf.gaunt.world.behavior.Holography;
import net.rowf.gaunt.world.behavior.Standard;
import net.rowf.gaunt.world.behavior.movement.Velocity;
import net.rowf.gaunt.world.components.Speed;
import net.rowf.gaunt.world.dungeon.Dungeon;
import net.rowf.gaunt.world.dungeon.spawns.Reuser;
import net.rowf.gaunt.world.dungeon.spawns.Specification;

/**
 *
 * @author woeltjen
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame  frame   = new JFrame("Gaunt's Dungeon");
        Canvas  canvas  = new Canvas();
        Arrows  keypad  = new Arrows(KeyEvent.VK_UP  , KeyEvent.VK_DOWN,
                                     KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);
        Watcher watcher = new Watcher();
        
        Specification player = new Specification();
        player.add(new Reuser(Think.class, new Pilot(keypad)));
        player.add(new Reuser(Think.class, watcher));
        player.add(new Reuser(Speed.class, new Speed(10)));
        Initialization initialization = new Initialization( 
                new Criterion() {
                    @Override
                    public boolean consider(Entity e) {
                        for (Boundary b : e.get(Boundary.class))
                            if(b.getMinimum().getX() + b.getMinimum().getY() > 20)
                                return false;
                        return !e.get(Holography.class).isEmpty(); //TODO: Normally player!
                    }
                },
                player,
                true
        );
        
        frame.setSize(640, 480);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(canvas);
        frame.setVisible(true);

        frame.addKeyListener(keypad);
        canvas.setCamera(watcher);

        
        World w = new World();

        List<Module> modules = new ArrayList<Module>();
        modules.add(new Renderer(canvas));
        modules.add(new Physics());
        modules.add(new Collider());
        modules.add(new Ticker(30.0));
        modules.add(new Thinker());
        modules.add(new Initializer(Collections.singleton(initialization)));

        Depot depot = new Resources();

        Sprite s = depot.retrieve(Sprite.class, "items", null);
        Dungeon d = new Level(depot.retrieve(Populator.class, "map01"));


        
        Render r = Standard.RENDER;
        Move   m = new Velocity(new Vector(0.1f,0.1f));

        Entity e = new Entity();
        e.add(s);
        e.add(new Boundary( 1, 1));
        e.add(r);
        e.add(m);

        w.addEntity(d.spawn());
        w.addEntity(e);

        Engine engine = new Engine(w, modules);
        engine.run();
    }

}
