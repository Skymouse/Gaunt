/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rowf.gaunt.engine.renderer.swing;

import java.awt.image.BufferedImage;
import net.rowf.gaunt.engine.renderer.Sprite;
import net.rowf.gaunt.engine.renderer.Sprite.Drawable;
import net.rowf.gaunt.engine.renderer.View;
import net.rowf.gaunt.world.components.Boundary;

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
