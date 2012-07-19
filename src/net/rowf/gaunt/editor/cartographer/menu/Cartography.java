/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.editor.cartographer.menu;

import java.io.File;

/**
 *
 * @author woeltjen
 */
public interface Cartography {
    public void create  (int width, int height);
    public void publish (File file);
    public void retrieve(File file);
    public void execute ();
}
