/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rowf.gaunt;

import java.awt.event.KeyEvent;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import net.rowf.gaunt.assets.Depot;
import net.rowf.gaunt.assets.level.Index;
import net.rowf.gaunt.assets.level.Level;
import net.rowf.gaunt.assets.level.Populator;
import net.rowf.gaunt.assets.level.catalog.Catalog;
import net.rowf.gaunt.assets.level.catalog.Compendium;
import net.rowf.gaunt.assets.resource.Resources;
import net.rowf.gaunt.editor.cartographer.Architect;
import net.rowf.gaunt.editor.cartographer.Cartographer;
import net.rowf.gaunt.engine.logic.control.Pilot;
import net.rowf.gaunt.engine.logic.control.swing.Arrows;
import net.rowf.gaunt.engine.renderer.Watcher;
import net.rowf.gaunt.engine.renderer.swing.Canvas;
import net.rowf.gaunt.game.Round;
import net.rowf.gaunt.world.*;
import net.rowf.gaunt.world.behavior.Common.Task;
import net.rowf.gaunt.world.dungeon.spawns.Reuser;
import net.rowf.gaunt.world.dungeon.spawns.Specification;

/**
 *
 * @author woeltjen
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Canvas  canvas  = new Canvas();
        Arrows  keypad  = new Arrows(KeyEvent.VK_UP  , KeyEvent.VK_DOWN,
                                     KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);
        Watcher watcher = new Watcher();
        
        Specification player = new Specification();
        player.add(new Reuser(Task.class, new Pilot(keypad)));
        player.add(new Reuser(Task.class, watcher));
        
        Depot        depot        = new Resources();
        Compendium   compendium   = new Compendium(depot);
        Catalog      catalog      = compendium.getCatalog();
        Cartographer cartographer = new Cartographer(new Architect(60,60),
                                                     catalog);
        Round game = new Round(
                player.apply(compendium.getHeroes().convert(new Index(0))),
                canvas,
                Arrays.<Prototype>asList(new Level(depot.retrieve(Populator.class, "map01")))
                );

        
        JFrame  frame   = new JFrame("Gaunt's Dungeon");    
        frame.setSize(640, 480);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(canvas);
        //frame.getContentPane().add(cartographer);
//        JMenu menu = new JMenu("MENU");
//        menu.add(new JMenuItem("test"));
//        frame.setJMenuBar(new JMenuBar());
//        frame.getJMenuBar().add(menu);
        frame.setVisible(true);

        frame.addKeyListener(keypad);
        canvas.setCamera(watcher);
        canvas.setScale(2.0f);

        game.begin();
    }

}
