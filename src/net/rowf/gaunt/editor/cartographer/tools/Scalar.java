/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.editor.cartographer.tools;

import java.awt.event.ActionListener;
import javax.swing.JTextField;

/**
 *
 * @author woeltjen
 */
public class Scalar extends Parameter<Float> {
    private JTextField field = new JTextField("2.0");
    
    public Scalar() {
        add(field);
    }

    @Override
    public void addActionListener(ActionListener listener) {
        field.addActionListener(listener);
    }

    @Override
    public Float get() {
        try {
            return Float.parseFloat(field.getText());
        } catch (Exception e) {
            return 1.0f;
        }
    }
    
}
