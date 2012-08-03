package net.rowf.gaunt.world;

import net.rowf.gaunt.world.components.Boundary;
import java.util.*;

/**
 *
 * @author woeltjen
 */
public class World implements Component {
    private int ticks = 0;
    private Collection<Entity> entities = new ArrayList<Entity>();
    private Collection<Entity> fresh    = new ArrayList<Entity>();
    private Collection<Entity> stale    = new ArrayList<Entity>();
    
    public  int getTicks() { return ticks; }
    
    public  void tick(int count) {
        // TODO: Move out of tick?
        for (Entity e : stale) e.remove(this);
        for (Entity e : fresh) e.set(World.class, this);
        entities.removeAll(stale);
        entities.addAll   (fresh);
        stale.clear();
        fresh.clear();
        
        ticks += count; 
    }

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
        fresh.add(e);
    }
    
    public void removeEntity(Entity e) {
        stale.add(e);
    }


}
