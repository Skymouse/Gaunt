/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jgaunt.assets.level.swing;

import java.awt.image.BufferedImage;
import jgaunt.assets.level.Convertor;
import jgaunt.assets.level.Coordinate;
import jgaunt.assets.level.Index;
import jgaunt.assets.level.Populator;
import jgaunt.world.Entity;
import jgaunt.world.Prototype;

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
