/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.world.adventurer;

import net.rowf.gaunt.engine.logic.control.Input;

/**
 *
 * @author woeltjen
 */
public interface Controller {
    public Input directional();
    public Input fire();
    public Input special();
    public Input selector();
}
