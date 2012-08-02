/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.assets.definitions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.rowf.gaunt.assets.definitions.syntax.Decoder;
import net.rowf.gaunt.world.Component;
import net.rowf.gaunt.world.Entity;
import net.rowf.gaunt.world.Prototype;
import net.rowf.gaunt.world.dungeon.spawns.Specification;
import net.rowf.gaunt.world.dungeon.spawns.Specifier;

/**
 *
 * @author woeltjen
 */
public class Dictionary {
    private static final Specification UNSPECIFIED = new Specification() {
        @Override
        public Entity spawn() {
            return new Entity();
        }        
    };

    private Map<String, Qualifier>     qualifiers     = new HashMap<String, Qualifier>();
    private Map<String, Specification> specifications = new HashMap<String, Specification>();
    private Map<String, Class<? extends Component>> classes =
            new HashMap<String, Class<? extends Component>>();
    
    public Specification getSpecification(String name) {
        return specifications.containsKey(name) ? 
               specifications.get(name) : UNSPECIFIED;
    }
    
    public Specification getSpecification(String name, final String parameter) {
        return qualifiers.containsKey(name) ? 
               qualifiers.get(name).getSpecification(parameter) : UNSPECIFIED;
    }
        
    public <T> void recordQuality(String name, Quality<T> quality, Decoder<T> parser) {
        qualifiers.put(name, new Qualifier(parser, quality));
    }
    
    public void recordSpecifier(String name, Specifier specifier, boolean overrides) {
        Specification specification = new Specification();
        if (overrides) specification.set(Collections.singleton(specifier));
        else           specification.add(Collections.singleton(specifier));
        specifications.put(name, specification);
    }
    
    public void recordSpecification(String name, Specification specification) {
        specifications.put(name, specification);
    }
    
    public void record (Descriptor descriptor) {
        specifications.put(descriptor.getName(), new Definition(this, descriptor));
    }
    

    private class Qualifier<T> {
        private Decoder<T> decoder;
        private Quality<T> quality;

        public Qualifier(Decoder<T> decoder, Quality<T> quality) {
            this.decoder = decoder;
            this.quality = quality;
        }

        public Specification getSpecification(final String parameter) {
            return quality.get(decoder.decode(parameter));
        }                
    }
    
  
    public Decoder<Class<? extends Component>> getComponentClassParser() {
        return componentClassParser;
    }
    
    private final Decoder<Class<? extends Component>> componentClassParser =
        new Decoder<Class<? extends Component>>() {       
        @Override
        public Class<? extends Component> decode(String argument) {
            if (classes.containsKey(argument)) {
                return classes.get(argument);
            } else {
                return Component.class;
            }
        }                
    };
    
    public Decoder<Prototype> getSpecificationDecoder(final Pattern parser) {
        return new Decoder<Prototype>() {
            @Override
            public Prototype decode(String argument) {
                Matcher m = parser.matcher(argument);
                return (m.matches() ? getSpecification(m.group(1), m.group(2)) :
                                    getSpecification(argument));
            }
        };
    }
 
}
