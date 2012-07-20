/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.editor.cartographer.menu;

import java.awt.Container;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import javax.swing.*;
import net.rowf.gaunt.engine.logic.control.Pilot;
import net.rowf.gaunt.engine.logic.control.swing.Arrows;
import net.rowf.gaunt.engine.renderer.Watcher;
import net.rowf.gaunt.engine.renderer.swing.Canvas;
import net.rowf.gaunt.world.behavior.Common;
import net.rowf.gaunt.world.dungeon.spawns.Reuser;
import net.rowf.gaunt.world.dungeon.spawns.Specification;

/**
 *
 * @author woeltjen
 */
public class Execute implements Invoker<Cartography> {
    private Container parent;
    
    public Execute (Container parent) {
        this.parent = parent;
    }

    @Override
    public void invoke(final Cartography target) {
        final JFrame frame = new JFrame();
        frame.setSize(640, 480);
        frame.setVisible(true);    
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);                        
        Canvas canvas = new Canvas();            
        frame.getContentPane().add(canvas);
        target.execute(canvas, makeControls(frame, canvas));
    }
    
    private Specification makeControls(Frame frame, Canvas canvas) {
        Arrows  keypad  = new Arrows(KeyEvent.VK_UP  , KeyEvent.VK_DOWN,
                                KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);
        Watcher watcher = new Watcher();
        
        Specification player = new Specification();
        player.add(new Reuser(Common.Task.class, new Pilot(keypad)));
        player.add(new Reuser(Common.Task.class, watcher));
        
        frame.addKeyListener(keypad);
        canvas.setCamera(watcher);
        
        return player;
    }
    
}
