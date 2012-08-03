/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.editor.cartographer.menu;

import java.io.File;
import java.util.Arrays;
import javax.imageio.ImageIO;
import net.rowf.gaunt.assets.level.Index;
import net.rowf.gaunt.editor.cartographer.Architect;
import net.rowf.gaunt.editor.cartographer.Cartographer;
import net.rowf.gaunt.engine.renderer.View;
import net.rowf.gaunt.game.Game;
import net.rowf.gaunt.game.Round;
import net.rowf.gaunt.world.entity.Prototype;
import net.rowf.gaunt.world.dungeon.spawns.Specification;

/**
 *
 * @author woeltjen
 */
public class Mapper implements Cartography {
    private Cartographer cartographer;

    public Mapper(Cartographer cartographer) {
        this.cartographer = cartographer;
    }

    @Override
    public void create(int width, int height) {
        cartographer.setArchitect(new Architect(width, height));
    }

    @Override
    public void execute(View view, Specification player) {
        Prototype p = player.apply(cartographer.getCompendium().getHeroes().evaluate(new Index(0)));
        Prototype l = cartographer.getLevel();
        final Game game = new Round(p, view, Arrays.asList(l));
        new Thread() {            
            public void run() {
                game.begin();
            }
        }.start();
    }

    @Override
    public void publish(File file) {
        try {
            ImageIO.write(cartographer.getArchitect().getImage(), "PNG", file);
        } catch (Exception e) {
            System.err.println("Error writing to " + file + "; " + e.getMessage());
        }
    }

    @Override
    public void retrieve(File file) {
        try {
            cartographer.setArchitect(new Architect(ImageIO.read(file)));
        } catch (Exception e) {
            System.err.println("Error writing to " + file + "; " + e.getMessage());
        }    
    }

    
    
}
