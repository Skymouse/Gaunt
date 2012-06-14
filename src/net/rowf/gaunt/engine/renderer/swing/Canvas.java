/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rowf.gaunt.engine.renderer.swing;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import net.rowf.gaunt.engine.renderer.Camera;
import net.rowf.gaunt.engine.renderer.Sprite;
import net.rowf.gaunt.engine.renderer.Sprite.Drawable;
import net.rowf.gaunt.engine.renderer.View;
import net.rowf.gaunt.world.Boundary;
import net.rowf.gaunt.world.Position;

/**
 *
 * @author woeltjen
 */
public class Canvas extends JPanel implements View, Camera {

    public static final float WORLD_TO_PIXEL = 32.0f;

    private BufferedImage[] buffer  = new BufferedImage[2];
    private int             visible = 0;
    
    private Camera          camera = this;
    private Position        origin = new Position(0,0);
    
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
        float width  = ((float) getWidth ()) / WORLD_TO_PIXEL;
        float height = ((float) getHeight()) / WORLD_TO_PIXEL;
        return camera.getVisibleBoundary(width, height);
    }
    
    public Boundary getVisibleBoundary(float width, float height) {
        return new Boundary(new Position(0,0), new Position(width,height));
    }

    public void draw(BufferedImage i, Boundary b) {
        b = b.add(origin.scale(-1));
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
        origin = camera.getVisibleBoundary(pixelToWorld(getWidth ()), 
                                           pixelToWorld(getHeight()))
                       .getMinimum();
    }

    public Graphics getDrawingBuffer() {
        return getInvisible().getGraphics();
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }
    
    private float pixelToWorld (int pixels) {
        return ((float) pixels) / WORLD_TO_PIXEL;
    }


    
    private BufferedImage getInvisible() {
        return buffer[1 ^ visible];
    }

    private BufferedImage getVisible() {
        return buffer[visible];
    }
    


}
