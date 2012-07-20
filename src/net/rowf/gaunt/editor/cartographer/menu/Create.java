/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.editor.cartographer.menu;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author woeltjen
 */
public class Create implements Invoker<Cartography> {
    private Container parent;
    
    public Create (Container parent) {
        this.parent = parent;
    }

    @Override
    public void invoke(final Cartography target) {
        final JDialog dialog = new Dialog(target);
        dialog.setVisible(true);
        dialog.pack();
    }
    
    
    
    private class Dialog extends JDialog {
        private Cartography target;
        private JTextField width, height;

        public Dialog(Cartography target) {            
            this.target = target;
            
            width  = new JTextField("128");
            height = new JTextField("128");
            JButton accept = new JButton ("Create");
            JButton cancel = new JButton ("Cancel");
            
            JPanel panel = new JPanel();
            panel.add (new JLabel("Width: "));
            panel.add (width);
            panel.add (new JLabel("Height: "));
            panel.add (height);
            panel.add (accept);
            panel.add (cancel);
            
            accept.addActionListener(create);
            accept.addActionListener(close);
            cancel.addActionListener(close);
            
            getContentPane().add(panel);
        }

        private ActionListener close = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
                dispose();
            }            
        };
        
        private ActionListener create = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    target.create(Integer.parseInt(width.getText()), 
                                  Integer.parseInt(height.getText()));
                } catch (Exception e) {
                    System.err.println("Bad arguments for creation");
                }
            }
            
        };

    }
    
    
    
}
