/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.editor.cartographer.viewer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import net.rowf.gaunt.engine.renderer.Sprite;
import net.rowf.gaunt.engine.renderer.Sprite.Drawable;
import net.rowf.gaunt.engine.renderer.View;
import net.rowf.gaunt.engine.renderer.swing.Canvas;
import net.rowf.gaunt.world.Boundary;

/**
 *
 * @author woeltjen
 */
public class Label implements Sprite, Drawable<Canvas> {
    private BufferedImage image = new BufferedImage(32,32,BufferedImage.TYPE_INT_ARGB);

    public Label(String string) {    
        Graphics2D g = image.createGraphics();
        g.setBackground(new Color(0,0,0,0));
        g.clearRect(0, 0, 32, 32);
        g.setColor(pickColor(string.hashCode()));
        g.fillOval(0, 0, 32, 32);
        g.setColor(new Color(255,255,255,255));        
        g.setFont(Font.decode("sansserif").deriveFont(7.0f));
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, 
                           RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.drawString(string, 17 - string.length() * 2, 18);
    }
    
    @Override
    public <V extends View> Drawable<V> getDrawable(Class<V> view) {
        return (view.isAssignableFrom(Canvas.class)) ? 
                (Drawable<V>) this : null;
    }

    @Override
    public void draw(Canvas display, Boundary b) {
        display.draw(image, b);
    }

    private Color pickColor(int hash) {
        Color c = Color.getHSBColor((float) (hash & 0xFF)/255f, .8f, .5f);
        return new Color(c.getRed(), c.getGreen(), c.getBlue(), 96);
    }
}
