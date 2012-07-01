/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rowf.gaunt.editor.cartographer;

import java.util.Arrays;
import net.rowf.gaunt.engine.logic.control.Input;
import net.rowf.gaunt.engine.logic.control.Locator;
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

    public Cursor (Input mouse) {
        this(mouse, new Entity());
    }

    public Cursor (Input mouse, Entity entity) {
        this.locator   = new Locator(mouse);
        this.entity    = entity;
    }

    public void setEntity(Entity e) {
        entity = e;
    }

    @Override
    public Entity spawn() {
        return new Entity(Arrays.asList(locator, render, this));
    }

    private final Component render = new Render() {
        public void invoke(Entity e, View view) {
            for (Boundary b : e.get(Boundary.class))
                for (Painter p : entity.get(Painter.class))
                    p.paint(entity, b, view);
        }
    };

}
