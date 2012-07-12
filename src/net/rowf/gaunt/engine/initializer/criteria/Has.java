/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.engine.initializer.criteria;

import net.rowf.gaunt.engine.initializer.Criterion;
import net.rowf.gaunt.world.Component;
import net.rowf.gaunt.world.Entity;

/**
 *
 * @author woeltjen
 */
public class Has implements Criterion {
    private Class<? extends Component> component;

    public Has(Class<? extends Component> component) {
        this.component = component;
    }
    
    @Override
    public boolean consider(Entity e) {
        return !e.get(component).isEmpty();
    } 
}
