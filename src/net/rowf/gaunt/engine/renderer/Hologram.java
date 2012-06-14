
package net.rowf.gaunt.engine.renderer;

import net.rowf.gaunt.world.Component;

/**
 *
 * @author woeltjen
 */
public interface Hologram extends Component {
    public Animation getAnimation(float angle);
}
