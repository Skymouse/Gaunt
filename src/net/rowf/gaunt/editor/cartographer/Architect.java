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
public class Architect<T> implements Convertor<Coordinate, Index> {
    private BufferedImage image;
    
    public Architect(BufferedImage image) {
        this.image   = image;
    }
    
    public Architect(int width, int height) {
        this.image   = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
    }
    
    public void set(int x, int y, int index) {
        if (x >= 0 && y >= 0 && x < image.getWidth() && y < image.getHeight()) {
            int i = index & 0xFF;
            image.getRaster().setPixel(x, y, new int[]{i});
        }
    }
    
    public int get(int x, int y) {
        int pix[] = { 0 };
        return image.getRaster().getPixel(x, y, pix)[0];
    }
    
    public BufferedImage getImage() {
        return image;
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
