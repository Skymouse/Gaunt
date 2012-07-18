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
public class Box extends Bounded {
    private static final Vector AXES[] = { new Vector(1f, 0f), new Vector(0f, 1f) };

    public Box(Ink ink) {
        super(ink);
    }

    @Override
    public void apply(Vector start, float w, float h) {
        float[] count  = { (float) Math.round(w), (float) Math.round(h) };        
        for (int axis = 0, other = 1; axis < 2; axis++, other--)
            for (int edge = 0; edge < 2; edge++)
                for (float i = 0f; i <= Math.abs(count[axis]); i += 1f)
                    apply( start.add(AXES[other].scale(edge * count[other]))
                                .add(AXES[axis].scale(i * Math.signum(count[axis]))) );
        
    }

    @Override
    public Render getActiveRepresentation(Vector from, Vector to) {
        return new Representation(from, to);
    }
    
    private class Representation implements Render {
        private Vector from;
        private Vector to;

        public Representation(Vector from, Vector to) {
            this.from = new Vector(Math.min(from.getX(), to.getX()),
                                   Math.min(from.getY(), to.getY()));
            this.to   = new Vector(Math.max(from.getX(), to.getX()),
                                   Math.max(from.getY(), to.getY()));
        }

        @Override
        public void invoke(Entity e, View argument) {
            if (argument instanceof Canvas) {
                Canvas c = (Canvas) argument;
                Vector a = c.fromWorld(from);
                Vector b = c.fromWorld(to).add(a.scale(-1f));
                Graphics g = c.getDrawingBuffer();
                Color color = g.getColor();
                g.setColor(Color.WHITE);
                g.drawRect((int)a.getX(), (int)a.getY(), (int)b.getX(), (int)b.getY());
                g.setColor(color);
            }
        }
        
    }
    
}
