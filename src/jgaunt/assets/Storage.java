/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jgaunt.assets;

/**
 *
 * @author woeltjen
 */
public interface Storage<T> {
    public <T> T retrieve (Class<T> tClass, String key);
    public Class<T> getStoredClass();
}
