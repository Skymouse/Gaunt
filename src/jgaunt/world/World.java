package jgaunt.world;

import java.util.*;

/**
 *
 * @author woeltjen
 */
public class World implements Component {
    private int ticks = 0;
    private Collection<Entity> entities = new ArrayList<Entity>();
    
    public  int getTicks() { return ticks; }
    
    public  void tick(int count) { ticks += count; }

    public Collection<Entity> getEntities() {
        return entities;
    }

    public Collection<Entity> getEntities(Boundary region) {
        List<Entity> subset = new ArrayList<Entity>();
        for (Entity e : entities)
            for (Boundary b : e.get(Boundary.class))
                if (region.overlaps(b)) subset.add(e);
        return subset;
    }

    public void addEntity(Entity e) {
        entities.add(e);
        e.set(World.class, this);
    }


}
