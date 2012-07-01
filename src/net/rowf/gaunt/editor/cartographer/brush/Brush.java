/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rowf.gaunt.editor.cartographer.brush;

import net.rowf.gaunt.world.Vector;

/**
 *
 * @author woeltjen
 */
public interface Brush {
    public void startDrawing    (Vector v);
    public void continueDrawing (Vector v);
    public void finishDrawing   (Vector v);
}
