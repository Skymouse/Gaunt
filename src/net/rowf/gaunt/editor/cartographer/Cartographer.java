/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.editor.cartographer;

import net.rowf.gaunt.editor.cartographer.stylus.Cursor;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicReference;
import javax.swing.JPanel;
import net.rowf.gaunt.assets.level.Convertor;
import net.rowf.gaunt.assets.level.Index;
import net.rowf.gaunt.assets.level.Level;
import net.rowf.gaunt.editor.cartographer.brush.Brush;
import net.rowf.gaunt.editor.cartographer.brush.Ink;
import net.rowf.gaunt.editor.cartographer.brush.Marker;
import net.rowf.gaunt.editor.cartographer.brush.Placer;
import net.rowf.gaunt.engine.Engine;
import net.rowf.gaunt.engine.Module;
import net.rowf.gaunt.engine.logic.Taskmaster;
import net.rowf.gaunt.engine.logic.control.swing.Mouse;
import net.rowf.gaunt.engine.renderer.Renderer;
import net.rowf.gaunt.engine.renderer.swing.Canvas;
import net.rowf.gaunt.engine.timing.Ticker;
import net.rowf.gaunt.world.Entity;
import net.rowf.gaunt.world.Prototype;
import net.rowf.gaunt.world.Vector;
import net.rowf.gaunt.world.World;

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
    private Brush   brush;
    
    public Cartographer(Architect architect, Convertor<Index, Prototype> convertor) {
        this.architect = architect;
        this.convertor = convertor;
        this.canvas    = new Canvas();
        this.palette   = new Palette(convertor);

        modules.add(new Renderer(canvas));
        modules.add(new Taskmaster());
        modules.add(new Ticker(100.0f));
        modules.add(updater);
        
        setLayout(new BorderLayout());
        //add(tools,   BorderLayout.NORTH);
        add(canvas, BorderLayout.CENTER);
        add(palette, BorderLayout.SOUTH);
        
        Mouse mouse = new Mouse();
        cursor = new Cursor(mouse);
        canvas.addMouseListener(mouse);        
        canvas.addMouseMotionListener(mouse);
        canvas.setScale(0.5f);

        simulator.start();
       
        
        populate();
        
        Ink ink = new Placer(architect, convertor, dungeon, new Index(0) {
            @Override
            public int get() {
                return palette.getPrimary();
            }            
        });
        cursor.setBrush(new Marker(ink,3.0f));//Marker(ink, 5));
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
    
    private class Adapter extends MouseAdapter {
        private Vector convert(MouseEvent me) {
            return canvas.toWorld(me.getX(), me.getY());
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            brush.advance(convert(e));
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            brush.prepare(convert(e));        
        }


        @Override
        public void mousePressed(MouseEvent e) {
            brush.begin(convert(e));
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            brush.conclude(convert(e));
        }
        
    };
}
