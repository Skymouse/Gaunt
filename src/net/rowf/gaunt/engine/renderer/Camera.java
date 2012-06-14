/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.engine.renderer;

import net.rowf.gaunt.world.Boundary;

/**
 *
 * @author woeltjen
 */
public interface Camera {
    public Boundary getVisibleBoundary(float width, float height);
}
