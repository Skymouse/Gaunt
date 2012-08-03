/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.world.adventurer;

import net.rowf.gaunt.world.Component;

/**
 *
 * @author woeltjen
 */
public class Player implements Component {
    private String name;
    
    public Player (String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
