/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rowf.gaunt.engine.renderer;

/**
 *
 * @author woeltjen
 */
public class Row implements Animation {
    private int row;
    private Gallery gallery;

    public Row(Gallery gallery, int row) {
        this.gallery = gallery;
        this.row     = row;
    }

    public int frames() {
        return gallery.columns();
    }

    public Sprite getSprite(int frame) {
        return gallery.getSprite(row, frame % frames());
    }
}
