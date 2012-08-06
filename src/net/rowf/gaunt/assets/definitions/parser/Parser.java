/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.assets.definitions.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import net.rowf.gaunt.assets.definitions.Descriptor;
import net.rowf.gaunt.assets.definitions.Descriptor.Interpretation;
import net.rowf.gaunt.assets.definitions.Dictionary;
import net.rowf.gaunt.theory.Pair;
import net.rowf.gaunt.assets.definitions.interpretations.Declarator;
import net.rowf.gaunt.assets.definitions.interpretations.Descriptive;
import net.rowf.gaunt.assets.definitions.syntax.Syntax;

/**
 *
 * @author woeltjen
 */
public class Parser implements Transcriber {
    private static final List<Pair<Pattern,Interpretation>> GRAMMAR = 
            Arrays.asList(
        new Pair<Pattern, Interpretation>(Syntax.DECLARATOR, new Declarator()),
        new Pair<Pattern, Interpretation>(Syntax.PARAMETERIZED, new Descriptive()),
        new Pair<Pattern, Interpretation>(Syntax.PARAMETERLESS, new Descriptive())
    );
    
    private Iterable<String> text;
    public Parser(Iterable<String> text) {
        this.text = text;
    }

    @Override
    public void transcribe(Dictionary dictionary) {
        Iterator<String> iterator = text.iterator();
        
        List<String> passage = new ArrayList<String>();
        while (iterator.hasNext()) {
            String line = iterator.next().trim();
            if (line.isEmpty() && !passage.isEmpty()) {
                dictionary.record(new Descriptor(passage, GRAMMAR));
                passage.clear();
            } else if (!line.isEmpty()) {
                passage.add(line);
            }
        }
        if (!passage.isEmpty()) {
            dictionary.record(new Descriptor(passage, GRAMMAR));            
        }        
    }
    
}
