/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jgaunt.assets.resource;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import javax.imageio.ImageIO;
import jgaunt.assets.Depot;
import jgaunt.assets.Storage;
import jgaunt.assets.level.Architect;
import jgaunt.assets.level.Populator;
import jgaunt.assets.level.catalog.Compendium;
import jgaunt.assets.level.swing.Map;
import jgaunt.engine.renderer.Gallery;
import jgaunt.engine.renderer.Sprite;
import jgaunt.engine.renderer.swing.Image;

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
            return new Architect(new Map(getImage(key)),
                    new Compendium(new Resources()).getCatalog());
        }

    };

    public Resources() {
        super(Arrays.<Storage<?>>asList(SPRITES, GALLERIES, MAPS));
    }

}
