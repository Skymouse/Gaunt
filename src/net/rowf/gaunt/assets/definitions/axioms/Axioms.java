/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.assets.definitions.axioms;

import java.util.Arrays;
import java.util.List;
import net.rowf.gaunt.assets.definitions.Dictionary;
import net.rowf.gaunt.assets.definitions.Dictionary.Decoder;
import net.rowf.gaunt.assets.definitions.Quality;
import net.rowf.gaunt.assets.definitions.parser.Transcriber;
import net.rowf.gaunt.world.Component;
import net.rowf.gaunt.world.Facing;
import net.rowf.gaunt.world.Replicant;
import net.rowf.gaunt.world.adventurer.Playable;
import net.rowf.gaunt.world.behavior.Standard;
import net.rowf.gaunt.world.components.*;
import net.rowf.gaunt.world.dungeon.spawns.Replicator;
import net.rowf.gaunt.world.dungeon.spawns.Reuser;
import net.rowf.gaunt.world.dungeon.spawns.Specification;
import net.rowf.gaunt.world.dungeon.spawns.Specifier;
import net.rowf.gaunt.world.dungeon.zone.Start;

/**
 *
 * @author woeltjen
 */
public class Axioms implements Transcriber {
    private static final List<Transcriber> AXIOMS = Arrays.<Transcriber> asList(
            new Axiom   (Standard.COLLIDE),
            new Axiom   (Standard.HOLOGRAPHY),
            new Axiom   (Standard.IMPACT),
            new Axiom   (Standard.RENDER),
            new Axiom   (Standard.WANDER),
            new Axiom   (new Playable()),
            new Axiom   (new Start()),
            new Axiom   (new Facing(0)),
            new Scored  (new Strength(0)),
            new Scored  (new Speed(0)),
            new Scored  (new Mind(0)),
            new Scored  (new Toughness(0)),
            new Scored  (new Health(0)),
            new Scored  (new Power(0)),
            new Scored  (new Experience(0)),
            new Numeric (new Size(1))
    );

    @Override
    public void transcribe(Dictionary dictionary) {
        for (Transcriber axiom : AXIOMS) axiom.transcribe(dictionary);           
    }
    
    private static class Axiom implements Transcriber {
        private Specifier specifier;
        private boolean   overrides;
        public Axiom(Component component) {
            this(component, true);
        }        
        public Axiom(Component component, boolean overrides) {
            specifier = new Reuser(component.getClass(), component);
            this.overrides = overrides;
        }

        @Override
        public void transcribe(Dictionary dictionary) {
            dictionary.recordSpecifier(
                    specifier.getSpecifiedClass().getSimpleName(), 
                    specifier, 
                    overrides);
        }
        
        
    }
    
    private static abstract class Qualitative<C extends Component, T> 
        implements Quality<T>, Transcriber {
        private boolean  overrides;
        private String   name;
        private Class<C> c;
        public Qualitative(Class<C> c) {
            this(c, true);
        }
        public Qualitative(Class<C> c, boolean o) {
            this.name      = c.getSimpleName();
            this.c         = c;
            this.overrides = o;
        }
        public String getName() {
            return name;
        }
        @Override
        public Specification get(T parameter) {
            Specification specification = new Specification();
            C component = create(parameter);
            Specifier specifier = (component instanceof Replicant) ?
                    new Replicator((Replicant) component) :
                    new Reuser(c, component);            
            if (overrides()) specification.set(specifier);
            else             specification.add(specifier);
            return specification;
        }
        public boolean  overrides() {
            return overrides;
        }
        public abstract C create(T parameter);
    }
    
    public static abstract class Parameterized<C extends Parameterizable<T>, T> extends Qualitative<C, T> {
        private C component;
        public Parameterized(C component) {
            this(component, true);
        }
        public Parameterized(C component, boolean o) {
            super((Class<C>) component.getClass(), o);
            this.component = component;
        }
        @Override
        public C create(T parameter) {
            C clone = (C) component.replicate();
            clone.set(parameter);
            return clone;
        }        
        @Override
        public void transcribe(Dictionary dictionary) {
            dictionary.recordQuality(getName(), this, getDecoder());
        }
        public abstract Decoder<T> getDecoder();
    }
    
    private static class Numeric<C extends Parameterizable<Float>> extends Parameterized<C, Float> {
        public Numeric(C component, boolean o) {
            super(component, o);
        }

        public Numeric(C component) {
            super(component);
        }

                
        @Override
        public Decoder<Float> getDecoder() {
            return Dictionary.FLOAT_DECODER;
        }
    }

    private static class Scored<C extends Parameterizable<Integer>> extends Parameterized<C, Integer> {

        public Scored(C component, boolean o) {
            super(component, o);
        }

        public Scored(C component) {
            super(component);
        }
       
        
        @Override
        public Decoder<Integer> getDecoder() {
            return Dictionary.INTEGER_DECODER;
        }
    }
    
    private static class Textual<C extends Parameterizable<String>> extends Parameterized<C, String> {

        public Textual(C component, boolean o) {
            super(component, o);
        }

        public Textual(C component) {
            super(component);
        }
        
        
        @Override
        public Decoder<String> getDecoder() {
            return Dictionary.STRING_DECODER;
        }
    }
    
}
