/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.editor.cartographer;

import java.awt.BorderLayout;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import net.rowf.gaunt.editor.cartographer.viewer.Zoomer;
import net.rowf.gaunt.engine.renderer.Camera;
import net.rowf.gaunt.engine.renderer.swing.Canvas;
import net.rowf.gaunt.world.Boundary;
import net.rowf.gaunt.world.Vector;

/**
 *
 * @author woeltjen
 */
public class Viewer extends JPanel implements Camera {
    private float pan[] = { 0f, 0f };
    
    public Viewer(Canvas canvas) {
        setLayout(new BorderLayout());
        setCanvas(canvas);
    }
    
    public void setCanvas(Canvas canvas) {
        removeAll();
        add(canvas, BorderLayout.CENTER);
        add(new Zoomer(canvas) , BorderLayout.WEST);
        add(new Panner(0, 128f), BorderLayout.SOUTH);
        add(new Panner(1, 128f), BorderLayout.EAST);
        canvas.setCamera(this);
    }
    
    @Override
    public Boundary getVisibleBoundary(float width, float height) {
        return new Boundary(new Vector(pan[0], pan[1]),
                                new Vector(pan[0]+width, pan[1]+height));
                
    }
    
    private static final int[] ORIENTATION = { JScrollBar.HORIZONTAL, 
                                               JScrollBar.VERTICAL };
    
    private class Panner extends JScrollBar implements AdjustmentListener {
        private int      axis;
        private float    size;
        
        public Panner(int axis, float size) {
            super(ORIENTATION[axis], 0, 1, 0, 1000);
            this.axis = axis;
            this.size = size;
            addAdjustmentListener(this);
        }

        @Override
        public void adjustmentValueChanged(AdjustmentEvent ae) {
            float value = (float) ae.getValue() / 1000f;
            pan[axis]   = value * size;
        }
    }
}
