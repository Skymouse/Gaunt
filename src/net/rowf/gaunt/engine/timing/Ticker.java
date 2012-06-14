/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rowf.gaunt.engine.timing;

import net.rowf.gaunt.engine.Module;
import net.rowf.gaunt.world.World;

/**
 *
 * @author woeltjen
 */
public class Ticker implements Module {
    private long start;
    private long last;
    private long interval;
    private long remainder;

    public Ticker (double rate) {
        this.start    = System.currentTimeMillis();
        this.last     = start;
        this.interval = (long) (1000.0 / rate);
    }

    public void run(World w) {
        long now        = System.currentTimeMillis();
        long delta      = now - last + remainder;
        if (delta < interval) {
            try {
                Thread.sleep(interval - delta);
            } catch (InterruptedException ie) {
                // Oh well! We tried!
            }
            now = System.currentTimeMillis();
            delta = now - last + remainder;
        }
        w.tick((int) (delta / interval));
        remainder = delta % interval;
        last      = now;
    }

}
