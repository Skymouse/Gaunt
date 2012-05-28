/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jgaunt.assets.level.catalog;

import java.util.ArrayList;
import java.util.List;
import jgaunt.assets.Depot;
import jgaunt.assets.level.Convertor;
import jgaunt.assets.level.Index;
import jgaunt.assets.level.catalog.categories.Category;
import jgaunt.engine.renderer.Animation;
import jgaunt.engine.renderer.Gallery;
import jgaunt.engine.renderer.Sprite;
import jgaunt.world.Prototype;

/**
 *
 * @author woeltjen
 */
public class Compendium {
    private Depot depot;

    public Compendium(Depot depot) {
        this.depot = depot;
    }

    public Catalog<Prototype> getCatalog() {
        List<Category> categories = new ArrayList<Category>();

        //TODO: Move this highly coupled stuff somewhere ???

        return new Catalog(categories);
    }


    private Convertor<Index, Sprite> makeTileset (final Animation anim) {
        return new Convertor<Index, Sprite>() {
            public Sprite convert(Index key) {
                return anim.getSprite(key.get());
            }
            public Index maximum() {
                return new Index(anim.frames());
            };
        };
    }

    private Convertor<Index, Sprite> makeTileset (final Gallery gallery) {
        return new Convertor<Index, Sprite>() {
            public Sprite convert(Index key) {
                int index  = key.get();
                int row    = index % gallery.columns();
                int column = index / gallery.columns();
                return gallery.getSprite(row, column);
            }

            public Index maximum() {
                return new Index(gallery.rows() * gallery.columns());
            };
        };
    }
}
