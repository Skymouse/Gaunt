/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.editor.cartographer.viewer;

import java.util.HashMap;
import java.util.Map;
import net.rowf.gaunt.engine.Module;
import net.rowf.gaunt.world.Entity;
import net.rowf.gaunt.world.World;
import net.rowf.gaunt.world.behavior.Common.Render;
import net.rowf.gaunt.world.behavior.Standard;
import net.rowf.gaunt.world.components.Name;

/**
 *
 * @author woeltjen
 */
public class Labeler implements Module {
    private Map<String, Label> labels = new HashMap<String, Label>();

    @Override
    public void run(World w) {
        for (Entity e : w.getEntities())
            if (e.get(Render.class).isEmpty())
                decorate(e);
    }
    
    private void decorate(Entity e) {
        for (Name name : e.first(Name.class)) {
            e.add(Standard.RENDER);
            e.add(label(name.get()));
        }        
    }
    
    private Label label(String string) {
        if (!labels.containsKey(string)) labels.put(string, new Label(string));
        return labels.get(string);
    }
    
}
