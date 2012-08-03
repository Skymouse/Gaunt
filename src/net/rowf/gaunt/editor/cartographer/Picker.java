/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.editor.cartographer;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Collection;
import java.util.List;
import net.rowf.gaunt.engine.renderer.swing.Canvas;
import net.rowf.gaunt.world.components.Boundary;
import net.rowf.gaunt.world.Entity;
import net.rowf.gaunt.world.Vector;
import net.rowf.gaunt.world.behavior.Painter;

/**
 *
 * @author woeltjen
 */
public class Picker extends Canvas {
    private List<Entity> entities;
    private float        scale;
    
    public Picker (float pixels, List<Entity> entities) {
        this.scale    = pixels / Canvas.WORLD_TO_PIXEL;
        this.entities = entities;
        setPreferredSize(new Dimension ((int) pixels, (int) pixels));
    }
    
    @Override
    public void paint(Graphics g) {
        render(entities);
        render(entities);
        super.paint(g);
    }

    private void render (List<Entity> entities) {
        int rescale = (int) Math.ceil(Math.sqrt(entities.size()));
        super.setScale(scale / (int) rescale);
        
        int i = 0;
        for (Entity e : entities) {
            int u = i % rescale;
            int v = i / rescale;
            Boundary b = new Boundary(new Vector(u, v), new Vector(u+1, v+1));
            for (Painter p : e.get(Painter.class)) p.paint(e, b, this);
            i++;
        }
        
        swap();
    }
    
    @Override
    public boolean isOptimizedDrawingEnabled() {
        return false;
    }
    
}
