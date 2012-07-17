/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.editor.cartographer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.swing.*;
import net.rowf.gaunt.assets.level.Provider;
import net.rowf.gaunt.editor.cartographer.brush.Brush;
import net.rowf.gaunt.editor.cartographer.brush.Ink;
import net.rowf.gaunt.editor.cartographer.brush.Marker;
import net.rowf.gaunt.editor.cartographer.brush.Pencil;
import net.rowf.gaunt.editor.cartographer.tools.Parameter;
import net.rowf.gaunt.editor.cartographer.tools.Scalar;

/**
 *
 * @author woeltjen
 */
public class Toolbox extends JPanel {
    private Provider<Ink> inkwell;

    private ButtonGroup                 group = new ButtonGroup();    
    private List<Selection>             listeners = new ArrayList<Selection>();
    
    public Toolbox(Provider<Ink> ink) {
        this.inkwell = ink;
        
        addOption("pen", new Tool<Object>(Parameter.NONE) { 
            @Override
            public Brush getBrush(Object parameter) {
                return new Pencil(inkwell.get());
            }            
        });
        addOption("marker", new Tool<Float>(new Scalar()) { 
            @Override
            public Brush getBrush(Float parameter) {
                return new Marker(inkwell.get(), parameter);
            }            
        });
        
    }
    
    public void addListener(Selection s) {
        listeners.add(s);
    }
    
    private void addOption(String name, Tool<?> tool) {
        add(new Toggle(name, tool));
    }
    
    private void select(Brush b) {
        for (Selection s : listeners) s.select(b);
    }
    
    public interface Selection {
        public void select(Brush b);
    }
    
    private abstract class Tool<T> {
        private Provider<T> provider;
        public Tool(Provider<T> provider) {
            this.provider = provider;
        }
        public Brush getBrush() { return getBrush(provider.get()); }
        public abstract Brush getBrush(T parameter);
    }
    
    private class Toggle extends JToggleButton {
        private Tool<?> tool;

        public Toggle(String text, Tool<?> tool) {
            super(text);
            this.tool = tool;
            group.add(this);
            addActionListener(selector);
        }

        public Toggle(Icon icon, Tool<?> tool) {
            super(icon);
            this.tool = tool;
            group.add(this);            
            addActionListener(selector);
        }
        
        public Tool<?> getTool() {
            return tool;
        }
    }
    
    private final ActionListener selector = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent ae) {
            Enumeration<AbstractButton> buttons = 
                    group.getElements();
            AbstractButton b;
            
            while (buttons.hasMoreElements()) 
                if ((b=buttons.nextElement()) instanceof Toggle)
                    if (b.getModel().isSelected())
                        select((((Toggle)b).getTool().getBrush()));                    
        }
        
    };
}
