/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.editor.cartographer.menu;

import java.io.File;
import javax.imageio.ImageIO;
import net.rowf.gaunt.editor.cartographer.Architect;
import net.rowf.gaunt.editor.cartographer.Cartographer;

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
    public void execute() {
        throw new UnsupportedOperationException("Not supported yet.");
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
