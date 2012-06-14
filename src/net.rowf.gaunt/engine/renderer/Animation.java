/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jgaunt.engine.renderer;

import jgaunt.world.Component;

/**
 *
 * @author woeltjen
 */
public interface Animation extends Component {
    public Sprite getSprite(int frame);
    public int    frames();
}
