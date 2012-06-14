/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rowf.gaunt.assets;

/**
 *
 * @author woeltjen
 */
public abstract class Storage<T> {
    public <T> T retrieve (Class<T> tClass, String key) {
        return (tClass.isAssignableFrom(getStoredClass())) ?
            (T) retrieve (key) : null;
    }
    public abstract T retrieve(String key);
    public abstract Class<T> getStoredClass();
}
