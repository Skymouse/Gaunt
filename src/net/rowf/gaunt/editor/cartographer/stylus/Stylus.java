/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.editor.cartographer.stylus;

import net.rowf.gaunt.editor.cartographer.brush.Brush;
import net.rowf.gaunt.engine.logic.control.Control;
import net.rowf.gaunt.engine.logic.control.Input;
import net.rowf.gaunt.engine.logic.control.swing.Mouse;
import net.rowf.gaunt.engine.renderer.View;
import net.rowf.gaunt.world.Entity;
import net.rowf.gaunt.world.Vector;
import net.rowf.gaunt.world.World;
import net.rowf.gaunt.world.behavior.Common.Render;

/**
 *
 * @author woeltjen
 */
public class Stylus extends Control {
    private int   frequency;
    private Brush brush;
    
    private Input buttons;
    private Input position;

    boolean pressed = false;
    
    public Stylus(int frequency, Brush brush, Mouse input) {
        super(input);
        this.frequency = frequency;
        this.brush     = brush;
        this.buttons   = input.getButtons();
        this.position  = input.getPosition();
    }
    
    public void setBrush(Brush brush) {
        this.brush = brush;
    }
    
    @Override
    public int frequency() {
        return frequency;
    }

    @Override
    public void react(Entity e, World w, Input i) {
        boolean p = buttons.get(0) > 0;
        Vector  v = new Vector(position.get(0), position.get(1));
        if ( p &&  pressed ) brush.advance(v);
        if (!p &&  pressed ) brush.conclude(v);
        if ( p && !pressed ) brush.begin(v);
        if (!p && !pressed ) brush.prepare(v);
        pressed = p;
    }
    
    public Render getRepresentation() {
        return new Render() {

            @Override
            public void invoke(Entity e, View argument) {
                brush.getRepresentation().invoke(e, argument);
            }
            
        };
    }
}
