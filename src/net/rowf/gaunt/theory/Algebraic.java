/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.theory;

/**
 *
 * @author woeltjen
 */
public class Algebraic<K> implements Function<Double, K> {
    private Function <Double, K> function;

    public Algebraic(Function<Double, K> function) {
        this.function = function;
    }

    @Override
    public Double evaluate(K input) {
        return function.evaluate(input);
    }
    
    public Algebraic<K> multiply(final double value) {
        return new Algebraic<K>(this) {
            @Override
            public Double evaluate(K input) {
                return super.evaluate(input) * value;
            }            
        };
    }
    
    public Algebraic<K> divide(final double value) {
        return new Algebraic<K>(this) {
            @Override
            public Double evaluate(K input) {
                return super.evaluate(input) / value;
            }            
        };
    }
    
    
}
