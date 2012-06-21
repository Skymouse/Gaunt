/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.assets.definitions.interpretations;

import java.util.List;
import net.rowf.gaunt.assets.definitions.Descriptor;
import net.rowf.gaunt.assets.definitions.Descriptor.Interpretation;

/**
 *
 * @author woeltjen
 */
public class Descriptive implements Interpretation {

    @Override
    public void interpret(Descriptor target, List<String> elements) {
        if (elements.size() == 1)
            target.addEntry(elements.get(0));
        else if (elements.size() == 2)
            target.addEntry(elements.get(0), elements.get(1));
    }
    
}
