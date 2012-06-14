/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.world.dungeon.spawns;

import net.rowf.gaunt.world.Component;
import net.rowf.gaunt.world.Entity;

/**
 *
 * @author woeltjen
 */
public class Reuser<T extends Component> implements Specifier<T> {
    private Class<T> specifiedClass;
    private T        component;
            
    public Reuser(Class<T> specifiedClass, T component) {
        this.specifiedClass = specifiedClass;
        this.component      = component;
    }
    
    @Override
    public T get(Entity e) {
        return component;
    }

    @Override
    public Class<T> getSpecifiedClass() {
        return specifiedClass;
    }
    
}
