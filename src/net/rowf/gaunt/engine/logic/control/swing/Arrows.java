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
public class Arrows implements Input, KeyListener {
    private float   state[] = { 0f , 0f };

    private int     codes  [] = { 0    , 0    , 0    , 0     };
    private boolean pressed[] = { false, false, false, false }; 
    
    public Arrows(int up, int down, int left, int right) {
        int  codes[] = { up , down , left, right };
        this.codes   = codes;
    }
    
    @Override
    public void keyPressed(KeyEvent ke) {
        for (int i = 0; i < 4; i++)
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
        return 2;
    }

    @Override
    public float get(int axis) {
        return state[axis];
    }

    @Override
    public float magnitude() {
        return (float) Math.sqrt(state[0]*state[0] + state[1]*state[1]);
    }

    @Override
    public void poll() {
        state[1] = (pressed[0] ? -1f : 0f) + (pressed[1] ? 1f : 0f);
        state[0] = (pressed[2] ? -1f : 0f) + (pressed[3] ? 1f : 0f);
    }
    
}
