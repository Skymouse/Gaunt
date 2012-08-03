/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.engine.logic.control.swing;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import net.rowf.gaunt.engine.logic.control.Input;

/**
 *
 * @author woeltjen
 */
public class Keyboard implements Input, KeyListener {
    private float   state[];

    private int     codes  [];
    private boolean pressed[]; 
    
    private Key     keys[];
    
    public Keyboard(int... codes) {
        this.codes   = codes;
        this.state   = new float  [codes.length];
        this.pressed = new boolean[codes.length];
        this.keys    = new Key    [codes.length];
        for (int i = 0; i < state.length; i++) state[i]   = 0f;
        for (int i = 0; i < state.length; i++) pressed[i] = false;               
    }
    
    @Override
    public void keyPressed(KeyEvent ke) {
        for (int i = 0; i < codes.length; i++)
            if (codes[i] == ke.getKeyCode())
                pressed[i] = true;
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        for (int i = 0; i < 4; i++)
            if (codes[i] == ke.getKeyCode())
                pressed[i] = false;
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }
    
    @Override
    public int axes() {
        return codes.length;
    }

    @Override
    public float get(int axis) {
        return state[axis];
    }

    @Override
    public float magnitude() {
        float magnitude = 0f;
        for (int i = 0; i < codes.length; i++) magnitude += state[i] * state[i];
        return (float) Math.sqrt(magnitude);
    }

    @Override
    public void poll() {
        for (int i = 0; i < state.length; i++) state[i] = pressed[i] ? 1f : 0f;
    }
    
    public Input key(int index) {
        return new Key(index);
    }

    private class Key implements Input {
        private int index;

        public Key(int index) {
            this.index = index;
        }

        @Override
        public int axes() {
            return 1;
        }

        @Override
        public float get(int axis) {
            return state[index];
        }

        @Override
        public float magnitude() {
            return state[index];
        }

        @Override
        public void poll() {
            state[index] = pressed[index] ? 1f : 0f;
        }
        
    }
}
