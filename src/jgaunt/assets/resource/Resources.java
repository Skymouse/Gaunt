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
import jgaunt.engine.renderer.Sprite;
import jgaunt.engine.renderer.swing.Image;

/**
 *
 * @author woeltjen
 */
public class Resources extends Depot {
    private static final Storage<Sprite> SPRITES = new Storage<Sprite>() {

        public Class<Sprite> getStoredClass() {
            return Sprite.class;
        }

        public <T> T retrieve(Class<T> tClass, String key) {

            try {
                BufferedImage image = ImageIO.read(Resources.class.getResourceAsStream(key + ".png"));
                return (T) new Image(image.getSubimage(0, 0, 32, 32));
            } catch (Exception e) {
                return null;
            }
        }

    };

    public Resources() {
        super(Arrays.<Storage<?>>asList(SPRITES));
    }

}
