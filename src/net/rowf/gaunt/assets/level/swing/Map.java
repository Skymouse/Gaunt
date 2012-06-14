/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rowf.gaunt.assets.level.swing;

import java.awt.image.BufferedImage;
import net.rowf.gaunt.assets.level.Convertor;
import net.rowf.gaunt.assets.level.Coordinate;
import net.rowf.gaunt.assets.level.Index;

/**
 *
 * @author woeltjen
 */
public class Map implements Convertor<Coordinate, Index> {
    private BufferedImage image;
    private int[]         index = new int[4];

    public Map(BufferedImage image) {
        this.image   = image;
    }

    public Index convert(Coordinate key) {
        index = image.getRaster().getPixel(key.getX(), key.getY(), index);
        return new Index(index[0]);
    }

    public Coordinate maximum() {
        return new Coordinate(image.getWidth(), image.getHeight());
    }

}
