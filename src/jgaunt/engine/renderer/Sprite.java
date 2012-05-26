/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jgaunt.engine.renderer;

import jgaunt.world.Boundary;
import jgaunt.world.Component;

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
