/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rowf.gaunt.engine;

import java.util.Collection;
import net.rowf.gaunt.world.World;

/**
 *
 * @author woeltjen
 */
public class Engine {
    private World world;
    private Collection<Module> modules;
    private boolean active = true;

    public Engine (World world, Collection<Module> modules) {
        this.world   = world;
        this.modules = modules;
    }

    public void run() {
        while (active) for (Module m : modules) m.run(world);
    }

    public void halt() {
        active = false;
    }
}
