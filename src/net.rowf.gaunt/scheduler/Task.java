/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jgaunt.scheduler;

import jgaunt.world.World;

/**
 *
 * @author woeltjen
 */
public interface Task {
    public float execute(float duration, Scheduler s);
}
