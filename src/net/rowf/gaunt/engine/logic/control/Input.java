/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.engine.logic.control;

/**
 *
 * @author woeltjen
 */
public interface Input {
    public void  poll();
    public int   axes();
    public float magnitude();
    public float get(int axis);
}
