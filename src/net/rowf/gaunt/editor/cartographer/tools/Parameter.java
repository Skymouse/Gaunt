/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.editor.cartographer.tools;

import java.awt.event.ActionListener;
import javax.swing.JPanel;
import net.rowf.gaunt.theory.Provider;

/**
 *
 * @author woeltjen
 */
public abstract class Parameter<T> extends JPanel implements Actionable, Provider<T> {
    public static final Parameter<Object> NONE = new Parameter<Object>() {

        @Override
        public void addActionListener(ActionListener listener) {
            
        }

        @Override
        public Object get() {
            return null;
        }
        
    };
}
