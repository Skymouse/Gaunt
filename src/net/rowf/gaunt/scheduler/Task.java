/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rowf.gaunt.scheduler;

import net.rowf.gaunt.world.World;

/**
 *
 * @author woeltjen
 */
public interface Task {
    public float execute(float duration, Scheduler s);
}
