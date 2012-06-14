/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jgaunt.engine.renderer;

/**
 *
 * @author woeltjen
 */
public class Rotary implements Hologram {
    private Animation   animation;
    private Animation[] animations;
    private float       start;
    private int         angles;
    private int         frames;
    
    public Rotary (Animation animation, int angles, float start) {
        this.start      = start;
        this.animation  = animation;
        this.angles     = angles;
        this.frames     = animation.frames() / angles;
        this.animations = new Animation[angles];
        for (int i = 0; i < angles; i++)
            animations[i] = new Sequence(i * frames);
    }
    
   
    
    @Override
    public Animation getAnimation(float angle) {
        while (angle < 0.0f) angle += Math.PI * 2.0;
        float rotations = (angle - start) / (float) (Math.PI * 2.0f);
        int   shift     = (int) (rotations * angles);
        int   index = shift % angles;
        return animations[index];
    }
    
    private class Sequence implements Animation {
        private int offset;
        
        public Sequence (int offset) {
            this.offset = offset;
        }

        @Override
        public int frames() {
            return frames;
        }

        @Override
        public Sprite getSprite(int frame) {
            return animation.getSprite(offset + (frame % frames));
        }
        
    }
}
