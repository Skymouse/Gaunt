/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rowf.gaunt.scheduler;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.rowf.gaunt.world.World;

/**
 *
 * @author woeltjen
 */
public class Scheduler {
    private List<Record> tasks = Collections.synchronizedList(new ArrayList<Record>());
    private boolean active = false;
    private static final float MAX_SLEEP = 1.0f;
    private World world;

    public void setWorld(World w) {
        world = w;
    }

    public World getWorld() {
        return world;
    }

    public void schedule(Task t, float delay) {
        long time = System.currentTimeMillis();
        tasks.add(new Record(time, time + (long)(delay*1000), t));
    }

    public void deschedule (Task t) {
        Record record = null;
        for (Record r : tasks) if (r.task.equals(t)) record = r;
        if (record != null) tasks.remove(record);
    }

    public void execute() {
        active = true;
        List<Record> records = new ArrayList<Record>();
        while (active && !tasks.isEmpty()) {
            long time = System.currentTimeMillis();
            float sleep = MAX_SLEEP;
            records.clear();
            records.addAll(tasks);
            for (Record r : records) {
                if (time > r.next) {
                    float interval = (time - r.last) / 1000.0f;
                    float next = r.task.execute(interval, this);
                    sleep = Math.min(next, sleep);
                    r.next = time + (long) (1000.0f * next);
                }
            }

            try {
                Thread.sleep((long) (sleep * 1000.0f));
            } catch (InterruptedException ex) {
                Logger.getLogger(Scheduler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private class Record {        
        private long last;
        private long next;
        private Task task;
        public Record(long last, long next, Task task) {
            this.last = last;
            this.next = next;
            this.task = task;
        }
    }
}
