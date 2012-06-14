/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rowf.gaunt.engine.renderer;

/**
 *
 * @author woeltjen
 */
public class Column implements Animation {
    private int column;
    private Gallery gallery;

    public Column(Gallery gallery, int column) {
        this.gallery = gallery;
        this.column  = column;
    }

    public int frames() {
        return gallery.rows();
    }

    public Sprite getSprite(int frame) {
        return gallery.getSprite(frame, column);
    }
}
