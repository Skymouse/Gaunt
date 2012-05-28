/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jgaunt.assets.level.catalog.categories;

import jgaunt.assets.level.Convertor;
import jgaunt.assets.level.Index;
import jgaunt.world.Prototype;

/**
 *
 * @author woeltjen
 */
public abstract class Category implements Convertor<Index, Prototype> {
    
    private static final Index SIZE = new Index(16);

    public Index maximum() {
        return SIZE;
    }
}
