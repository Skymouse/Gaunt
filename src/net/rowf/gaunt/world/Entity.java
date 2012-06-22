package net.rowf.gaunt.world;


import java.util.*;

/**
 *
 * @author woeltjen
 */
public final class Entity implements Cloneable {
    private LinkedList<Component> components = new LinkedList<Component>();
    private Map<Class, Collection<Component>> cache = 
            new HashMap<Class, Collection<Component>>();

    public Entity() {}

    public Entity(List<Component> components) {
        this.components.addAll(components);
    }

    public <C extends Component> Collection<C> first(Class<C> cClass) {
        for (Component c : components) if (cClass.isAssignableFrom(c.getClass())) return Collections.singleton((C) c);
        return Collections.emptyList();
    }

    public <C extends Component> Collection<C> get(Class<C> bClass) {
        if (cache.containsKey(bClass)) return (Collection<C>) cache.get(bClass);
        Collection<C> subset = new ArrayList<C>();
        for (Component c : components) if (bClass.isAssignableFrom(c.getClass())) subset.add((C)c);
        cache.put(bClass, (Collection<Component>) subset);
        return subset;
    }

    public void add (Component c) {
        cache.clear();
        components.add(c);
    }

    public void remove(Component c) {
        cache.clear();
        components.remove(c);
    }

    public <C extends Component> void removeAll(Class<C> cClass) {
        cache.clear();
        components.removeAll(get(cClass));
    }

    public <C extends Component> void set (Class<C> cClass, C c) {
        cache.clear();
        removeAll(cClass);
        add(c);
    }

    @Override
    public Entity clone() {
        return new Entity(components);
    }
    
}
