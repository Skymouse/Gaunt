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
    private int         angles;
    private int         frames;
    
    public Rotary (Animation animation, int angles) {
        this.animation  = animation;
        this.angles     = angles;
        this.frames     = animation.frames() / angles;
        this.animations = new Animation[angles];
        for (int i = 0; i < angles; i++)
            animations[i] = new Sequence(i * frames);
    }
    
    @Override
    public Animation getAnimation(float angle) {
        int index = (int) (angles * angle / (float) Math.PI * 2.0f);
        return animations[index % angles];
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
