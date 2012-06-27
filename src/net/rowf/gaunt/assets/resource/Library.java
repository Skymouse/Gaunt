/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.assets.resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import net.rowf.gaunt.assets.Depot;
import net.rowf.gaunt.assets.Storage;
import net.rowf.gaunt.assets.definitions.parser.Preprocessor;
import net.rowf.gaunt.assets.definitions.parser.Text;

/**
 *
 * @author woeltjen
 */
public class Library extends Storage<Text> {
    private String folder;
    private String extension;
    private Depot  depot;

    public Library(String folder, String extension, Depot depot) {
        this.folder = folder;
        this.extension = extension;
        this.depot = depot;
    }

    @Override
    public Class<Text> getStoredClass() {
        return Text.class;
    }

    @Override
    public Text retrieve(final String key) {
        InputStream i = Resources.class.getResourceAsStream("definitions/" + key + ".definition");
        if (i == null) return null;
        InputStreamReader reader = new InputStreamReader(i);
        final BufferedReader buffer = new BufferedReader(reader);
        try {
            final Iterator<String> iterator = new Iterator<String>() {
                private String line = buffer.readLine();

                @Override
                public boolean hasNext() {      
                    return line != null;
                }

                @Override
                public String next() {
                    try {
                        String l = line;
                        line = buffer.readLine();
                        return l;
                    } catch (IOException e) {
                        return null;
                    }
                }

                @Override
                public void remove() {
                    throw new UnsupportedOperationException("Not supported yet.");
                }

            };
            Text t =  new Text(new Iterable<String>() {
                @Override
                public Iterator<String> iterator() {
                    return iterator;
                }
            });                
            t.setPreprocessor(new Preprocessor(depot));
            return t;
        } catch (IOException e) {
        }

        return null;
    }


        

}
