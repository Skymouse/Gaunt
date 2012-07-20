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
import net.rowf.gaunt.assets.level.Provider;
import net.rowf.gaunt.assets.level.catalog.Compendium;
import net.rowf.gaunt.editor.cartographer.Toolbox.Selection;
import net.rowf.gaunt.editor.cartographer.brush.Brush;
import net.rowf.gaunt.editor.cartographer.brush.Ink;
import net.rowf.gaunt.editor.cartographer.brush.Placer;
import net.rowf.gaunt.editor.cartographer.menu.Mapper;
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
public class Cartographer extends JPanel implements Provider<Ink> {
    private Collection<Module>      modules = new ArrayList<Module>();    
    private AtomicReference<Engine> engine  = new AtomicReference<Engine>();
    
    private Architect architect;    
    private Convertor<Index, Prototype> convertor;
    private Compendium                  compendium;
    
    private Toolbox toolbox;
    private Canvas  canvas;
    private Palette palette;

    private Entity  dungeon;
    private Cursor  cursor;
    private Brush   brush;
    
    private Ink     ink;
    
    
    public Cartographer(Architect architect, Compendium compendium) {
        this.architect = architect;
        this.compendium= compendium;
        this.convertor = compendium.getCatalog();        
        this.canvas    = new Canvas();
        this.palette   = new Palette(convertor);
        this.toolbox   = new Toolbox(this, new Mapper(this));
        
        modules.add(new Renderer(canvas));
        modules.add(new Taskmaster());
        modules.add(new Ticker(100.0f));
        modules.add(updater);
        
        setLayout(new BorderLayout());
        add(toolbox,   BorderLayout.NORTH);
        //add(canvas,    BorderLayout.CENTER);
        add(new Viewer(canvas), BorderLayout.CENTER);
        add(palette,   BorderLayout.SOUTH);
        //add(new Zoomer(canvas), BorderLayout.WEST);
             
        
        Mouse mouse = new Mouse();
        cursor = new Cursor(mouse);
        canvas.addMouseListener(mouse);        
        canvas.addMouseMotionListener(mouse);
        canvas.setScale(1f);

        simulator.start();
       
        
        populate();
        
        this.ink = new Placer(architect, convertor, dungeon, palette);
        toolbox.addListener(new Selection() {
            @Override
            public void select(Brush b) {                
                cursor.setBrush(b);//Marker(ink, 5));
            }            
        });
    }

    @Override
    public Ink get() {
        return ink;
    }
    
    public Architect getArchitect() {
        return architect;
    }
    
    public void setArchitect(Architect architect) {
        this.architect = architect;
        populate();
    }
    
    public Compendium getCompendium() {
        return compendium;
    }
    
    public Prototype getLevel() {
        return new Level(architect.getPopulator(convertor));
    }
    
    public void populate() {
        //TODO: Listeners!
        World w = new World();
        w.addEntity(dungeon = getLevel().spawn());
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

//TODO: Too long, disentangle
