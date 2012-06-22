/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.assets.definitions.parser;

import java.util.Collections;
import net.rowf.gaunt.assets.Depot;

/**
 *
 * @author woeltjen
 */
public class Preprocessor {
    private Depot depot;

    public Preprocessor(Depot depot) {
        this.depot = depot;
    } 
    
    public boolean handles(String line) {
        return line.startsWith("@");
    }
    
    public Iterable<String> expand(String line) {
        if (line.startsWith("@")) {
            Text t =  depot.retrieve(Text.class, line.substring(1).trim(), new Text(Collections.<String>emptyList()));
            return t;
        }
        return Collections.singleton(line);
    }
    

}
