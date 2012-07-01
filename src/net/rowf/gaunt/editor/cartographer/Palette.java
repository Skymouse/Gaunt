/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.editor.cartographer;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import net.rowf.gaunt.assets.level.Convertor;
import net.rowf.gaunt.assets.level.Index;
import net.rowf.gaunt.world.Entity;
import net.rowf.gaunt.world.Prototype;
import net.rowf.gaunt.world.World;
import net.rowf.gaunt.world.behavior.Common.Think;

/**
 *
 * @author woeltjen
 */
public class Palette extends JPanel {
    private int primary   = 1;
    private int secondary = 0;
    
    private Picker[] categories = new Picker[16];
    private Picker[] choices    = new Picker[256];
    
    private JPanel top    = new JPanel();
    private JPanel bottom = new JPanel();
    
    public Palette(Convertor<Index, Prototype> convertor) { // Does it need a Convertor<Index, Prototype>?
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(top   );
        add(bottom);
        
        top   .setLayout(new BoxLayout(top   , BoxLayout.LINE_AXIS));
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.LINE_AXIS));
        
        List<Entity> group = new ArrayList<Entity>();
        for (int i = 0; i < 256; i++) {
            Entity e = convertor.convert(new Index(i)).spawn();
            for (Think t : e.get(Think.class)) t.invoke(e, new World());
            group.add(e);
            choices[i] = new Picker(64, Arrays.asList(e));
            choices[i].addMouseListener(new Choice(i));
            choices[i].setBorder(BorderFactory.createLineBorder(Color.ORANGE));
            
            if (group.size() == 16) {               
                categories[i >> 4] = new Picker(64, group);
                categories[i >> 4].addMouseListener(new Categorical(i>>4));
                top.add(categories[i >> 4]);
                group = new ArrayList<Entity>();
            }
        }
        
        pickFrom(0);
        
    }
    
    public int getPrimary() {
        return primary;
    }
    
    public int getSecondary() {
        return secondary;
    }
    
    private void pickFrom(int category) {
        bottom.removeAll();
        for (int i = 0; i < 16; i++) {
            bottom.add(choices[i + (category << 4)]); 
            choices[i + (category << 4)].repaint();
        }
        bottom.doLayout();
        bottom.repaint();
        repaint();
    }
    
    private class Categorical implements MouseListener {
        private int index;
        
        public Categorical(int index) {
            this.index = index;
        }
        
        @Override
        public void mouseClicked(MouseEvent me) {
            pickFrom(index);
        }

        @Override
        public void mouseEntered(MouseEvent me) {
        }

        @Override
        public void mouseExited(MouseEvent me) {
        }

        @Override
        public void mousePressed(MouseEvent me) {
        }

        @Override
        public void mouseReleased(MouseEvent me) {
        }        
    }

    private class Choice implements MouseListener {
        private int index;
        
        public Choice(int index) {
            this.index = index;
        }
        
        @Override
        public void mouseClicked(MouseEvent me) {
            if      (me.getButton() == MouseEvent.BUTTON1) primary   = index;
            else if (me.getButton() == MouseEvent.BUTTON3) secondary = index;
            System.out.println(primary);
            System.out.println(secondary);
        }

        @Override
        public void mouseEntered(MouseEvent me) {
        }

        @Override
        public void mouseExited(MouseEvent me) {
        }

        @Override
        public void mousePressed(MouseEvent me) {
        }

        @Override
        public void mouseReleased(MouseEvent me) {
        }        
    }

}
