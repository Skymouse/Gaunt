/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rowf.gaunt.assets;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author woeltjen
 */
public class Depot {
    private List<Storage<?>> stores = new ArrayList<Storage<?>>();

    public Depot (Collection<Storage<?>> stores) {
        this.stores.addAll(stores);
    }

    public <T> T retrieve (Class<T> tClass, String key) {
        return retrieve(tClass, key, null);
    }

    public <T> T retrieve (Class<T> tClass, String key, T otherwise) {
        T stored;
        for (Storage<?> store : stores) 
            if (store.getStoredClass().isAssignableFrom(tClass)) 
                if ( (stored = store.retrieve(tClass, key)) != null)
                    return stored;
        return otherwise;
    }

    public void addStore(Storage<?> store) {
        stores.add(store);
    }
}
