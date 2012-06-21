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
public class Declarator implements Interpretation {

    @Override
    public void interpret(Descriptor target, List<String> elements) {
        for (int i = 0; i < elements.size(); i++)
            if (i == 0) target.setName  (elements.get(i));
            else        target.setParent(elements.get(i));
    }
    
}
