/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rowf.gaunt.engine.renderer;

import net.rowf.gaunt.world.components.Boundary;

/**
 *
 * @author woeltjen
 */
public interface View {
    public Boundary getVisibleBoundary();
    public void     draw(Sprite s, Boundary b);
    public void     update();
}
