/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.assets.level.catalog;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.rowf.gaunt.assets.Depot;
import net.rowf.gaunt.assets.definitions.Dictionary;
import net.rowf.gaunt.assets.definitions.parser.Text;
import net.rowf.gaunt.assets.definitions.parser.Transcriber;
import net.rowf.gaunt.engine.renderer.Column;
import net.rowf.gaunt.engine.renderer.Gallery;
import net.rowf.gaunt.engine.renderer.Hologram;
import net.rowf.gaunt.engine.renderer.Rotary;
import net.rowf.gaunt.world.dungeon.spawns.Reuser;
import net.rowf.gaunt.world.dungeon.spawns.Specification;

/**
 *
 * @author woeltjen
 */
public class Illustrator implements Transcriber {
    private static final Pattern PATTERN = 
            Pattern.compile("\\s*(\\w+):\\s*(\\w+)\\s*(\\d+)\\s*");
    
    private Depot depot;
    
    private List<Illustration> illustrations = new ArrayList<Illustration>();
    
    public Illustrator(Depot depot, String file) {
        this.depot = depot;
        Text text = depot.retrieve(Text.class, file);
        for (String line : text) {
            try {
                illustrations.add(new Illustration(line));
            } catch (ParseException e) {
                /* Ignore bad lines. */
            }
        }
    }
    
    @Override
    public void transcribe(Dictionary dictionary) {
        for (Illustration i : illustrations) i.transcribe(dictionary);
    }
    
    private class Illustration implements Transcriber {
        private String target;
        private String name;
        private int    index;
        
        public Illustration(String string) throws ParseException {
            Matcher m = PATTERN.matcher(string);
            if (m.matches() && m.groupCount() > 3) {
                target = m.group(1);
                name   = m.group(2);
                index  = Integer.parseInt(m.group(3));                
            } else {
                throw new ParseException(string, m.regionEnd());
            }
        }

        @Override
        public void transcribe(Dictionary dictionary) {
            Specification spec = dictionary.getSpecification(target);
            Gallery g = depot.retrieve(Gallery.class, name);
            Hologram hologram = new Rotary(
                        new Column (g, index % g.columns()),
                        4, -(float) (Math.PI / 3));
            spec.add(new Reuser(Hologram.class, hologram));
        }
        
    }
}
