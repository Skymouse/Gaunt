/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.editor.cartographer.stylus;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.List;
import net.rowf.gaunt.theory.Pair;
import net.rowf.gaunt.theory.Provider;
import net.rowf.gaunt.editor.cartographer.brush.Brush;
import net.rowf.gaunt.theory.Criterion;

/**
 *
 * @author woeltjen
 */
public class Switch extends MouseAdapter {
    public static final Criterion<MouseEvent> CTRL = new Criterion<MouseEvent>() {
        @Override
        public boolean consider(MouseEvent e) {
            return e.isControlDown();
        }        
    };
    
    private static final int DEFAULT = -1; //Not an index into list
    
    private MouseAdapter parent;
    private Cursor  cursor;
    private List<Pair<Criterion<MouseEvent>, Provider<Brush>>> options;
    private Provider<Brush> otherwise;
    
    private int active = DEFAULT;

    public Switch(MouseAdapter parent,
                  Cursor cursor, 
                  List<Pair<Criterion<MouseEvent>, Provider<Brush>>> options, 
                  Provider<Brush> otherwise) {
        this.parent = parent;
        this.cursor = cursor;
        this.options = options;
        this.otherwise = otherwise;
    }
    
    private void activate(MouseEvent me) {
        int choice = categorize(me);
        if (choice != active) {
            cursor.setBrush(provider(choice).get());
        }
        active = choice;
    }
    
    private Provider<Brush> provider(int i) {
        return i == DEFAULT ? otherwise : options.get(i).getRight();
    }
    
    private int categorize(MouseEvent me) {
        for (int i = 0; i < options.size(); i++)
            if (options.get(i).getLeft().consider(me))
                return i;
        return -1;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        activate(e);
        parent.mouseClicked(e);
    }

    public void mouseWheelMoved(MouseWheelEvent e) {
        activate(e);
        parent.mouseWheelMoved(e);
    }

    public void mouseReleased(MouseEvent e) {
        activate(e);
        parent.mouseReleased(e);
    }

    public void mousePressed(MouseEvent e) {
        activate(e);
        parent.mousePressed(e);
    }

    public void mouseMoved(MouseEvent e) {
        activate(e);
        parent.mouseMoved(e);
    }

    public void mouseExited(MouseEvent e) {
        activate(e);
        parent.mouseExited(e);
    }

    public void mouseEntered(MouseEvent e) {
        activate(e);
        parent.mouseEntered(e);
    }

    public void mouseDragged(MouseEvent e) {
        activate(e);
        parent.mouseDragged(e);
    }

    
}
