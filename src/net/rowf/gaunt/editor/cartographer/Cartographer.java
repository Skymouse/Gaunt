/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.editor.cartographer;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicReference;
import javax.swing.JPanel;
import net.rowf.gaunt.assets.level.Convertor;
import net.rowf.gaunt.assets.level.Index;
import net.rowf.gaunt.assets.level.Level;
import net.rowf.gaunt.engine.Engine;
import net.rowf.gaunt.engine.Module;
import net.rowf.gaunt.engine.logic.Thinker;
import net.rowf.gaunt.engine.logic.control.swing.Mouse;
import net.rowf.gaunt.engine.renderer.Renderer;
import net.rowf.gaunt.engine.renderer.swing.Canvas;
import net.rowf.gaunt.engine.timing.Ticker;
import net.rowf.gaunt.world.Boundary;
import net.rowf.gaunt.world.Entity;
import net.rowf.gaunt.world.Prototype;
import net.rowf.gaunt.world.Vector;
import net.rowf.gaunt.world.World;
import net.rowf.gaunt.world.dungeon.Dungeon;

/**
 *
 * @author woeltjen
 */
public class Cartographer extends JPanel {
    private Collection<Module>      modules = new ArrayList<Module>();    
    private AtomicReference<Engine> engine  = new AtomicReference<Engine>();
    
    private Architect architect;    
    private Convertor<Index, Prototype> convertor;
    
    private Canvas  canvas;
    private Palette palette;

    private Entity  dungeon;
    private Cursor  cursor;
    
    public Cartographer(Architect architect, Convertor<Index, Prototype> convertor) {
        this.architect = architect;
        this.convertor = convertor;
        this.canvas    = new Canvas();
        this.palette   = new Palette(convertor);

        modules.add(new Renderer(canvas));
        modules.add(new Thinker());
        modules.add(new Ticker(30.0f));
        modules.add(updater);
        
        setLayout(new BorderLayout());
        //add(tools,   BorderLayout.NORTH);
        add(canvas, BorderLayout.CENTER);
        add(palette, BorderLayout.SOUTH);
        
        Mouse mouse = new Mouse();
        cursor = new Cursor(mouse.getPosition());
        canvas.addMouseListener(listener);
        canvas.addMouseMotionListener(mouse);
        canvas.setScale(0.5f);

        simulator.start();
        
        populate();
    }
    
    public void populate() {
        //TODO: Listeners!
        World w = new World();
        w.addEntity(dungeon = new Level(architect.getPopulator(convertor)).spawn());
        w.addEntity(cursor.spawn());
        Engine old = engine.get();
        if (old != null) old.halt();
        engine.compareAndSet(old, new Engine(w, modules));
    }

    private final Module updater = new Module() {
        public void run(World w) {
            cursor.setEntity(palette.getExample(palette.getPrimary()));
        }
    };
    
    private final Thread simulator = new Thread() {
        @Override
        public void run() {
            while (true) { 
                Engine e = engine.get();
                if (e != null) e.run();
            }
        }
    };
    
    private final MouseListener listener = new MouseListener() {

        @Override
        public void mouseClicked(MouseEvent me) {
            Vector v = canvas.toWorld(me.getX(), me.getY());
            Vector midpoint = new Vector((float) Math.floor(v.getX()),
                                         (float) Math.floor(v.getY()))
                                         .add(new Vector(0.5f,0.5f));
            Boundary clear = new Boundary(0.5f, midpoint);
            architect.set((int) v.getX(), (int) v.getY(), palette.getPrimary());
            Prototype p = convertor.convert(new Index(palette.getPrimary()));
            for (World w : dungeon.first(World.class))
                for (Entity e : w.getEntities())
                    if (e != dungeon && e.get(Cursor.class).isEmpty())
                        for (Boundary b : e.get(Boundary.class))
                            if (b.overlaps(clear))
                                w.removeEntity(e);
            for (Dungeon d : dungeon.first(Dungeon.class))
                d.setEntity((int) v.getX(), (int) v.getY(), p.spawn());
            //populate();
        }

        @Override
        public void mouseEntered(MouseEvent me) {
        }

        @Override
        public void mouseExited(MouseEvent me) {
        }

        @Override
        public void mousePressed(MouseEvent me) {
        }

        @Override
        public void mouseReleased(MouseEvent me) {
        }
        
    };
}
