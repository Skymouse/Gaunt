/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.assets.definitions;

import net.rowf.gaunt.assets.definitions.Descriptor.Description;
import net.rowf.gaunt.world.components.Name;
import net.rowf.gaunt.world.dungeon.spawns.Reuser;
import net.rowf.gaunt.world.dungeon.spawns.Specification;

/**
 *
 * @author woeltjen
 */
public class Definition extends Specification {
    public Definition (Dictionary dictionary, Descriptor descriptor) {
        inherit(dictionary.getSpecification(descriptor.getParent()));
        for (Description d : descriptor.getEntries()) 
            inherit(d.hasParameter() ?
                    dictionary.getSpecification(d.getLeft(), d.getRight()) :
                    dictionary.getSpecification(d.getLeft()));
        set (new Reuser(Name.class, new Name(descriptor.getName())));
    }
}
