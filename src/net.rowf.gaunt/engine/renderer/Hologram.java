
package jgaunt.engine.renderer;

import jgaunt.world.Component;

/**
 *
 * @author woeltjen
 */
public interface Hologram extends Component {
    public Animation getAnimation(float angle);
}
