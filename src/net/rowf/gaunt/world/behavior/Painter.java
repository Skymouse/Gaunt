/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.world.behavior;

import net.rowf.gaunt.engine.renderer.Sprite;
import net.rowf.gaunt.engine.renderer.View;
import net.rowf.gaunt.world.Boundary;

/**
 *
 * @author woeltjen
 */
public class Painter {
    public void paint(Sprite sprite, Boundary boundary, View view) {
        view.draw(sprite, boundary.resize(1.0f));
    }
}
