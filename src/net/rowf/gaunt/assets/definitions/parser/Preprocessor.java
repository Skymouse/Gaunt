/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.assets.definitions.parser;

import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.rowf.gaunt.assets.Depot;

/**
 *
 * @author woeltjen
 */
public class Preprocessor {
    private static final Pattern INCLUDES = Pattern.compile("\\s*\\[\\s*(\\w+)\\s*\\]\\s*");
    private static final Text    EMPTY    = new Text(Collections.<String>emptyList()); 
    
    private Depot depot;

    public Preprocessor(Depot depot) {
        this.depot = depot;
    } 
    
    public boolean handles(String line) {
        return INCLUDES.matcher(line).matches();
    }
    
    public Iterable<String> expand(String line) {
        Matcher m = INCLUDES.matcher(line);
        return (m.matches()) ? depot.retrieve(Text.class, m.group(1), EMPTY) :
                    EMPTY;            
    }
    

}
