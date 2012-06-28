/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rowf.gaunt.assets.level.catalog.categories;

import net.rowf.gaunt.assets.level.Convertor;
import net.rowf.gaunt.assets.level.Index;
import net.rowf.gaunt.world.Prototype;

/**
 *
 * @author woeltjen
 */
public abstract class Category implements Convertor<Index, Prototype> {
    
    private static final Index MAXIMUM = new Index(16);

    public Index maximum() {
        return MAXIMUM;
    }
}
