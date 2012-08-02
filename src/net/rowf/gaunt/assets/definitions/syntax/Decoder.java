/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.assets.definitions.syntax;

/**
 *
 * @author woeltjen
 */
public interface Decoder<T> {
    public T decode(String argument);
    
    public static final Decoder<Integer> INTEGER = new Decoder<Integer>() {
    @Override
    public Integer decode(String argument) {
        try {
                return Integer.parseInt(argument);
            } catch (Exception e) {
                return 0;
            }
        }
    };
    
    public static final Decoder<Float> FLOAT = new Decoder<Float>() {
        @Override
        public Float decode(String argument) {
            try {
                return Float.parseFloat(argument);
            } catch (Exception e) {
                return 0f;
            }
        }
    };
    
    public static final Decoder<String> STRING = new Decoder<String>() {
        @Override
        public String decode(String argument) {
            try {
                return argument;
            } catch (Exception e) {
                return argument;
            }
        }
    };
  
}
