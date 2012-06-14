/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.engine.initializer;

import java.util.ArrayList;
import java.util.Collection;
import net.rowf.gaunt.engine.Module;
import net.rowf.gaunt.world.Entity;
import net.rowf.gaunt.world.World;

/**
 *
 * @author woeltjen
 */
public class Initializer implements Module {
    private boolean initialized = false;

    private Collection<Initialization> initializations =
            new ArrayList<Initialization>();
    
    public Initializer(Collection<Initialization> initializations) {
        this.initializations.addAll(initializations);
    }
    
    @Override
    public void run(World w) {
        Collection<Initialization> stale = new ArrayList<Initialization>();
    
        for (Initialization initialization : initializations)
            for (Entity e : w.getEntities())
                if (initialization.consider(e)) {
                    initialization.initialize(e);
                    stale.add(initialization);
                    if (initialization.unique()) break; }
        
        initializations.removeAll(stale);
    }
    
    
    
}
