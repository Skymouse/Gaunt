/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jgaunt.engine.renderer;

import jgaunt.world.Boundary;

/**
 *
 * @author woeltjen
 */
public interface View {
    public Boundary getVisibleBoundary();
    public void     draw(Sprite s, Boundary b);
    public void     update();
}
