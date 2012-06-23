/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.assets.definitions.grimoire;

import net.rowf.gaunt.assets.definitions.Dictionary;
import net.rowf.gaunt.assets.definitions.axioms.Axioms;
import net.rowf.gaunt.assets.definitions.parser.Parser;

/**
 *
 * @author woeltjen
 */
public class Grimoire extends Dictionary {
    public Grimoire (Iterable<String> text) {
        new Axioms(    ).transcribe(this);
        new Parser(text).transcribe(this);
    }
}
