/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.editor.cartographer.brush;

import java.awt.Color;
import java.awt.Graphics;
import net.rowf.gaunt.engine.renderer.View;
import net.rowf.gaunt.engine.renderer.swing.Canvas;
import net.rowf.gaunt.world.Entity;
import net.rowf.gaunt.world.Vector;
import net.rowf.gaunt.world.behavior.Common.Render;

/**
 *
 * @author woeltjen
 */
public class Line extends Bounded {

    public Line(Ink ink) {
        super(ink);
    }

    @Override
    public void apply(Vector start, float w, float h) {
        Vector delta = new Vector( (Math.abs(w) < Math.abs(h)) ? w / Math.abs(h) : Math.signum(w),
                                   (Math.abs(h) < Math.abs(w)) ? h / Math.abs(w) : Math.signum(h));
        for (int u = 0; u <= Math.round(Math.max(Math.abs(w), Math.abs(h))); u += 1.0, start = start.add(delta)) 
            apply(start);
    }

    @Override
    public Render getActiveRepresentation(Vector from, Vector to) {
        return new Representation(from, to);
    }
    
    private class Representation implements Render {
        private Vector from;
        private Vector to;

        public Representation(Vector from, Vector to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public void invoke(Entity e, View argument) {
            if (argument instanceof Canvas) {
                Canvas c = (Canvas) argument;
                Vector a = c.fromWorld(from);
                Vector b = c.fromWorld(to);//.add(a.scale(-1f));
                Graphics g = c.getDrawingBuffer();
                Color color = g.getColor();
                g.setColor(Color.WHITE);
                g.drawLine((int)a.getX(), (int)a.getY(), (int)b.getX(), (int)b.getY());
                g.setColor(color);
            }
        }
        
    }
}
