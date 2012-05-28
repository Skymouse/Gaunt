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
    private static BufferedImage getImage(String key) {
            try {
                return ImageIO.read(
                        Resources.class.getResourceAsStream(key + ".png"));
            } catch (Exception e) {
                return new BufferedImage(32, 32, BufferedImage.TYPE_BYTE_INDEXED);
            }

    }

    private static final Storage<Sprite> SPRITES = new Storage<Sprite>() {

        public Class<Sprite> getStoredClass() {
            return Sprite.class;
        }

        public <T> T retrieve(Class<T> tClass, String key) {
            return (T) new Image(getImage(key).getSubimage(0, 0, 32, 32));
        }

    };

    public Resources() {
        super(Arrays.<Storage<?>>asList(SPRITES));
    }

}
