/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.editor.cartographer.menu;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;

/**
 *
 * @author woeltjen
 */
public class Load implements Invoker<Cartography> {
    private Container parent;
    
    public Load (Container parent) {
        this.parent = parent;
    }

    @Override
    public void invoke(final Cartography target) {
        final JFileChooser chooser = new JFileChooser();
        
        chooser.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (chooser.getSelectedFile() != null)
                    target.retrieve(chooser.getSelectedFile());
            }            
        });        
        
        chooser.showOpenDialog(parent);
    }
    
    
    
    
    
}
