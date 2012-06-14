/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jgaunt.world.dungeon.spawns;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import jgaunt.world.Entity;
import jgaunt.world.Prototype;

/**
 *
 * @author woeltjen
 */
public class Specification extends Prototype {
    private List<Contributor> contributors = new ArrayList<Contributor>();
    private Prototype parent = Prototype.EMPTY;
    
    public Specification() {
        
    }
    
    public Specification(Prototype parent) {
        this.parent = parent;
    }
    
    public Specification(Collection<Specifier> s) {
         contributors.add(new Adder(s));
    }
    
    public Specification apply(Prototype prototype) {
        Specification specification = new Specification(prototype);
        
        for (Contributor c : contributors) specification.contributors.add(c);
        
        return specification;
    }
    
    public Entity apply(Entity e) {
        for (Contributor c : contributors) c.contribute(e);
        return e;
    }
    
    @Override
    public Entity spawn() {
        return apply(parent.spawn());
    }
    
    public void add(Specifier s) {
        contributors.add(new Adder(s));
    }
    
    public void add(Collection<Specifier> s) {
        contributors.add(new Adder(s));
    }

    public void addset(Specifier s) {
        contributors.add(new Setter(s));
    }
    
    public void set(Collection<Specifier> s) {
        contributors.add(new Setter(s));
    }
    
    private interface Contributor {
        public void contribute(Entity e);
    }
    
    private class Adder implements Contributor {
        private List<Specifier> specifiers = new ArrayList<Specifier>();
        
        public Adder(Specifier specifier) {
            this.specifiers.add(specifier);
        }
        
        public Adder(Collection<Specifier> specifiers) {
            this.specifiers.addAll(specifiers);
        }
        
        @Override
        public void contribute(Entity e) {
            for (Specifier specifier : specifiers)
                e.add(specifier.get(e));
        }
    }
    
    public class Setter implements Contributor {
        private List<Specifier> specifiers = new ArrayList<Specifier>();
        
        public Setter(Specifier specifier) {
            this.specifiers.add(specifier);
        }
        
        public Setter(Collection<Specifier> specifiers) {
            this.specifiers.addAll(specifiers);
        }
        
        @Override
        public void contribute(Entity e) {
            for (Specifier specifier : specifiers)
                e.set(specifier.getSpecifiedClass(), specifier.get(e));
        }
    }
    
}
