/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.editor.cartographer;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import net.rowf.gaunt.assets.level.Convertor;
import net.rowf.gaunt.assets.level.Index;
import net.rowf.gaunt.world.Prototype;

/**
 *
 * @author woeltjen
 */
public class Cartographer extends JPanel {
    public Cartographer(Convertor<Index, Prototype> convertor) {
        setLayout(new BorderLayout());
        //add(tools,   BorderLayout.NORTH);
        //add(canvas,  BorderLayout.CENTER);
        add(new Palette(convertor), BorderLayout.SOUTH);
    }
}
