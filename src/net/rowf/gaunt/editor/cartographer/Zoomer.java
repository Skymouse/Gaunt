/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.editor.cartographer;

import java.awt.event.ActionEvent;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import net.rowf.gaunt.engine.renderer.swing.Canvas;

/**
 *
 * @author woeltjen
 */
public class Zoomer extends JSlider {

    public Zoomer(Canvas canvas) {
        super(JSlider.VERTICAL, 10, 400, 100);        
        addChangeListener(new Zoom(canvas));
    }
    
    private class Zoom implements ChangeListener {
        private Canvas canvas;

        public Zoom(Canvas canvas) {
            this.canvas = canvas;
        }

        @Override
        public void stateChanged(ChangeEvent ce) {
            canvas.setScale(((float)getValue()) / 100f);
        }
        
    }
    
}
