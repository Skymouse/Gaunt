/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.assets.level.catalog.categories;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.rowf.gaunt.assets.definitions.Dictionary;
import net.rowf.gaunt.theory.Convertor;
import net.rowf.gaunt.assets.level.Index;
import net.rowf.gaunt.world.entity.Prototype;
import net.rowf.gaunt.world.dungeon.spawns.Specification;

/**
 *
 * @author woeltjen
 */
public class Numerology implements Convertor<Index, Specification> {
    private static final Specification EMPTY = new Specification();
    private static final Pattern   FORMAT = Pattern.compile("\\s*(\\d+):\\s*(\\w+)\\s*");
    private Map<Integer, Specification> convertor = new HashMap<Integer, Specification>();
    private Index maximum = new Index(0);
    
    public Numerology(Iterable<String> text, Dictionary dictionary) {
        for (String line : text) {
            Matcher m = FORMAT.matcher(line);
            if (m.matches() && m.groupCount() >= 2) {
                Integer index = Integer.parseInt(m.group(1));
                convertor.put(index, dictionary.getSpecification(m.group(2)));
                if (index >= maximum.get()) maximum = new Index(index+1);
            }
        }
    }
    
    @Override
    public Specification convert(Index key) {
        if (convertor.containsKey(key.get())) return convertor.get(key.get());
        else                                  return EMPTY;
    }

    @Override
    public Index maximum() {
        return maximum;
    }
    
}
