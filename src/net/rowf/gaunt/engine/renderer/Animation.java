/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rowf.gaunt.engine.renderer;

import net.rowf.gaunt.world.Component;

/**
 *
 * @author woeltjen
 */
public interface Animation extends Component {
    public Sprite getSprite(int frame);
    public int    frames();
}
