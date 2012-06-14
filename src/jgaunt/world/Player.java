/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jgaunt.world;

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
