/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.editor.cartographer;

/**
 *
 * @author woeltjen
 */
public interface Matrix {
    public int  get(int x, int y);
    public void set(int x, int y, int value);
}
