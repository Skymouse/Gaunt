/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rowf.gaunt.assets.level;

import net.rowf.gaunt.theory.Convertor;
import net.rowf.gaunt.world.Entity;
import net.rowf.gaunt.world.entity.Prototype;

/**
 *
 * @author woeltjen
 */
public class Carpenter<T> implements Populator {

    private Convertor<Coordinate, T> plan;
    private Convertor<T, Prototype>  palette;

    public Carpenter (Convertor<Coordinate, T> plan,
                      Convertor<T, Prototype>  palette) {
        this.plan    = plan;
        this.palette = palette;
    }

    public Entity getEntity(int x, int y) {
        return palette.evaluate(plan.evaluate(new Coordinate(x, y))).spawn();
    }

    public int getHeight() {
        return plan.maximum().getX();
    }

    public int getWidth() {
        return plan.maximum().getY();
    }

}
