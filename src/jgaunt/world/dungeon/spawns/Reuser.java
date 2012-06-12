/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jgaunt.world.dungeon.spawns;

import jgaunt.world.Component;
import jgaunt.world.Entity;

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
