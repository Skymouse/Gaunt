/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jgaunt.engine.renderer.swing;

import java.awt.image.BufferedImage;
import jgaunt.engine.renderer.Sprite;
import jgaunt.engine.renderer.Sprite.Drawable;
import jgaunt.engine.renderer.View;
import jgaunt.world.Boundary;

/**
 *
 * @author woeltjen
 */
public class Image implements Sprite, Drawable<Canvas> {
    private BufferedImage image;

    public Image ( BufferedImage image ) {
        this.image = image;
    }

    public void draw(Canvas display, Boundary b) {
        display.draw(image, b);
    }

    public <V extends View> Drawable<V> getDrawable(Class<V> view) {
        if (view.isAssignableFrom(Canvas.class)) return (Drawable<V>) this;
        return null;
    }
}
