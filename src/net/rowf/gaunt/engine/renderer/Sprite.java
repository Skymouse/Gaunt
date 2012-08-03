/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rowf.gaunt.engine.renderer;

import net.rowf.gaunt.world.components.Boundary;
import net.rowf.gaunt.world.Component;

/**
 *
 * @author woeltjen
 */
public interface Sprite extends Component {
    public <V extends View> Drawable<V> getDrawable(Class<V> view);

    public interface Drawable<V extends View> {
        public void draw(V display, Boundary b);
    }
}
