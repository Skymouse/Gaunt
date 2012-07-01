/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rowf.gaunt.engine.logic.control.swing;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import net.rowf.gaunt.engine.logic.control.Input;
import net.rowf.gaunt.engine.renderer.swing.Canvas;
import net.rowf.gaunt.world.Vector;

/**
 *
 * @author woeltjen
 */
public class Mouse extends MouseAdapter implements MouseMotionListener, Input {
    private enum Axis {
        POSITION_X    ,
        POSITION_Y    ,
        BUTTONS_LEFT  ,
        BUTTONS_MIDDLE,
        BUTTONS_RIGHT ,
        MOVEMENT_X    ,
        MOVEMENT_Y    ,
        SCROLL
    }
    
    private float state  [];
    private float pending[];

    private final Input position, movement, buttons, scroll;

    public Mouse() {
        this.state    = initialStates();
        this.pending  = initialStates();

        this.position = new Substate(Axis.POSITION_X, 2);
        this.movement = new Substate(Axis.MOVEMENT_X, 2);
        this.buttons  = new Substate(Axis.BUTTONS_LEFT, 3);
        this.scroll   = new Substate(Axis.BUTTONS_LEFT, 3);
    }

    public Input getButtons() {
        return buttons;
    }

    public Input getMovement() {
        return movement;
    }

    public Input getPosition() {
        return position;
    }

    public Input getScroll() {
        return scroll;
    }

    private float[] initialStates() {
        float[] f = new float[axes()];
        for (int i = 0; i < axes(); i++) f[i] = 0f;
        return f;
    }

    public int axes() {
        return Axis.values().length;
    }

    public float get(int axis) {
        return state[axis % axes()];
    }

    public float magnitude() {
        return magnitude(0, axes());
    }

    public void poll() {
        System.arraycopy(pending, 0, state, 0, axes());
        pending[Axis.SCROLL.ordinal()] = 0f;
    }


    private boolean hover = false;
    @Override public void mouseEntered(MouseEvent e) { hover = true ; }
    @Override public void mouseExited (MouseEvent e) { hover = false; }

    @Override
    public void mousePressed(MouseEvent e) {
        if (hover) pending[buttonAxis(e.getButton()).ordinal()] = 1f;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        pending[buttonAxis(e.getButton()).ordinal()] = 0f;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Vector v = (e.getSource() instanceof Canvas) ?
                   ((Canvas) e.getSource()).toWorld(e.getX(), e.getY()) :
                   new Vector(e.getX(), e.getY());
        pending[Axis.POSITION_X.ordinal()] = v.getX();
        pending[Axis.POSITION_Y.ordinal()] = v.getY();
        pending[Axis.MOVEMENT_X.ordinal()] = v.getX() - state[Axis.POSITION_X.ordinal()];
        pending[Axis.MOVEMENT_Y.ordinal()] = v.getY() - state[Axis.POSITION_X.ordinal()];;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        pending[Axis.SCROLL.ordinal()] += e.getScrollAmount();
    }

    private Axis buttonAxis(int button) {
        if (button == MouseEvent.BUTTON1) return Axis.BUTTONS_LEFT;
        if (button == MouseEvent.BUTTON2) return Axis.BUTTONS_MIDDLE;
        if (button == MouseEvent.BUTTON3) return Axis.BUTTONS_RIGHT;
        return Axis.BUTTONS_LEFT;        
    }

    private float magnitude(int start, int count) {
        float sum = 0f;
        for (int i = start; i < start + count; i++) sum += state[i] * state[i];
        return sum;
    }

    private class Substate implements Input {
        private int start;
        private int count;

        public Substate(Axis start, int count) {
            this.start = start.ordinal();
            this.count = count;
        }

        public int axes() {
            return count;
        }

        public float get(int axis) {
            return Mouse.this.get(start + axis);
        }

        public float magnitude() {
            return Mouse.this.magnitude(start, count);
        }

        public void poll() {
            Mouse.this.poll();
        }

    }

}
