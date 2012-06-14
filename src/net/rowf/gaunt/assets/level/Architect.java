/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rowf.gaunt.assets.level;

import net.rowf.gaunt.world.Entity;
import net.rowf.gaunt.world.Prototype;

/**
 *
 * @author woeltjen
 */
public class Architect<T> implements Populator {

    private Convertor<Coordinate, T> plan;
    private Convertor<T, Prototype>  palette;

    public Architect (Convertor<Coordinate, T> plan,
                      Convertor<T, Prototype>  palette) {
        this.plan    = plan;
        this.palette = palette;
    }

    public Entity getEntity(int x, int y) {
        return palette.convert(plan.convert(new Coordinate(x, y))).spawn();
    }

    public int getHeight() {
        return plan.maximum().getX();
    }

    public int getWidth() {
        return plan.maximum().getY();
    }

}
