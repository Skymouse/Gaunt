/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.editor.cartographer.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 *
 * @author woeltjen
 */
public class Popup extends JPopupMenu {
    private Cartography cartography;

    public Popup(Cartography cartography) {
        this.cartography = cartography;
        add (new Option("Open", new Load(this)));
        add (new Option("Save", new Save(this)));
    }
    
    private class Option extends JMenuItem implements ActionListener {
        private Invoker<Cartography> action;
        public Option(String name, Invoker<Cartography> action) {
            super(name);
            this.action = action;
            addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            action.invoke(cartography);
        }        
    }
}
