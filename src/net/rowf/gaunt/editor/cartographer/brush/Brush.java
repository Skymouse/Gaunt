/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rowf.gaunt.editor.cartographer.brush;

import net.rowf.gaunt.engine.renderer.View;
import net.rowf.gaunt.world.Entity;
import net.rowf.gaunt.world.Vector;
import net.rowf.gaunt.world.behavior.Common.Render;

/**
 *
 * @author woeltjen
 */
public interface Brush {
    public void prepare  (Vector v);
    public void begin    (Vector v);
    public void advance  (Vector v);
    public void conclude (Vector v);

    public Render getRepresentation();

    public static final Render NONE = new Render() {
        public void invoke(Entity e, View argument) {}
    };
}
