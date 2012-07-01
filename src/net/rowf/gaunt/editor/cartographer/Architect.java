/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.editor.cartographer;

import java.awt.image.BufferedImage;
import net.rowf.gaunt.assets.level.Carpenter;
import net.rowf.gaunt.assets.level.Convertor;
import net.rowf.gaunt.assets.level.Coordinate;
import net.rowf.gaunt.assets.level.Index;
import net.rowf.gaunt.assets.level.Populator;

/**
 *
 * @author woeltjen
 */
public class Architect<T> implements Matrix, Convertor<Coordinate, Index> {
    private BufferedImage image;
    
    public Architect(int width, int height) {
        this.image   = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
    }
    
    public void set(int x, int y, int index) {
        if (x >= 0 && y >= 0 && x < image.getWidth() && y < image.getHeight()) {
            int i = index & 0xFF;
            //i = (i << 0) | (i << 8) | (i << 16);
            image.setRGB(x, y, i);
        }
    }
    
    public int get(int x, int y) {
        return image.getRGB(x, y) & 0xFF;
    }

    @Override
    public Index convert(Coordinate key) {
        return new Index(get(key.getX(), key.getY()));
    }

    @Override
    public Coordinate maximum() {
        return new Coordinate(image.getWidth(), image.getHeight());
    }

    public Populator getPopulator(Convertor<Index, Coordinate> catalog) {
        return new Carpenter(this, catalog);        
    }
        
}