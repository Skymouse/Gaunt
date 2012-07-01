/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rowf.gaunt.engine.logic.control;

import net.rowf.gaunt.world.Boundary;
import net.rowf.gaunt.world.Entity;
import net.rowf.gaunt.world.Vector;
import net.rowf.gaunt.world.World;

/**
 *
 * @author woeltjen
 */
public class Locator extends Controller {
    private static final Vector   NONE = new Vector(0f, 0f);
    private static final Boundary UNIT = new Boundary(1f);

    public Locator(Input input) {
        super(input);
    }

    @Override
    public int frequency() {
        return 1;
    }

    @Override
    public void react(Entity e, World w, Input i) {
        Boundary boundary = UNIT;
        for (Boundary b : e.first(Boundary.class))
            boundary = b;
        e.set(Boundary.class, 
              boundary.add(getVector(i).add(boundary.center().scale(-1f))));
    }

    private Vector getVector(Input i) {
        return (i.axes() >= 2) ? new Vector(i.get(0), i.get(1)) : NONE;
    }

}
