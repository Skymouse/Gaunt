/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rowf.gaunt.assets.resource;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import javax.imageio.ImageIO;
import net.rowf.gaunt.assets.Depot;
import net.rowf.gaunt.assets.Storage;
import net.rowf.gaunt.assets.level.Carpenter;
import net.rowf.gaunt.assets.level.Populator;
import net.rowf.gaunt.assets.level.catalog.Compendium;
import net.rowf.gaunt.assets.level.catalog.categories.Numerology;
import net.rowf.gaunt.assets.resource.maps.Map;
import net.rowf.gaunt.engine.renderer.Gallery;
import net.rowf.gaunt.engine.renderer.Sprite;
import net.rowf.gaunt.engine.renderer.swing.Image;

/**
 *
 * @author woeltjen
 */
public class Resources extends Depot {
    private static final int SIZE = 32;

    private static BufferedImage getImage(String key) {
            try {
                return ImageIO.read(
                        Resources.class.getResourceAsStream(key + ".png"));
            } catch (Exception e) {
                return new BufferedImage(SIZE, SIZE, BufferedImage.TYPE_BYTE_INDEXED);
            }

    }

    private static Gallery makeGallery(BufferedImage image) {
        final int rows = image.getHeight() / SIZE;
        final int cols = image.getWidth()  / SIZE;

        final Sprite[] sprites = new Sprite[rows * cols];

        for (int u = 0; u < cols; u++)
            for (int v = 0; v < rows; v++)
                sprites[u + v * cols] =
                    new Image(image.getSubimage(u*SIZE, v*SIZE, SIZE, SIZE));

        return new Gallery() {

            public int columns() {
                return cols;
            }

            public Sprite getSprite(int r, int c) {
                return sprites[r * cols + c];
            }

            public int rows() {
                return rows;
            }

        };
    }

    private static final Storage<Sprite> SPRITES = new Storage<Sprite>() {

        public Class<Sprite> getStoredClass() {
            return Sprite.class;
        }

        public Sprite retrieve(String key) {
            return new Image(getImage(key).getSubimage(0, 0, SIZE, SIZE));
        }

    };

    private static final Storage<Gallery> GALLERIES = new Storage<Gallery>() {

        public Class<Gallery> getStoredClass() {
            return Gallery.class;
        }

        public Gallery retrieve(String key) {
            return makeGallery(getImage(key));
        }

    };

    private static final Storage<Populator> MAPS = new Storage<Populator>() {

        @Override
        public Class<Populator> getStoredClass() {
            return Populator.class;
        }

        @Override
        public Populator retrieve(String key) {
            return new Carpenter(new Map(getImage(key)),
                    new Compendium(new Resources()).getCatalog());
        }

    };
        
    public Resources() {
        super(Arrays.<Storage<?>>asList(SPRITES, GALLERIES, MAPS));
        this.addStore(new Library("definitions", "definition", this));
        this.addStore(new Library("index",       "index",      this));
    }

}
