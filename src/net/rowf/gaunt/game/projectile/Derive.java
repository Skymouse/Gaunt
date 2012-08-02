/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.game.projectile;

import net.rowf.gaunt.world.Component;
import net.rowf.gaunt.world.Entity;
import net.rowf.gaunt.world.World;
import net.rowf.gaunt.world.behavior.Common.Think;
import net.rowf.gaunt.world.components.Parameterizable;

/**
 *
 * @author woeltjen
 */
public class Derive<C extends Component> implements Think, Parameterizable<Class<C>> {
    private Class<C> componentClass;

    public Derive(Class<C> componentClass) {
        this.componentClass = componentClass;
    }
    
    @Override
    public void invoke(Entity e, World argument) {
        for (Owner o : e.get(Owner.class))
            for (C c : o.get().get(componentClass))
                e.set(componentClass, c);
        e.remove(this); // Only derive once!
    }

    @Override
    public void set(Class<C> parameter) {
        this.componentClass = parameter;
    }

    @Override
    public Class<Parameterizable<Class<C>>> getReplicatedClass() {
        return (Class) Derive.class;
    }

    @Override
    public Parameterizable<Class<C>> replicate() {
        return new Derive(componentClass);
    }
    
    public static final class None implements Component {}
    public static final Class<Component> NONE = (Class) None.class;
}
