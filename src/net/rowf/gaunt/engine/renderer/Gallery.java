/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rowf.gaunt.engine.renderer;

/**
 *
 * @author woeltjen
 */
public interface Gallery {
    public Sprite getSprite(int r, int c);
    public int    rows();
    public int    columns();
}
