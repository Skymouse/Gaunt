/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rowf.gaunt.assets.level.catalog;

import java.util.List;
import net.rowf.gaunt.theory.Convertor;
import net.rowf.gaunt.assets.level.Index;

/**
 *
 * @author woeltjen
 */
public class Catalog<T> implements Convertor<Index, T> {
    private T[]   conversions;
    private T     defacto;
    private Index maximum;

    private Catalog(List<Convertor<Index, T>> convertors) {
        int max  = 0;

        for (Convertor<Index, T> convertor : convertors)
            max += convertor.maximum().get();

        conversions = (T[]) new Object[max];

        int base = 0;

        for (Convertor<Index, T> convertor : convertors) {
            for (int i = 0; i < convertor.maximum().get(); i++)
                conversions[base+i] = convertor.evaluate(new Index(i));
            base += convertor.maximum().get();
        }

        maximum = new Index(max);
    }

    public Catalog(List<Convertor<Index, T>> convertors, T defacto, int size) {
        this (convertors);
        this.defacto = defacto;
        this.maximum = new Index(size);
    }

    public T evaluate(Index key) {
        if (key.get() >= conversions.length) return defacto;
        if (conversions[key.get()] == null)  return defacto;
        return conversions[key.get()];
    }

    public Index maximum() {
        return maximum;
    }

}
