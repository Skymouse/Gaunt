/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jgaunt.engine.renderer.swing;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import jgaunt.engine.renderer.Sprite;
import jgaunt.engine.renderer.Sprite.Drawable;
import jgaunt.engine.renderer.View;
import jgaunt.world.Boundary;

/**
 *
 * @author woeltjen
 */
public class Canvas extends JPanel implements View {

    public static final float WORLD_TO_PIXEL = 32.0f;

    private BufferedImage[] buffer  = new BufferedImage[2];
    private int             visible = 0;

    public Canvas() {
        buffer[0] = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        buffer[1] = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(buffer[visible], 0, 0, this);
    }

    public void draw(Sprite s, Boundary b) {
        Drawable<Canvas> d = s.getDrawable(Canvas.class);
        d.draw(this, b);
    }

    public Boundary getVisibleBoundary() {
        Dimension d = getSize();
        return new Boundary ((float) d.getWidth () / WORLD_TO_PIXEL,
                             (float) d.getHeight() / WORLD_TO_PIXEL);
    }

    public void draw(BufferedImage i, Boundary b) {
        int x = (int) (b.getMinimum().getX() * WORLD_TO_PIXEL);
        int y = (int) (b.getMinimum().getY() * WORLD_TO_PIXEL);
        int w = (int) (b.getMaximum().getX() * WORLD_TO_PIXEL) - x;
        int h = (int) (b.getMaximum().getY() * WORLD_TO_PIXEL) - y;
        getDrawingBuffer().drawImage(i, x, y, w, h, this);
    }

    public void update() {
        swap();
        repaint();
        getDrawingBuffer().clearRect(0, 0, getWidth(), getHeight());
    }

    public synchronized void swap() {
        visible = 1 ^ visible;
        if (getWidth () != buffer[1^visible].getWidth () ||
            getHeight() != buffer[1^visible].getHeight()) {
            buffer[1^visible] = new BufferedImage(getWidth(), getHeight(),
                    BufferedImage.TYPE_INT_RGB);
        }
    }

    public Graphics getDrawingBuffer() {
        return getInvisible().getGraphics();
    }

    private BufferedImage getInvisible() {
        return buffer[1 ^ visible];
    }

    private BufferedImage getVisible() {
        return buffer[visible];
    }

}
