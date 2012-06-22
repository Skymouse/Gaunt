/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.assets.definitions.parser;

import java.util.Iterator;
import java.util.Stack;

/**
 *
 * @author woeltjen
 */
public class Text implements Iterable<String> {
    private Preprocessor       preprocessor = null;
    private Iterable<String>   lines;
    
    public Text(Iterable<String> lines) {
        this.lines = lines;
    }
    
    public void setPreprocessor(Preprocessor preprocessor) {
        this.preprocessor = preprocessor;
    }

    @Override
    public Iterator<String> iterator() {
        return new Traverser(lines.iterator());
    }
    
    
    
    private class Traverser implements Iterator<String> {
        private Stack<Iterator<String>> iterators = new Stack<Iterator<String>>();

        public Traverser(Iterator<String> iterator) {
            iterators.push(iterator);
        }
        
        @Override
        public boolean hasNext() {
            while (!iterators.isEmpty())
                if (iterators.peek().hasNext()) return true;
                else                            iterators.pop();
            return false;
        }

        @Override
        public String next() {
            while (!iterators.isEmpty()) {
                if (iterators.peek().hasNext()) {
                    String line = iterators.peek().next();
                    if (preprocessor != null && preprocessor.handles(line)) {
                        iterators.push(preprocessor.expand(line).iterator());
                    } else {
                        return line;
                    }
                } else {
                    iterators.pop();
                }
            }
            return null;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Immutable.");
        }
        
    }
    
}
