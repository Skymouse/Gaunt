/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rowf.gaunt.editor.cartographer.stylus;

import java.util.Arrays;
import net.rowf.gaunt.editor.cartographer.brush.Brush;
import net.rowf.gaunt.engine.logic.control.Input;
import net.rowf.gaunt.engine.logic.control.Locator;
import net.rowf.gaunt.engine.logic.control.swing.Mouse;
import net.rowf.gaunt.engine.renderer.View;
import net.rowf.gaunt.world.Boundary;
import net.rowf.gaunt.world.Component;
import net.rowf.gaunt.world.Entity;
import net.rowf.gaunt.world.Prototype;
import net.rowf.gaunt.world.behavior.Common.Render;
import net.rowf.gaunt.world.behavior.Painter;

/**
 *
 * @author woeltjen
 */
public class Cursor extends Prototype implements Component {
    private Component locator;
    private Entity    entity;
    private Stylus    stylus;

    public Cursor (Mouse mouse) {
        this(mouse, new Entity());
    }

    public Cursor (Mouse mouse, Entity entity) {
        this.locator   = new Locator(mouse.getPosition());
        this.entity    = entity;
        this.stylus    = new Stylus(1, Brush.EMPTY, mouse);
    }

    public void setEntity(Entity e) {
        entity = e;
    }
    
    public void setBrush(Brush b) {
        stylus.setBrush(b);
    }

    @Override
    public Entity spawn() {
        return new Entity(Arrays.asList(locator, render, stylus, stylus.getRepresentation(), this));
    }

    private final Component render = new Render() {
        public void invoke(Entity e, View view) {
            for (Boundary b : e.get(Boundary.class))
                for (Painter p : entity.get(Painter.class))
                    p.paint(entity, b, view);
        }
    };

}
