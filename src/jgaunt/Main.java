/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jgaunt;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JFrame;
import jgaunt.assets.Depot;

import jgaunt.assets.level.Level;
import jgaunt.assets.level.Populator;
import jgaunt.assets.resource.Resources;
import jgaunt.engine.Engine;
import jgaunt.engine.Module;
import jgaunt.engine.initializer.Criterion;
import jgaunt.engine.initializer.Initialization;
import jgaunt.engine.initializer.Initializer;
import jgaunt.engine.logic.Thinker;
import jgaunt.engine.logic.control.Pilot;
import jgaunt.engine.logic.control.swing.Arrows;
import jgaunt.engine.physics.Collider;
import jgaunt.engine.physics.Physics;
import jgaunt.engine.renderer.Renderer;
import jgaunt.engine.renderer.Sprite;
import jgaunt.engine.renderer.swing.Canvas;
import jgaunt.engine.timing.Ticker;
import jgaunt.world.*;
import jgaunt.world.behavior.Common.Move;
import jgaunt.world.behavior.Common.Render;
import jgaunt.world.behavior.Common.Think;
import jgaunt.world.behavior.Standard;
import jgaunt.world.behavior.movement.Velocity;
import jgaunt.world.dungeon.Dungeon;
import jgaunt.world.dungeon.spawns.Reuser;
import jgaunt.world.dungeon.spawns.Specification;

/**
 *
 * @author woeltjen
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame frame  = new JFrame("Gaunt's Dungeon");
        Canvas canvas = new Canvas();
        Arrows keypad = new Arrows(KeyEvent.VK_UP  , KeyEvent.VK_DOWN,
                                   KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);
        
        Specification controls = new Specification();
        controls.add(new Reuser(Think.class, new Pilot(keypad)));
        Initialization initialization = new Initialization( 
                new Criterion() {
                    @Override
                    public boolean consider(Entity e) {
                        for (Boundary b : e.get(Boundary.class))
                            if(b.getMinimum().getX() + b.getMinimum().getY() > 20)
                                return false;
                        return !e.get(Player.class).isEmpty();
                    }
                },
                controls,
                true
        );
        
        frame.setSize(640, 480);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(canvas);
        frame.setVisible(true);

        frame.addKeyListener(keypad);

        
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
        Move   m = new Velocity(new Position(0.1f,0.1f));

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
