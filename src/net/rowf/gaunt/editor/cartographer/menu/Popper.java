/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.editor.cartographer.menu;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;

/**
 *
 * @author woeltjen
 */
public class Popper extends JLabel {
    private Popup popup;
    
    public Popper(Cartography cartography) {
        super("menu");
        popup = new Popup(cartography);
        addMouseListener(listener);
    }
    
    private MouseListener listener = new MouseAdapter() {

        @Override
        public void mouseClicked(MouseEvent e) {
            popup.show(Popper.this, 0, 0);
        }
        
    };
    
}
