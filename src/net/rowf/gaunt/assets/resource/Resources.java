/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rowf.gaunt.assets.resource;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import javax.imageio.ImageIO;
import net.rowf.gaunt.assets.Depot;
import net.rowf.gaunt.assets.Storage;
import net.rowf.gaunt.assets.definitions.parser.Text;
import net.rowf.gaunt.assets.level.Architect;
import net.rowf.gaunt.assets.level.Populator;
import net.rowf.gaunt.assets.level.catalog.Compendium;
import net.rowf.gaunt.assets.level.swing.Map;
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
            return new Architect(new Map(getImage(key)),
                    new Compendium(new Resources()).getCatalog());
        }

    };
    
    private static final Storage<Text> LIBRARY = new Storage<Text>() {

        @Override
        public Class<Text> getStoredClass() {
            return Text.class;
        }

        @Override
        public Text retrieve(String key) {
            InputStream i = Resources.class.getResourceAsStream("definitions/" + key + ".definition");
            if (i == null) return null;
            InputStreamReader reader = new InputStreamReader(i);
            final BufferedReader buffer = new BufferedReader(reader);
            try {
                final Iterator<String> iterator = new Iterator<String>() {
                    private String line = buffer.readLine();

                    @Override
                    public boolean hasNext() {                   
                        return line != null;
                    }

                    @Override
                    public String next() {
                        try {
                            String l = line;
                            line = buffer.readLine();
                            return l;
                        } catch (IOException e) {
                            return null;
                        }
                    }

                    @Override
                    public void remove() {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                };
                return new Text(new Iterable<String>() {
                    @Override
                    public Iterator<String> iterator() {
                        return iterator;
                    }
                });
            } catch (IOException e) {
                
            }
            
            return null;
        }


        
    } ;

    public Resources() {
        super(Arrays.<Storage<?>>asList(SPRITES, GALLERIES, MAPS));
    }

}
