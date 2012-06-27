/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.assets.definitions;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author woeltjen
 */
public class Descriptor {
    private List<Description> entries = new ArrayList<Description>();

    private String      name;
    private String      parent;

    public Descriptor(List<String>                        elements, 
                      List<Pair<Pattern, Interpretation>> syntax) {
        for (String element : elements) {
            for (Pair<Pattern, Interpretation> pair : syntax) {
                Matcher m = pair.getLeft().matcher(element);
                if (m.matches()) {
                    List<String> parameters = new ArrayList<String>();
                    for (int i = 1; i <= m.groupCount(); i++) 
                        parameters.add(m.group(i));
                    pair.getRight().interpret(this, parameters);
                }
            }
        }
    }
    
    public List<Description> getEntries() {
        return entries;
    }

    public String getName() {
        return name;
    }

    public String getParent() {
        return parent;
    }
    
    
    public void setName(String name) {
        this.name = name;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }
    
    public void addEntry(String left) {
        addEntry(left, null);
    }
    
    public void addEntry(String left, String right) {
        entries.add(new Description(left, right));
    }
    
    public class Description extends Pair<String, String> {
        public Description(String left, String right) {
            super(left, right);
        }
        
        public boolean hasParameter() {
            return getRight() != null;
        }
    }
    
    public interface Interpretation {
        public void interpret(Descriptor target, List<String> elements);
    }
    
}
