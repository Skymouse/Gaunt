/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.assets.definitions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import net.rowf.gaunt.world.Component;
import net.rowf.gaunt.world.dungeon.spawns.Specification;
import net.rowf.gaunt.world.dungeon.spawns.Specifier;

/**
 *
 * @author woeltjen
 */
public class Dictionary {
    private Map<String, Qualifier>     qualifiers     = new HashMap<String, Qualifier>();
    private Map<String, Specification> specifications = new HashMap<String, Specification>();
    private Map<String, Class<? extends Component>> classes =
            new HashMap<String, Class<? extends Component>>();
    
    public Specification getSpecification(String name) {
        return specifications.get(name);
    }
    
    public Specification getSpecification(String name, final String parameter) {
        return qualifiers.get(name).getSpecification(parameter);
    }
        
    public <T> void recordQuality(String name, Quality<T> quality, Parser<T> parser) {
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
    
    public interface Parser<T> {
        public T parse(String argument);
    }
    
    private class Qualifier<T> {
        private Parser<T>  parser;
        private Quality<T> quality;

        public Qualifier(Parser<T> parser, Quality<T> quality) {
            this.parser = parser;
            this.quality = quality;
        }

        public Specification getSpecification(final String parameter) {
            return quality.get(parser.parse(parameter));
        }                
    }
    
    public static final Parser<Integer> INTEGER_PARSER = new Parser<Integer>() {
        @Override
        public Integer parse(String argument) {
            try {
                return Integer.parseInt(argument);
            } catch (Exception e) {
                return 0;
            }
        }
    };
    
    public static final Parser<Float> FLOAT_PARSER = new Parser<Float>() {
        @Override
        public Float parse(String argument) {
            try {
                return Float.parseFloat(argument);
            } catch (Exception e) {
                return 0f;
            }
        }
    };
    
    public static final Parser<String> STRING_PARSER = new Parser<String>() {
        @Override
        public String parse(String argument) {
            try {
                return argument;
            } catch (Exception e) {
                return argument;
            }
        }
    };
    
    public Parser<Class<? extends Component>> getComponentClassParser() {
        return componentClassParser;
    }
    
    private final Parser<Class<? extends Component>> componentClassParser =
        new Parser<Class<? extends Component>>() {       
        @Override
        public Class<? extends Component> parse(String argument) {
            if (classes.containsKey(argument)) {
                return classes.get(argument);
            } else {
                return Component.class;
            }
        }                
    };
 
}
