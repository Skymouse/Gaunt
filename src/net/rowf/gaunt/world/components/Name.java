/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.world.components;

import net.rowf.gaunt.world.Component;

/**
 *
 * @author woeltjen
 */
public class Name implements Component {
    private String name;
    public Name(String name) {
        this.name = name;
    }
    public String get() { return name; }
}
